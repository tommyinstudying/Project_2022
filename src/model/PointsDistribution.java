package model;

public class PointsDistribution {
  private int degirity = 0;
  private int force = 0;
  private int resistance = 0;
  private int constitution = 0;
  private int initiative = 0;

  public int calculateDiff(PointsDistribution distribution) {
    int diff = 0;

    diff += this.degirity - distribution.degirity;
    diff += this.force - distribution.force;
    diff += this.resistance - distribution.resistance;
    diff += this.constitution - distribution.constitution;
    diff += this.initiative - distribution.initiative;

    return diff;
  }

  /**
   * Degirity getter
   */
  public int getDegirity() {
    return this.degirity;
  }

  /**
   * Degirity setter
   */
  public void setDegirity(int degirity) {
    this.degirity = degirity;
  }

  /**
   * Force setter
   */
  public int getForce() {
    return this.force;
  }

  /**
   * Force setter
   */
  public void setForce(int force) {
    this.force = force;
  }

  /**
   * Resistance getter
   */
  public int getResistance() {
    return this.resistance;
  }

  /**
   * Resistance setter
   */
  public void setResistance(int resistance) {
    this.resistance = resistance;
  }

  /**
   * Constitution getter
   */
  public int getConstitution() {
    return this.constitution;
  }

  /**
   * Constitution setter
   */
  public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  /**
   * Initiative getter
   */
  public int getInitiative() {
    return this.initiative;
  }

  /**
   * Initiative setter
   */
  public void setInitiative(int initiative) {
    this.initiative = initiative;
  }

}
