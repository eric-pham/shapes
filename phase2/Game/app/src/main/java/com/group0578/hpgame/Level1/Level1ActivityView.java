package com.group0578.hpgame.Level1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Level1ActivityView extends SurfaceView implements SurfaceHolder.Callback {

    private Level1MainThread thread;
    private CharacterSprite characterSprite;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public SkyManager skyManager;

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
        skyManager = new SkyManager(screenHeight,screenWidth);
        skyManager.createSkyItems();
        characterSprite = (CharacterSprite)skyManager.skyItems.get(0);

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
//        characterSprite.update(screenWidth,screenHeight);
        skyManager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas!=null){
            characterSprite.draw(canvas);
            skyManager.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int first = (screenHeight/3);
        int second = (screenHeight/3) * 2;

//        System.out.println(screenHeight);
//        System.out.println(first);
//        System.out.println(second);

        int x = (int)event.getX();
        int y = (int)event.getY();

        if (y>0 && y<first){
            System.out.println("Character on " + first/2);
            characterSprite.setLocation(100, first/2);
        }
        else if( y>= first && y < second){
            System.out.println("Character on " + (second -first/2));
            characterSprite.setLocation(100, second - first/2);
        }
        else{
            System.out.println("Character on " + (screenHeight -first/2));
            characterSprite.setLocation(100, screenHeight - first/2);
        }

        return super.onTouchEvent(event);
    }
}
