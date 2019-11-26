package com.group0578.hpgame.Level1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.group0578.hpgame.model.Timer;

public class FlyingView extends View {

  private Paint character = new Paint();
  private int charX = 50;
  private int charY;
  private int charSpeed;

  private int yellowX, yellowY, yellowSpeed = 16;
  private Paint yellowPaint = new Paint();

  private int greenX, greenY, greenSpeed = 20;
  private Paint greenPaint = new Paint();

  private int collected, lives, goal;

  private Paint background = new Paint();
  private Paint scorePaint = new Paint();

  private Timer timer = new Timer();

  public FlyingView(Context context) {
    super(context);

    character.setColor(Color.RED);

    background.setARGB(255, 0, 191, 230);

    scorePaint.setColor(Color.WHITE);
    scorePaint.setTextSize(70);
    scorePaint.setTypeface(Typeface.DEFAULT_BOLD);

    yellowPaint.setColor(Color.YELLOW);
    greenPaint.setColor(Color.GREEN);

    collected = 0;
    lives = 3;
    goal = 10;

    charY = 100;

    timer.start();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPaint(background);

    int canvasWidth = getWidth();
    int canvasHeight = getHeight();

    int minCharY = 100;
    int maxCharY = canvasHeight - 40;
    charY = charY + charSpeed;

    if (charY < minCharY) {
      charY = minCharY;
    }
    if (charY > maxCharY) {
      charY = maxCharY;
    }
    charSpeed = charSpeed + 2;

    yellowX = yellowX - yellowSpeed;

    if (collisionChecker(yellowX, yellowY)) {
      collected = collected + 1;
      yellowX = -100;
    }

    if (yellowX < 0) {
      yellowX = canvasWidth + 21;
      yellowY = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
    }
    canvas.drawCircle(yellowX, yellowY, 20, yellowPaint);

    greenX = greenX - greenSpeed;

    if (collisionChecker(greenX, greenY)) {
      greenX = -100;
      lives--;

      if (lives == 0) {
        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
      }
    }

    if (greenX < 0) {
      greenX = canvasWidth + 21;
      greenY = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
    }
    canvas.drawCircle(greenX, greenY, 30, greenPaint);

    canvas.drawCircle(charX, charY, 40, character);
    canvas.drawText("Collected : " + collected, 0, 60, scorePaint);
    canvas.drawText("Goal : " + goal, (canvasWidth / 3) + 70, 60, scorePaint);
    canvas.drawText("Lives : " + lives, (canvasWidth / 3) * 2 + 50, 60, scorePaint);
    canvas.drawText(timer.getTimerString(), 0, 120, scorePaint);
  }

  public boolean collisionChecker(int x, int y) {
    return (charX < x && x < (charX + 40) && charY < y && y < (charY + 40));
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      charSpeed = -22;
    }
    return true;
  }
}
