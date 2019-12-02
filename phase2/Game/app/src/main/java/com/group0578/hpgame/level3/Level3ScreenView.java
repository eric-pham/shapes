package com.group0578.hpgame.level3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;
import com.group0578.hpgame.view.GameOverActivity;
import com.group0578.hpgame.view.ScoreboardActivity;


/**
 * The level's view or visual appearance on the screen for the user.
 */
public class Level3ScreenView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The width of a character.
     */
    private static float charWidth;
    /**
     * The height of a character.
     */
    private static float charHeight;
    /**
     * The screen contents.
     */
    private static Manager roomManager;
    /**
     * The part of the program that manages time.
     */
    private Level3MainThread thread;
    /**
     * The screen's background colour.
     */
    private static Paint background = new Paint();
    /**
     * Timer for this screen.
     */
    private Timer level3Timer = new Timer();
    /**
     * Paint for texts on the screen.
     */
    private Paint scorePaint = new Paint();

    /**
     * Construct a new instance of a Level3ScreenView.
     *
     * @param context the environment making this view appear on the screen.
     */
    public Level3ScreenView(Context context) {
        super(context);
        init();
    }

    /**
     * Construct a new instance of a Level3ScreenView.
     *
     * @param context the environment making this view appear on the screen.
     * @param attrs   default attribute of the parent class.
     */
    public Level3ScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Construct a new instance of a Level3ScreenView.
     *
     * @param context  the environment making this view appear on the screen.
     * @param attrs    default attribute of the parent class.
     * @param defStyle default style of the parent class.
     */
    public Level3ScreenView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Method called by constructor for initializing instance attributes.
     */
    private void init() {
        getHolder().addCallback(this);
        thread = new Level3MainThread(getHolder(), this);
        setFocusable(true);
        level3Timer.start();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
    }
    /**
     * Getter for character width.
     *
     * @return The character width.
     */
    public static float getCharWidth() {
        return charWidth;
    }

    /**
     * Getter for character height.
     *
     * @return The character height.
     */
    public static float getCharHeight() {
        return charHeight;
    }

    /**
     * Getter for the Manager for this level.
     *
     * @return The Manager for this level.
     */
    public static Manager getRoomManager() {
        return roomManager;
    }

    public static void setBackground(int a, int r, int g, int b) {
        background.setARGB(a, r, g, b);
    }

    /**
     * This is called immediately after the surface is first created. Implementations of this should
     * start up whatever rendering code they desire. Note that only one thread can ever draw into a
     * {@link Level3ScreenView}, so you should not draw into the Surface here if your normal
     * rendering will be in another thread.
     *
     * @param holder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // Figure out the size of a letter.
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        charWidth = paintText.measureText("W");
        charHeight = -paintText.ascent() + paintText.descent();

        // Use the letter size and screen height to determine the size of the screen.
        roomManager = new Manager((int) (screenWidth / charWidth), (int) (screenHeight / charHeight),
                ((Level3MainActivity) getContext()).getPlayer());

        roomManager.createDementors();
        roomManager.createSpecialItems();

        thread.setRunning(true);
        thread.start();
    }

    /**
     * This is called immediately after any structural changes (format or size) have been made to the
     * surface. You should at this point update the imagery in the surface. This method is always
     * called at least once, after {@link #surfaceCreated}.
     *
     * @param holder The SurfaceHolder whose surface has changed.
     * @param format The new PixelFormat of the surface.
     * @param width  The new width of the surface.
     * @param height The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * This is called immediately before a surface is being destroyed. After returning from this call,
     * you should no longer try to access this surface. If you have a rendering thread that directly
     * accesses the surface, you must ensure that thread is no longer touching the Surface before
     * returning from this function.
     *
     * @param holder The SurfaceHolder whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Updates the items on the screen and stops the running thread when the game is over.
     */
    public void update() {
        roomManager.updateDementor();
        roomManager.updateBlasts();
        roomManager.updatePlayer();
        roomManager.updateSpecialItems();
        int lives = ((Level3MainActivity) getContext()).getPlayer().getLives();
        if ((Manager.getKilledDementorsCount() == 5) || roomManager.getSpecialItems().isEmpty() || lives == 0) {
            try {
                thread.setRunning(false);
                setVisibility(INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draw the view and the texts on the canvas.
     *
     * @param canvas the canvas the view is drawn on.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPaint(background);
        roomManager.draw(canvas);
        int lives = ((Level3MainActivity) getContext()).getPlayer().getLives();
        canvas.drawText(level3Timer.getSecondsPassedString(), 0, 120, scorePaint);
        canvas.drawText("Collected : " + Manager.getKilledDementorsCount(), 0, 60, scorePaint);
        canvas.drawText("Goal : 5", (screenWidth / 3) + 70, 60, scorePaint);
        canvas.drawText("Lives : " + lives, (screenWidth / 3) * 2 + 30, 60, scorePaint);

        if (lives == 0) {
            goToGameOver();
        } else if (Manager.getKilledDementorsCount() >= 5 ||
                Level3ScreenView.getRoomManager().getSpecialItems().isEmpty()) {
            updateDatabase(lives);
        }

    }

    /**
     * Update the statistics of the user.
     *
     * @param lives the lives of the user.
     */
    private void updateDatabase(int lives) {
        String username = ((Level3MainActivity) getContext()).getUsername();
        SQLiteHelper sqLiteHelper = ((Level3MainActivity) getContext()).getSqlHelper();
        sqLiteHelper.setLives(username, lives);
        sqLiteHelper.setProgress(username, "three");
        sqLiteHelper.setLevelThreeTime(username, level3Timer.getSecondsPassed());
        sqLiteHelper.saveNewScore(username);
        sqLiteHelper.saveNewTotalTime(username);
        sqLiteHelper.saveNewAvgTime(username);

        goToPlayerStats();
    }

    /**
     * Go to the GameOver activity when the user loses.
     */
    public void goToGameOver() {
        Intent gameOver = new Intent(getContext(), GameOverActivity.class);
        gameOver.putExtra("username", ((Level3MainActivity) getContext()).getUsername());
        gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(gameOver);
    }

    /**
     * Go to the Scoreboard activity when the user wins.
     */
    private void goToPlayerStats() {
        Intent displayStats = new Intent(getContext(), ScoreboardActivity.class);
        displayStats.putExtra("username", ((Level3MainActivity) getContext()).getUsername());
        displayStats.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(displayStats);
    }

}
