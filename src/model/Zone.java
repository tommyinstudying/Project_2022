
package model;

import java.util.ArrayList;

/**
 * Class that incorporates all
 * the logic related to the zone.
 */
public class Zone {
  /**
   * String that represents
   * name of the zone.
   */
  private String name;

  /**
   * List of all the students
   * that are located in the zone.
   */
  private ArrayList<Student> students = new ArrayList<Student>();

  /**
   * Constuctor of class Zone.
   * 
   * @param name Name of the zone
   */
  public Zone(String name) {
    this.name = name;
  }

  /**
   * Place a student to the zone.
   * 
   * @param student Student to place to the zone
   */
  public void addStudent(Student student) {
    students.add(student);
  }

  /**
   * Remove a student from the zone.
   * 
   * @param student Student to remote from the zone
   */
  public void removeStudent(Student student) {
    students.remove(student);
  }

  /**
   * Relocate a student from one zone to another.
   * It removes the student from the zone and
   * place them to the destination zone.
   * 
   * @param student     Student to relocate
   * @param destination Destination zone
   */
  public void relocateStudent(Student student, Zone destination) {
    this.removeStudent(student);
    destination.addStudent(student);
  }

  public void fight() {

  }

  /**
   * Get sum of students' ECTS (health points)
   * within the zone for a particular player.
   * 
   * @param player Player that has students to extract ECTS for
   * @return Students' ECTS
   */
  public int getEcts(Player player) {
    // TODO
    return students.stream().reduce(0, (acc, student) -> acc + student.getEcts(), Integer::sum);
  }

  /**
   * Name getter.
   * 
   * @return Name of the zone
   */
  public String getName() {
    return this.name;
  }

  /**
   * Students getter.
   * 
   * @return List of students within the zone
   */
  public ArrayList<Student> getStudents() {
    return this.students;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();

    sb.append("Zone - ");
    sb.append(this.name);
    sb.append("\n");

    for (Student student : this.students) {
      Player player = student.getPlayer();
      sb.append("P - ");
      sb.append(player.getName());
      sb.append(", S - ");
      sb.append(student.getName());
      sb.append(", ");
      sb.append(student.getEcts());
      sb.append(" ECTS");
      sb.append("\n");
    }

    return sb.toString();
  }
}
