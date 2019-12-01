package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Level3MainThread extends Thread {
    /**
     * The surface which manages this thread.
     */
    private final SurfaceHolder surfaceHolder;
    /**
     * View for this thread.
     */
    private Level3ScreenView level3ActivityView;
    /**
     * Whether the thread is still running.
     */
    private boolean isRunning;
    /**
     * the graphics context in which to draw.
     */
    public static Canvas canvas;

    /**
     * @param surfaceHolder      the surface creating this thread.
     * @param level3ActivityView View for this thread.
     */
    Level3MainThread(SurfaceHolder surfaceHolder, Level3ScreenView level3ActivityView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.level3ActivityView = level3ActivityView;
    }

    /**
     * Setter for the running status of the thread.
     *
     * @param isRunning new running status of the thread.
     */
    void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Runs the thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.level3ActivityView.update();
                    this.level3ActivityView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
