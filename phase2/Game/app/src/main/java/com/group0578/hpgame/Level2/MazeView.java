package com.group0578.hpgame.Level2;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


/** Got inspiration from youtube channels Y-key, and mybringback which offered tutorials. */

/**
 * The Maze's view or visual appearance on the screen for the user.
 *
 * <p>Implementing View.OnTouchListener -- for performing actions in response to user clicks
 */
public class MazeView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

  /** The surfaceHolder containing the surface of this MazeView screen. */
  private SurfaceHolder surfaceHolder;

  /** An instance of the mazePresenter responsible for handling user's actions. */
  private MazePresenter mazePresenter;

  /** The MazeThread created in order to draw the Maze. */
  private MazeThread mazeThread;


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
    mazePresenter = new MazePresenter(this);
    this.mazeThread = new MazeThread(surfaceHolder, this, mazePresenter);
    mazePresenter.setUsername(((MazeActivity) context).getUsername());
    mazePresenter.setSQLHelper(((MazeActivity) context).getSqlHelper());

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
    String TAG = "MazeBuilder.makePlayer";
    Log.e(TAG, "test");

    // If the user clicks and drags the mouse, then an action has been detected
    if (event.getAction() == MotionEvent.ACTION_MOVE) {

      // Gets the x and y coordinates of where the user clicked on the screen.
      float touchX = event.getX(), touchY = event.getY();

      // MazePresenter changes player location based on where the user clicked.
      mazePresenter.handlePlayerMovement(touchX, touchY);

    }

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      return true;
    }

    // Checking if player has reached exitPoint
    if (!mazeThread.isRunning()) {
      try {
        // Destroying the thread.
//          mazeThread.setRunning(false);
        mazeThread.join();
        setVisibility(GONE); // Moving to level 3.

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return true; // returns true so the user can continue dragging/clicking to move the player
  }

}
