
package model;

import java.util.ArrayList;

public class Zone {
  private String name;
  private ArrayList<Student> students = new ArrayList<Student>();

  Zone(String name) {
    this.name = name;
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public void removeStudent(Student student) {
    students.remove(student);
  }

  public void relocateStudent(Student student, Zone destination) {
    this.removeStudent(student);
    destination.addStudent(student);
  }

  public int getEcts() {
    return students.stream().reduce(0, (acc, student) -> acc + student.getEcts(), Integer::sum);
  }
}
