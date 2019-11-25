package com.group0578.hpgame.Level1;

public class Stopwatch {

  private long start;
  private boolean running;

  public Stopwatch() {
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
