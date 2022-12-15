package model;

/**
 * Class that contains logic related to student type.
 */
public class StudentType {
  /**
   * String value representing name of the type.
   */
  private StudentTypeName typeName;

  /**
   * Points distribution that will be additional
   * for the student that has the student type.
   */
  private PointsDistribution additionalDistribution;

  /**
   * Constructor of class StudentType.
   * 
   * @param typeName               Name of the type
   * @param additionalDistribution Points distribution that the type adds for the
   *                               student
   */
  public StudentType(StudentTypeName typeName, PointsDistribution additionalDistribution) {
    this.typeName = typeName;
    this.additionalDistribution = additionalDistribution;
  }

  /**
   * Type name getter.
   * 
   * @return Name of the type
   */
  public StudentTypeName getTypeName() {
    return this.typeName;
  }

  /**
   * Additional distribution getter.
   * 
   * @return Additional distribution
   */
  public PointsDistribution getAdditionalDistribution() {
    return this.additionalDistribution;
  }
}
