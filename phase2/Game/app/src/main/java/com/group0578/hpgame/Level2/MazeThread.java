package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MazeThread extends Thread {
  private SurfaceHolder surfaceHolder;
  //  private MazeActivity mazeActivity;
  private boolean running = false;
  public Canvas mazeCanvas;
  private MazePresenter mazePresenter;
  private MazeView mazeView;

  MazeThread(SurfaceHolder surfaceHolder, MazeView mazeView, MazePresenter mazePresenter) {
    super();
    this.surfaceHolder = surfaceHolder;
    //    this.mazeActivity = mazeActivity;
    this.mazePresenter = mazePresenter;
    this.mazeView = mazeView;
  }

  void setRunning(boolean running) {
    this.running = running;
    String TAG = "MazeThread.setRunning";
    Log.e(TAG, "test");
  }

  @Override
  public void run() {
    String TAG = "MazeThread.run";
    Log.e(TAG, "test");

    // Only draw the Maze when the thread is currently running
    while (running) {
      if (!mazeView.getSurfaceHolder().getSurface().isValid()) {
        Log.e(TAG, "test to see if it skips over the while loop iterations");
        continue;
      }
      Log.e(TAG, "test to see if it now it executes the while loop stuff");
      System.out.println("running");
      mazeCanvas = mazeView.getSurfaceHolder().lockCanvas();
      mazeCanvas.drawARGB(255, 100, 30, 250);
      mazeCanvas.drawText("Player = Circle", 100, 100, mazePresenter.getTextBrush());
      mazeCanvas.drawText("End Point = Square", 100, 160, mazePresenter.getTextBrush());

      mazePresenter.drawMazeWalls(mazeCanvas);
      mazePresenter.drawPlayer(mazeCanvas, mazePresenter.getMazeSectionLength() / 10);
      mazePresenter.drawExitPoint(mazeCanvas, mazePresenter.getMazeSectionLength() / 10);

      surfaceHolder.unlockCanvasAndPost(mazeCanvas);

      try {
        sleep(150);
      } catch (Exception e) {
        e.printStackTrace();
      }

      checkExitReached();
    }
  }

  private void checkExitReached() {
    if (mazePresenter.getPlayer().getRow() == mazePresenter.getExitPoint().getRow()
        && mazePresenter.getPlayer().getCol() == mazePresenter.getExitPoint().getCol()) {
      mazePresenter.setGameWon(true);
    }
  }
}
