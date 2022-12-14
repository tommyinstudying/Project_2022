package model;

/**
 * Enum that contains names of student types.
 */
public enum StudentTypeName {
  REGULAR("Regular"),
  ELITE("Elite"),
  MASTER_GOBI("Master Gobi");

  private final String stringValue;

  StudentTypeName(final String stringValue) {
    this.stringValue = stringValue;
  }

  public String toString() {
    return stringValue;
  }
}
