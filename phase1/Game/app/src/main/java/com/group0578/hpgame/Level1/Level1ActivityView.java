package com.group0578.hpgame.Level1;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.group0578.hpgame.Level1.Level1MainThread;

public class Level1ActivityView extends SurfaceView implements SurfaceHolder.Callback {

    private Level1MainThread thread;

    public Level1ActivityView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new Level1MainThread(getHolder(),this);
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try{
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }

    }

    public void update(){

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
