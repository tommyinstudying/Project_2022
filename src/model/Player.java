package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player {
  private String name;
  private Branch branch;

  private ArrayList<Zone> controlledZones = new ArrayList<Zone>();
  private ArrayList<Student> students = new ArrayList<Student>();

  private int pointsLeft = 400;
  private HashMap<String, PointsDistribution> distributionMap = new HashMap<String, PointsDistribution>();

  Player(String name) {
    this.name = name;

    for (int i = 0; i < 15; i++) {
      PointsDistribution regularDistribution = new PointsDistribution();
      StudentType regularType = new StudentType(StudentTypeName.REGULAR, regularDistribution);
      this.students.add(new Student(name + " " + regularType.getTypeName() + " " + i, regularType));
    }

    for (int i = 0; i < 4; i++) {
      PointsDistribution eliteDistribution = new PointsDistribution();
      eliteDistribution.setDegirity(1);
      eliteDistribution.setForce(1);
      eliteDistribution.setResistance(1);
      eliteDistribution.setConstitution(1);
      eliteDistribution.setInitiative(1);
      StudentType eliteType = new StudentType(StudentTypeName.ELITE, eliteDistribution);
      this.students.add(new Student(name + " " + eliteType.getTypeName() + " " + i + 15, eliteType));
    }

    PointsDistribution gobiDistribution = new PointsDistribution();
    gobiDistribution.setDegirity(2);
    gobiDistribution.setForce(2);
    gobiDistribution.setResistance(2);
    gobiDistribution.setConstitution(2);
    gobiDistribution.setInitiative(2);
    StudentType eliteType = new StudentType(StudentTypeName.MASTER_GOBI, gobiDistribution);
    this.students.add(new Student(name + " Master Gobi", eliteType));

    for (Iterator<Student> it = this.students.iterator(); it.hasNext();) {
      Student student = it.next();
      this.distributionMap.put(student.getName(), new PointsDistribution());
    }
  }

  public ArrayList<String> getDistributionErrors(Student student, PointsDistribution distribution) {
    ArrayList<String> errors = new ArrayList<>();

    if (distribution.getDegirity() < 0 || distribution.getDegirity() > 10) {
      errors.add("Degirity should be between 0 and 10");
    }

    if (distribution.getForce() < 0 || distribution.getForce() > 10) {
      errors.add("Force should be between 0 and 10");
    }

    if (distribution.getResistance() < 0 || distribution.getResistance() > 10) {
      errors.add("Resistance should be between 0 and 10");
    }

    if (distribution.getConstitution() < 0 || distribution.getConstitution() > 10) {
      errors.add("Constitution should be between 0 and 10");
    }

    if (distribution.getInitiative() < 0 || distribution.getInitiative() > 10) {
      errors.add("Initiative should be between 0 and 10");
    }

    return errors;
  }

  public void distributePoints(Student student, PointsDistribution distribution) throws Exception {
    ArrayList<String> errors = getDistributionErrors(student, distribution);

    if (errors.size() > 0) {
      throw new Exception("Points distribution is not valid for this student.");
    }

    String studentName = student.getName();
    PointsDistribution currentDistribution = this.distributionMap.get(studentName);
    this.distributionMap.put(studentName, distribution);
    this.pointsLeft = this.pointsLeft - distribution.calculateDiff(currentDistribution);
  }

  public String getName() {
    return this.name;
  }

  public Branch getBranch() {
    return this.branch;
  }

  public ArrayList<Student> getStudents() {
    return this.students;
  }

  public int getPoints() {
    return this.pointsLeft;
  }
}
