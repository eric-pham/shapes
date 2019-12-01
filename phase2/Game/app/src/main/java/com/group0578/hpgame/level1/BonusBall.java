package com.group0578.hpgame.level1;

import android.graphics.Color;

/** The ball responsible for bonus points, instant clear of the stage */
class BonusBall extends FlyingBall {

  /** Constructor that creates the point ball and colour */
  BonusBall(String theme) {
    super();
    this.radius = 30;
    this.speed = 50;
    if (theme.equalsIgnoreCase("Light")) paint.setColor(Color.MAGENTA);
    else paint.setColor(Color.DKGRAY);
  }

  /**
   * Override parent function because bonus ball behaves differently Bonus ball is infrequent so X
   * position is much larger
   */
  public void update(int width, int height) {

    int minCharY = 200;
    int maxCharY = height - 70;

    this.x = this.x - this.speed;

    if (this.x < 0) {

      this.x = width + 10000;
      this.y = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
    }
  }
}
