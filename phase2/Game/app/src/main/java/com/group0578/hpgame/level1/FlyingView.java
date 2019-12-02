package com.group0578.hpgame.level1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;
import com.group0578.hpgame.view.GameOverActivity;

/** Handles visual appearance of the Flying Game */
class FlyingView extends View {
  /** Initialize a Timer for the level */
  private Timer timer = new Timer();

  /** Presenter for the level */
  private FlyingPresenter flyingPresenter;

  /** Store the username String to pass to next level later */
  private String username;

  /** Store the related interactor*/
  private FlyingInteractor flyingInteractor;

  /** Store the state of the game in a boolean*/
  private boolean gameOver = false;

  /**
   * Initialize a new FlyingView
   *
   * @param context context
   * @param sqlHelper required to help pull data from the database
   * @param username required to pull database data based on username
   */
  public FlyingView(Context context, SQLiteHelper sqlHelper, String username) {
    super(context);
    timer.start();
    this.username = username;
    this.flyingInteractor = new FlyingInteractor(sqlHelper, username);
    flyingPresenter = new FlyingPresenter(this, this.flyingInteractor);
  }

  /**
   * Override onDraw method, main body to update the screen Uses the presenter to update the game
   * state and draw the balls Also draws the stats and legend
   *
   * @param canvas the canvas to be drawn on
   */
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPaint(flyingPresenter.getBackground());

    int canvasWidth = getWidth();
    int canvasHeight = getHeight();

    // Updating the balls and player, and then drawing them
    flyingPresenter.updateGameState(canvasWidth, canvasHeight);
    flyingPresenter.draw(canvas);

    // Draws the stats that are being kept track of
    canvas.drawText(
        "Collected : " + flyingPresenter.getCollected(), 0, 60, flyingPresenter.getScorePaint());
    canvas.drawText(
        "Goal : " + flyingPresenter.getGoal(),
        (canvasWidth / 3) + 70,
        60,
        flyingPresenter.getScorePaint());
    canvas.drawText(
        "Lives : " + flyingPresenter.getLives(),
        (canvasWidth / 3) * 2 + 50,
        60,
        flyingPresenter.getScorePaint());
    canvas.drawText(timer.getSecondsPassedString(), 0, 120, flyingPresenter.getScorePaint());

    // Legend for instructions
    canvas.drawText("= +1", 70, canvasHeight - 20, flyingPresenter.getScorePaint());
    canvas.drawText(
        "= +10", (canvasWidth / 3) + 60, canvasHeight - 20, flyingPresenter.getScorePaint());
    canvas.drawText(
        "= -1 lives", canvasWidth - 320, canvasHeight - 20, flyingPresenter.getScorePaint());
    flyingPresenter.drawLegend(canvas, canvasHeight, canvasWidth);
  }

  /**
   * Override onTouchEvent method Uses the presenter to update the speed of the player, moving them
   * up the screen if the user has tapped the screen
   *
   * @param event the event if the user has touched the screen
   * @return always returns true signalling player has touched screen
   */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      flyingPresenter.setCharSpeed();
    }
    return true;
  }

  /** Lost all lives, transition to GameOver */
  public void goToGameOver() {
    // Update the database with default values
    flyingInteractor.updateDatabase(0, 0.0, "none");

    Intent gameOver = new Intent(getContext(), GameOverActivity.class);
    gameOver.putExtra("username", this.username);
    gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    getContext().startActivity(gameOver);
  }

  /** Goal reached transition to next level */
  public void goToTransition() {
    // Update the database with new data for lives and time
    flyingInteractor.updateDatabase(flyingPresenter.getLives(), timer.getSecondsPassed(), "one");

    Intent transition = new Intent(getContext(), FlyingTransitionActivity.class);
    transition.putExtra("username", this.username);
    transition.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    getContext().startActivity(transition);
  }

  /**
   * Gets if the game is over or not
   *
   * @return boolean representing if the game is over
   */
  public boolean getGameOver() {
    return this.gameOver;
  }

  /**
   * Sets the value to true if game is over
   * @param value boolean representing if the game is over
   */
  public void setGameOver(boolean value){this.gameOver = value;}
}
