package com.group0578.hpgame.model;

/**
 * Timer class represents the amount of time used to complete a level in this game.
 */
public class Timer {

    /**
     * The number of seconds, minutes and hours taken to complete a level in this game.
     */
    private int seconds, minutes, hours;

    /**
     * Constructing a new Timer instance.
     * @param seconds the total number of seconds taken in a level's completion, must be >= 0.
     */
    public Timer(int seconds) {
        setSeconds(seconds);
    }

    /**
     * Calculating amount of time taken to complete a level in seconds.
     * @return total number of seconds to complete a level.
     */
    public int getTotalSeconds() {
        return this.seconds + (this.minutes * 60) + (this.hours * 3600);
    }

    /**
     * Setting the number of seconds taken to complete a level.
     * @param seconds number of seconds to complete a level.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
        if (this.seconds >= 60) {
            this.seconds = seconds % 60;
            setMinutes(seconds / 60);
        } else {
            this.minutes = 0;
            this.hours = 0;
        }
    }

    /**
     * Setter for minutes attribute
     * @param minutes the number of minutes taken to complete a level.
     */
    private void setMinutes(int minutes) {
        this.minutes = minutes;
        if (this.minutes >= 60) {
            this.minutes = minutes % 60;
            setHours(minutes / 60);
        } else {
            this.hours = 0;
        }
    }

    /**
     * Setter for hours attribute.
     * @param hours the number of hours taken to complete a level.
     */
    private void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * The string representation of this Timer object
     * @return the string of hours, minutes, seconds taken to complete a level.
     */
    public String toString() {
        return "H: " + this.hours + ", M: " + this.minutes + ", S: " + this.seconds;
    }
}
