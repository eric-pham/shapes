package com.group0578.hpgame.Level3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class ScreenView extends SurfaceView implements SurfaceHolder.Callback {

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
  private MainThread thread;

  private Paint background = new Paint();
  private Level3Timer level3Timer = new Level3Timer();
  private Paint scorePaint = new Paint();

  public ScreenView(Context context) {
    super(context);
    init();
  }

  public ScreenView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ScreenView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    getHolder().addCallback(this);
    thread = new MainThread(getHolder(), this);
    setFocusable(true);
    background.setARGB(255, 0, 191, 230);
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

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    // Use the letter size and screen height to determine the size of the screen.
    roomManager = new Manager((int) (screenWidth / charWidth), (int) (screenHeight / charHeight));

    roomManager.createDementors();

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
    if (roomManager.getMyLittledementors().isEmpty()) {
      System.out.println("Rick and Morty");
      try{
        thread.setRunning(false);
        // thread.join();
        System.out.println("Paul Gries");
        setVisibility(INVISIBLE);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      roomManager.draw(canvas);
    }

    String timeDisplay;
    String timerMSString = String.valueOf(level3Timer.getMiliseconds());
    if (timerMSString.length() == 1) {
      timeDisplay = level3Timer.getSeconds() + ".00" + level3Timer.getMiliseconds();
    } else if (timerMSString.length() == 2) {
      timeDisplay = level3Timer.getSeconds() + ".0" + level3Timer.getMiliseconds();
    } else {
      timeDisplay = level3Timer.getSeconds() + "." + level3Timer.getMiliseconds();
    }
    canvas.drawText("Timer : " + timeDisplay, 0, 120, scorePaint);
    System.out.println(timeDisplay);
  }
}
