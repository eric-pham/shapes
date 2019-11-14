package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Level1MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private Level1ActivityView level1ActivityView;
    private boolean running;
    public static Canvas canvas;
    private int targetFPS = 60;
    private double averageFPS;

    public Level1MainThread(SurfaceHolder surfaceHolder, Level1ActivityView level1ActivityView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.level1ActivityView = level1ActivityView;

    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.level1ActivityView.update();
                    this.level1ActivityView.draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}
