package com.group0578.hpgame.level1;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class FlyingBall {

  /** Integer storing the x position of the ball */
  int x;

  /** Integer storing the y position of the ball */
  int y;

  /** Paint object storing the paint/color of the ball */
  Paint paint = new Paint();

  /** Integer storing the radius/size of the ball */
  int radius;

  /** Integer storing the speed of the ball */
  int speed;

  /** Constructor creates new flying ball, should not be called as it is abstract */
  FlyingBall() {}

  /**
   * Gets the radius of the ball
   *
   * @return integer of the radius
   */
  int getRadius() {
    return radius;
  }

  /**
   * Gets the x position of the ball
   *
   * @return integer of x position
   */
  int getX() {
    return x;
  }

  /**
   * Gets the y position of the ball
   *
   * @return integer of y position
   */
  int getY() {
    return y;
  }

  /** Sets the x position of the ball */
  void setX(int x) {
    this.x = x;
  }

  /** Sets the y position */
  void setY(int y) {
    this.y = y;
  }

  /** Update the position of the ball */
  void update(int width, int height) {

    int minCharY = 200;
    int maxCharY = height - 140;

    this.x = this.x - this.speed;

    if (this.x < 0) {
      this.x = width + 21;
      this.y = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
    }
  }

  /** Draw the ball on the screen */
  void draw(Canvas canvas) {
    canvas.drawCircle(x, y, radius, paint);
  }
}
