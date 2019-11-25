package com.group0578.hpgame.Level3;

public class Level3Timer {
    private long start;
    private boolean running;

    public Level3Timer() {
        start = System.currentTimeMillis();
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public long end() {
        return System.currentTimeMillis() - start;
    }

    public long getSeconds() {
        return (System.currentTimeMillis() - start) / 1000;
    }

    public long getMiliseconds() {
        return (System.currentTimeMillis() - start) % 1000;
    }
}
