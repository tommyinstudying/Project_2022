package model;

public class StudentType {
  private StudentTypeName typeName;
  private PointsDistribution additionalDistribution;

  public StudentType(StudentTypeName typeName, PointsDistribution additionalDistribution) {
    this.typeName = typeName;
    this.additionalDistribution = additionalDistribution;
  }

  public StudentTypeName getTypeName() {
    return this.typeName;
  }

  public PointsDistribution getAdditionalDistribution() {
    return this.additionalDistribution;
  }
}
