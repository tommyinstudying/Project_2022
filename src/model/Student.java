package model;

/**
 * Class that contains the logic related to the student.
 */
public class Student {
  /**
   * Player that owns the student in their troop.
   */
  private Player player;

  /**
   * Boolean value representing whether
   * the student is a reservist or not.
   */
  private boolean isReservist = false;

  /**
   * Number value representing ECTS (health points)
   * of the student. By default it is 30.
   */
  private int ects = 30;

  /**
   * String value representing name of the student.
   * Should be unique because potentially this value
   * can be used to identify a specific student.
   */
  private String name;

  /**
   * Student type that may influence on
   * additional distribution points.
   */
  private StudentType type;

  /**
   * Points distribution that the student got.
   */
  private PointsDistribution distribution = new PointsDistribution();

  /**
   * Strategy of the student.
   * Can be offensive, defensive and random.
   * It influences on the algorithm how the student does acts.
   * It is implemented with pattern Strategy.
   */
  private Strategy strategy;

  /**
   * Constructor of class Student.
   * 
   * @param name Name of the student
   * @param type Type of the student
   */
  public Student(Player player, String name, StudentType type) {
    this.player = player;
    this.name = name;
    this.type = type;
  }

  /**
   * isReservist flag getter.
   * 
   * @return Boolean value of isReservist flag
   */
  public boolean getIsReservist() {
    return this.isReservist;
  }

  /**
   * isReservist flag setter.
   * 
   * @param isReservist Boolean value of isReservist flag to set
   */
  public void setIsReservist(boolean isReservist) {
    this.isReservist = isReservist;
  }

  /**
   * ECTS getter.
   * 
   * @return Number of ECTS
   */
  public int getEcts() {
    return this.ects;
  }

  /**
   * ECTS setter.
   * 
   * @param ects Number of ECTS to set
   */
  public void setEcts(int ects) {
    this.ects = ects;
  }

  /**
   * Name getter.
   * 
   * @return Name of the student
   */
  public String getName() {
    return this.name;
  }

  /**
   * Name setter.
   * 
   * @param name Name of the student to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Type getter.
   * 
   * @return Type of the student
   */
  public StudentType getType() {
    return this.type;
  }

  /**
   * Points distribution getter.
   * 
   * @return Points distribution of the student
   */
  public PointsDistribution getDistribution() {
    return this.distribution;
  }

  /**
   * Points distribution setter.
   * 
   * @param distribution Points distribution of the student to set
   */
  public void setDistribution(PointsDistribution distribution) {
    this.distribution = distribution;
  }
}
