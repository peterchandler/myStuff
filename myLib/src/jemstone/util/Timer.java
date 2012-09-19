package jemstone.util;

public class Timer {
  private long start;

  public Timer() {
    start = System.currentTimeMillis();
  }

  public long duration() {
    return System.currentTimeMillis() - start;
  }

  @Override
  public String toString() {
    return duration() + "ms";
  }
}
