package model;

public enum Branch {
  ISI("ISI"),
  RT("RT"),
  A2I("A2I"),
  GI("GI"),
  GM("GM"),
  MTE("MTE"),
  MM("MM");

  private final String branchName;

  Branch(final String branchName) {
    this.branchName = branchName;
  }

  public String toString() {
    return branchName;
  }
}
