package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class MainThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private ScreenView level3ActivityView;
    private boolean running;
    public static Canvas canvas;
    private int targetFPS = 60;
    private double averageFPS;

    public MainThread(SurfaceHolder surfaceHolder, ScreenView level3ActivityView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.level3ActivityView = level3ActivityView;

    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }





}
