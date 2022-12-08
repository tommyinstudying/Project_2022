package model;

/**
 * Model of student
 */
public class Student {
  private int ects = 30;
  private String name;
  private StudentType type;

  private int degirity = 0;
  private int force = 0;
  private int resistance = 0;
  private int constitution = 0;
  private int initiative = 0;

  private Strategy strategy;

  Student(String name, StudentType type) {
    this.name = name;
    this.type = type;
  }

  /**
   * Name getter
   */
  public String getName() {
    return this.name;
  }

  /**
   * Name setter
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Type getter
   */
  public StudentType getType() {
    return this.type;
  }

  /**
   * Type setter
   */
  public void setType(StudentType type) {
    this.type = type;
  }

  /**
   * ECTS getter
   */
  public int getEcts() {
    return this.ects;
  }

  /**
   * ECTS setter
   */
  public void setEcts(int ects) {
    this.ects = ects;
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

  /**
   * Strategy getter
   */
  public Strategy getStrategy() {
    return this.strategy;
  }

  /**
   * Strategy setter
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}
