package com.group0578.hpgame.Level3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
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
    private Manager roomManager;
    /** The part of the program that manages time. */
    private MainThread thread;

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
    }

    public static float getCharWidth() {
        return charWidth;
    }

    public static float getCharHeight() {
        return charHeight;
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
        roomManager =
                new Manager((int) (screenWidth / charWidth), (int) (screenHeight / charHeight));

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
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            roomManager.draw(canvas);
        }
    }
}
