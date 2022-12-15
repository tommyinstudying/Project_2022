package model;

/**
 * Enum that contains names of branches.
 */
public enum Branch {
  ISI("ISI"),
  RT("RT"),
  A2I("A2I"),
  GI("GI"),
  GM("GM"),
  MTE("MTE"),
  MM("MM");

  private final String stringValue;

  Branch(final String stringValue) {
    this.stringValue = stringValue;
  }

  public String toString() {
    return stringValue;
  }
}
