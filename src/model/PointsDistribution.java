package model;

import helpers.Color;

/**
 * Class that containts the logic related to points distribution.
 * It is used as a structure to store points distribution,
 * also it incorporates related operations.
 */
public class PointsDistribution {
  /**
   * Quantitive measure of degirity.
   */
  private int degirity = 0;

  /**
   * Quantitive measure of force.
   */
  private int force = 0;

  /**
   * Quantitve measure of resistance.
   */
  private int resistance = 0;

  /**
   * Quantitve measure of constituion.
   */
  private int constitution = 0;

  /**
   * Quantitive measure of initiative.
   */
  private int initiative = 0;

  /**
   * Constructor of class PointsDistribution.
   */
  public PointsDistribution() {
  }

  /**
   * Copy constructor of class PointsDistribution.
   * 
   * @param distribution Target points distribution to copy
   */
  public PointsDistribution(PointsDistribution distribution) {
    this.degirity = distribution.degirity;
    this.force = distribution.force;
    this.resistance = distribution.resistance;
    this.constitution = distribution.constitution;
    this.initiative = distribution.initiative;
  }

  /**
   * Calculate difference between 2 points distributions.
   * Difference is not grouped, but just represented as single value.
   * 
   * @param distribution Distribution to calculate difference with
   * @return Single number value representing the difference
   */
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
   * Degirity getter.
   * 
   * @return Quantitive measure of degirity
   */
  public int getDegirity() {
    return this.degirity;
  }

  /**
   * Degirity setter.
   * 
   * @param degirity Number representing new degirity points
   * @return
   */
  public void setDegirity(int degirity) {
    this.degirity = degirity;
  }

  /**
   * Force setter.
   * 
   * @return Quantitive measure of force
   */
  public int getForce() {
    return this.force;
  }

  /**
   * Force setter.
   * 
   * @param force Number representing new force points
   */
  public void setForce(int force) {
    this.force = force;
  }

  /**
   * Resistance getter.
   * 
   * @return Quantitive measure of resistance
   */
  public int getResistance() {
    return this.resistance;
  }

  /**
   * Resistance setter.
   * 
   * @param resistance Number representing new resistance points
   */
  public void setResistance(int resistance) {
    this.resistance = resistance;
  }

  /**
   * Constitution getter.
   * 
   * @return Quantitive measure of constitution
   */
  public int getConstitution() {
    return this.constitution;
  }

  /**
   * Constitution setter.
   * 
   * @param constitution Number representing new constitution points
   */
  public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  /**
   * Initiative getter.
   * 
   * @return Quantitive measure of initiative
   */
  public int getInitiative() {
    return this.initiative;
  }

  /**
   * Initiative setter.
   * 
   * @param initiative Number representing new initiative points
   */
  public void setInitiative(int initiative) {
    this.initiative = initiative;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();

    sb.append("D ");
    sb.append(Color.MAGENTA_BRIGHT);
    sb.append(this.degirity);
    sb.append(Color.RESET);
    sb.append("\t");

    sb.append("F ");
    sb.append(Color.MAGENTA_BRIGHT);
    sb.append(this.force);
    sb.append(Color.RESET);
    sb.append("\t");

    sb.append("R ");
    sb.append(Color.MAGENTA_BRIGHT);
    sb.append(this.resistance);
    sb.append(Color.RESET);
    sb.append("\t");

    sb.append("C ");
    sb.append(Color.MAGENTA_BRIGHT);
    sb.append(this.constitution);
    sb.append(Color.RESET);
    sb.append("\t");

    sb.append("I ");
    sb.append(Color.MAGENTA_BRIGHT);
    sb.append(this.initiative);
    sb.append(Color.RESET);

    return sb.toString();
  }
}
