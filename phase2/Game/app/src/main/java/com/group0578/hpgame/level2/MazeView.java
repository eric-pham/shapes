package com.group0578.hpgame.level2;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

// Got inspiration from youtube channels Y-key, and mybringback which offered tutorials.

/**
 * The Maze's view or visual appearance on the screen for the user.
 *
 * <p>Implementing View.OnTouchListener -- for performing actions in response to user clicks
 */
public class MazeView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

  /** The surfaceHolder containing the surface of this MazeView screen. */
  private SurfaceHolder surfaceHolder;

  /** The MazeThread created in order to draw the Maze. */
  private MazeThread mazeThread;

  /** The class responsible for changing the player's position in the maze. */
  private PlayerPositioner playerPositioner;

  /** The maximum duration that will count as a click. */
  private static final int MAX_CLICK_DURATION = 200;

  /** The time representing the start of the click. */
  private long startClickTime;

  /**
   * Construct a new instance of a MazeView.
   *
   * @param context the environment making this MazeView appear on the screen.
   */
  public MazeView(Context context) {
    super(context);
    init(context);
  }

  /**
   * Construct a new instance of a MazeView.
   *
   * @param context the environment making this MazeView appear on the screen.
   */
  public MazeView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  /**
   * Construct a new instance of a MazeView.
   *
   * @param context the environment making this MazeView appear on the screen.
   */
  public MazeView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  /** Method called by constructor for initializing instance attributes. */
  private void init(Context context) {
    surfaceHolder = this.getHolder();
    surfaceHolder.addCallback(this);
    setFocusable(true);
    this.mazeThread =
        new MazeThread(
            surfaceHolder,
            this,
            ((MazeActivity) context).getSqlHelper(),
            ((MazeActivity) context).getUsername());
    this.playerPositioner = new PlayerPositioner(this.mazeThread.getMaze());
  }

  /**
   * This is called immediately after the surface is first created. Implementations of this should
   * start up whatever rendering code they desire. Note that only one thread can ever draw into a
   * {@link MazeView}, so you should not draw into the Surface here if your normal rendering will be
   * in another thread.
   *
   * @param holder The SurfaceHolder whose surface is being created.
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    // Enables the user to click the screen and drag the player through the maze.
    this.setOnTouchListener(this);

    // Begins running the mazeThread
    mazeThread.setRunning(true);
    mazeThread.start();
  }

  /**
   * This is called immediately after any structural changes (format or size) have been made to the
   * surface. You should at this point update the imagery in the surface. This method is always
   * called at least once, after {@link #surfaceCreated}.
   *
   * @param holder The SurfaceHolder whose surface has changed.
   * @param format The new PixelFormat of the surface.
   * @param width The new width of the surface.
   * @param height The new height of the surface.
   */
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  /**
   * This is called immediately before a surface is being destroyed. After returning from this call,
   * you should no longer try to access this surface. If you have a rendering thread that directly
   * accesses the surface, you must ensure that thread is no longer touching the Surface before
   * returning from this function.
   *
   * @param holder The SurfaceHolder whose surface is being destroyed.
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {}

  /**
   * Getter for surfaceHolder instance attribute
   *
   * @return the surface this view contains.
   */
  public SurfaceHolder getSurfaceHolder() {
    return surfaceHolder;
  }

  /**
   * Called when a touch event is dispatched to a view. This allows users to click/drag the player
   * on the screen.
   *
   * @param v The view the touch event has been dispatched to.
   * @param event The MotionEvent object containing full information about the event.
   * @return True if the listener has consumed the event, false otherwise.
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    String TAG = "MazeView.onTouch";
    Log.e(TAG, "test");

    // Gets the x and y coordinates of where the user clicked on the screen.
    float touchX = event.getX(), touchY = event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        startClickTime = System.currentTimeMillis();
        break;
      case MotionEvent.ACTION_UP:
        // this is the time in milliseconds
        long diff = System.currentTimeMillis() - startClickTime;
        if (diff <= MAX_CLICK_DURATION) {
          // if the click is in the top right hand corner, they can bypass the maze level
          if (touchX >= Resources.getSystem().getDisplayMetrics().widthPixels - 100
              && touchY <= 100) {
            mazeThread.setLevelWon(true);
            mazeThread.updateDatabase();
            mazeThread.setRunning(false);
            try {
              mazeThread.join();
            } catch (Exception e) {
              e.printStackTrace();
            }
            stopGame();
          }
          break;
        }
      case MotionEvent.ACTION_MOVE:
        // MazePresenter changes player location based on where the user clicked.
        this.playerPositioner.handlePlayerMovement(touchX, touchY);
    }

    // The MazeThread stops running if the player dies or wins the game.
    if (!mazeThread.isRunning()) {
      stopGame();
    }

    return true; // returns true so the user can continue dragging/clicking to move the player
  }

  /** Ends the level by either taking the user to the Game Over screen or to the next level */
  private void stopGame() {
    if (mazeThread.isLevelWon()) { // user completed the level
      // hides the MazeView
      setVisibility(GONE);
      // hides the hint if the user figures it out before the message disappears.
      ((MazeActivity) this.getContext()).getToast().cancel();
    } else { // player has lost all lives thus losing the game
      // leads to the game over screen
      ((MazeActivity) this.getContext()).goToGameOver();
    }
  }
}
