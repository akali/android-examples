package kz.mathncode.testingapp;

public class NumberProvider {

  private int q;

  public int getNumber() {
    return 3;
  }

  public int calculate() {
    return q * q;
  }

  public int badMethod() {
    q = 5;
    return q;
  }

  public int goodMethod() {
    int q = 5;
    // ...
    return q;
  }
}
