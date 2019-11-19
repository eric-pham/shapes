package com.group0578.hpgame.Level3;

import android.content.Intent;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
  private SurfaceHolder surfaceHolder;
  private ScreenView level3ActivityView;
  private boolean isRunning;
  public static Canvas canvas;

  /**
   * @param surfaceHolder
   * @param level3ActivityView
   */
  MainThread(SurfaceHolder surfaceHolder, ScreenView level3ActivityView) {

    super();
    this.surfaceHolder = surfaceHolder;
    this.level3ActivityView = level3ActivityView;
  }

  void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }

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
        this.sleep(300);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

  }

}
