package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Level1MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private Level1ActivityView level1ActivityView;
    private boolean running;
    public static Canvas canvas;

    public Level1MainThread(SurfaceHolder surfaceHolder, Level1ActivityView level1ActivityView){

        super();
        this.surfaceHolder = surfaceHolder;
        this.level1ActivityView = level1ActivityView;

    }

    public void setRunning(boolean isRunning){
        running = isRunning;
    }

    public void run(){
        while(running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.level1ActivityView.update();
                    this.level1ActivityView.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally {
                if (canvas!=null)
                {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e){e.printStackTrace();}
                }
        }
        }
    }
}
