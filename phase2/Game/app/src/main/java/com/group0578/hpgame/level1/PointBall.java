package com.group0578.hpgame.level1;

import android.graphics.Color;

/** The ball responsible for giving points */
class PointBall extends FlyingBall {

  /** Constructor that creates the point ball and colour */
  PointBall(String theme) {
    super();
    this.radius = 20;
    this.speed = 16;
    if (theme.equalsIgnoreCase("Light")) paint.setColor(Color.YELLOW);
    else paint.setColor(Color.LTGRAY);
  }
}
