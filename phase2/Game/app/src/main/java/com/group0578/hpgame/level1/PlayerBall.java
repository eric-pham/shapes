package com.group0578.hpgame.level1;

import android.graphics.Canvas;
import android.graphics.Color;

/** The Player's ball/character in the game */
class PlayerBall extends FlyingBall {

  /** Keeps track of whether the character is a circle or a square */
  private String character;

  /** Constructor that creates the players ball and colour */
  PlayerBall(String theme, String character) {
    super();
    this.y = 100;
    this.radius = 70;
    this.speed = 0;
    this.character = character;
    if (theme.equalsIgnoreCase("Light")) this.paint.setColor(Color.BLACK);
    else this.paint.setColor(Color.WHITE);
  }

  /** Override parent function because player ball only goes up and down
   * @param width the width
   * @param height the height
   */
  public void update(int width, int height) {
    int minCharY = 200;
    int maxCharY = height - 140;

    this.y = this.y + this.speed;

    if (this.y < minCharY) {
      this.y = minCharY;
    }
    if (this.y > maxCharY) {
      this.y = maxCharY;
    }
    this.speed = this.speed + 2;
  }

  /**
   * Override parent function because player can be either a circle or a square Draws the player
   * based on character option
   */
  @Override
  public void draw(Canvas canvas) {
    if (this.character.equalsIgnoreCase("Circle")) canvas.drawCircle(radius, y, radius, paint);
    else canvas.drawRect(0, y + radius, 140, y - radius, paint);
  }
}
