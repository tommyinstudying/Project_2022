package model;

public enum StudentType {
  REGULAR("Regular"),
  ELITE("Elite"),
  MASTER_GOBI("Master Gobi");

  private final String type;

  StudentType(final String type) {
    this.type = type;
  }

  public String toString() {
    return type;
  }
}
