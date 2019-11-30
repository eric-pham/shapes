package com.group0578.hpgame.level3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.group0578.hpgame.model.Timer;
import com.group0578.hpgame.view.GameOverActivity;
import com.group0578.hpgame.view.StatsActivity;

public class Level3ScreenView extends SurfaceView implements SurfaceHolder.Callback {

  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The width of a character. */
  private static float charWidth;
  /** The height of a character. */
  private static float charHeight;

  /** The screen contents. */
  private static Manager roomManager;
  /** The part of the program that manages time. */
  private Level3MainThread thread;

  private static Paint background = new Paint();
  private Timer level3Timer = new Timer();
  private Paint scorePaint = new Paint();

  public Level3ScreenView(Context context) {
    super(context);
    init();
  }

  public Level3ScreenView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public Level3ScreenView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    getHolder().addCallback(this);
    thread = new Level3MainThread(getHolder(), this);
    setFocusable(true);
    level3Timer.start();
    scorePaint.setColor(Color.WHITE);
    scorePaint.setTextSize(70);
    scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
  }

  public static float getCharWidth() {
    return charWidth;
  }

  public static float getCharHeight() {
    return charHeight;
  }

  public static Manager getRoomManager() {
    return roomManager;
  }

  public static void setBackground(int a, int r, int g, int b) {
    background.setARGB(a, r, g, b);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    // Use the letter size and screen height to determine the size of the screen.
    roomManager = new Manager((int) (screenWidth / charWidth), (int) (screenHeight / charHeight),
            ((Level3MainActivity) getContext()).getPlayer());

    roomManager.createDementors();
    roomManager.createObjects();

    thread.setRunning(true);
    thread.start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {

    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  public void update() {
    roomManager.updateDementor();
    roomManager.updateBlasts();
    roomManager.updateWand();
    roomManager.updateObjects();
    if (roomManager.getNumDementorsKilled() == 5 || roomManager.getObjects().isEmpty()) {
      try {
        thread.setRunning(false);
         //thread.join();
         setVisibility(INVISIBLE);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    canvas.drawPaint(background);
    roomManager.draw(canvas);
    int lives = ((Level3MainActivity) getContext()).getPlayer().getLives();
    canvas.drawText(level3Timer.getSecondsPassedString(), 0, 120, scorePaint);
    canvas.drawText("Collected : " + Manager.getNumDementorsKilled(), 0, 60, scorePaint);
    canvas.drawText("Goal : 5" , (screenWidth / 3) + 70, 60, scorePaint);
    canvas.drawText("Lives : " + lives, (screenWidth / 3) * 2 + 30, 60, scorePaint);

    if (lives == 0){
      goToGameOver();
    }
    else if (roomManager.getNumDementorsKilled() >= 5 ||
            Level3ScreenView.getRoomManager().getObjects().isEmpty()) {
      goToLevel3Transition();
    }

  }

  public void goToGameOver(){
    Intent gameOver = new Intent(getContext(), GameOverActivity.class);
    gameOver.putExtra("username",((Level3MainActivity) getContext()).getUsername());
    gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    getContext().startActivity(gameOver);
  }

  public void goToLevel3Transition(){

    Intent displayStats = new Intent(getContext(), StatsActivity.class);
    displayStats.putExtra("username",((Level3MainActivity) getContext()).getUsername());
    displayStats.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    getContext().startActivity(displayStats);

  }

}
