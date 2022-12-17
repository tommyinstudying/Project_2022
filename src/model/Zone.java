
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import helpers.Color;

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
   * The winning player in the zone.
   * It can be updated after a fight in the zone.
   */
  private Player winner = null;

  /**
   * Sorts students in zone
   * by initiative in ascending order.
   * This is to ensure that the list is ready
   * to be used for the fight.
   */
  private void sortStudents() {
    Collections.sort(this.students, new Comparator<Student>() {
      public int compare(Student s1, Student s2) {
        Integer initiative1 = s1.getDistribution().getInitiative();
        Integer initiative2 = s2.getDistribution().getInitiative();
        return initiative1.compareTo(initiative2) * -1;
      }
    });
  }

  /**
   * Finds the winning player in the zone.
   * The player wins in case all the remaining
   * students belong to them. Returns null
   * if there is no winner.
   * 
   * @return The winning player in the zone
   */
  private Player findWinner() {
    Player winner = null;

    for (Student student : this.students) {
      if (!student.isOutOfGame()) {
        Player player = student.getPlayer();

        if (winner == null) {
          winner = student.getPlayer();
          continue;
        }

        if (winner != player) {
          return null;
        }
      }
    }

    return winner;
  }

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
    sortStudents();
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

  /**
   * Makes a fight in the zone.
   * It returns <code>true</code> if
   * the fight is finished after the method
   * is called, otherwise it returns <code>false</code>.
   * 
   * @return Boolean value representing that the fight
   *         is finished after the method is called
   */
  public boolean fight() {
    if (this.winner != null) {
      return false;
    }

    for (Student student : this.students) {
      if (student.isOutOfGame()) {
        continue;
      }

      student.makeTurn(students);
    }

    Player winner = this.findWinner();
    this.winner = winner;

    if (winner != null) {
      ArrayList<Zone> winnerControlledZones = winner.getControlledZones();
      winnerControlledZones.add(this);
      return true;
    }

    return false;
  }

  /**
   * Get sum of students' ECTS (health points)
   * within the zone for a particular player.
   * 
   * @param player Player that has students to extract ECTS for
   * @return Students' ECTS
   */
  public int getEcts(Player player) {
    return students.stream().reduce(
        0,
        (acc, student) -> acc + (student.getPlayer() == player ? Math.max(student.getEcts(), 0) : 0),
        Integer::sum);
  }

  /**
   * Winner getter.
   * 
   * @return Player that is the winner in the zone
   */
  public Player getWinner() {
    return this.winner;
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

    sb.append("Zone: ");
    sb.append(this.name);
    if (this.winner != null) {
      sb.append(" ");
      sb.append(Color.CYAN_UNDERLINED);
      sb.append("controlled by ");
      sb.append(this.winner.getName());
      sb.append(Color.RESET);
    }
    sb.append("\n");

    for (Student student : this.students) {
      Player player = student.getPlayer();
      sb.append(Color.CYAN_BRIGHT);
      sb.append(player.getName());
      sb.append(Color.RESET);
      sb.append(" ");

      sb.append(Color.MAGENTA_BRIGHT);
      sb.append(student.getStrategy());
      sb.append(Color.RESET);
      sb.append(" ");

      sb.append(student.getName());
      sb.append(" ");

      if (student.getEcts() > 0) {
        sb.append(Color.GREEN_BRIGHT);
        sb.append(student.getEcts());
        sb.append(" ECTS");
        sb.append(Color.RESET);
      }

      if (student.getEcts() <= 0) {
        sb.append(Color.RED_BRIGHT);
        sb.append("out of game");
        sb.append(Color.RESET);
      }

      sb.append("\n");
    }

    return sb.toString();
  }
}
