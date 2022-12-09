package model;

public class Student {
  private int ects = 30;
  private String name;
  private StudentType type;

  private PointsDistribution distribution;

  private Strategy strategy;

  public Student(String name, StudentType type) {
    this.name = name;
    this.type = type;
  }

  public int getEcts() {
    return this.ects;
  }

  public void setEcts(int ects) {
    this.ects = ects;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StudentType getType() {
    return this.type;
  }

  public PointsDistribution getDistribution() {
    return this.distribution;
  }

  public void setDistribution(PointsDistribution distribution) {
    this.distribution = distribution;
  }
}
