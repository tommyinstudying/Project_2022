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
      this.students.add(new Student(name + i, StudentType.REGULAR));
    }

    for (int i = 0; i < 4; i++) {
      this.students.add(new Student(name + i + 15, StudentType.ELITE));
    }

    this.students.add(new Student(name + "Gobi", StudentType.MASTER_GOBI));

    for (Iterator<Student> it = this.students.iterator(); it.hasNext();) {
      Student student = it.next();
      this.distributionMap.put(student.getName(), new PointsDistribution());
    }
  }

  public void distributePoints(Student student, PointsDistribution distribution) {
    PointsDistribution previousDistribution = this.distributionMap.get(student.getName());

    this.distributionMap.put(student.getName(), distribution);

    this.pointsLeft = this.pointsLeft - distribution.calculateDiff(previousDistribution);
  }

  public boolean validateDistribution(Student student, PointsDistribution distribution) {

  }

}
