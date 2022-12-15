package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that contains the logic related to the player.
 */
public class Player {
  /**
   * String value representing name of the player.
   */
  private String name;

  /**
   * String value representing name of the branch.
   */
  private Branch branch;

  /**
   * List of the zones controlled by the player.
   */
  private ArrayList<Zone> controlledZones = new ArrayList<Zone>();

  /**
   * List of the students that the player has.
   */
  private ArrayList<Student> students = new ArrayList<Student>();

  /**
   * Points left to distribute.
   */
  private int pointsLeft = 400;

  /**
   * Constructor of class Player.
   * 
   * @param name Name of the player.
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * Initializes the player's students with default values.
   * 
   * @param namesToExclude Names excluded from the the list of available names.
   *                       This is useful to avoid the repetition of names with
   *                       the students of another player.
   */
  public void initializeStudents(List<String> namesToExclude) {
    NameGenerator nameGenerator = new NameGenerator();

    nameGenerator.excludeNames(namesToExclude);

    /*
     * We create first 15 students
     * with type Regular.
     */
    for (int i = 0; i < 15; i++) {
      PointsDistribution regularDistribution = new PointsDistribution();
      StudentType regularType = new StudentType(StudentTypeName.REGULAR, regularDistribution);
      this.students.add(new Student(this, nameGenerator.generateName(), regularType));
    }

    /*
     * We create 5 students
     * with type Elite.
     */
    for (int i = 0; i < 4; i++) {
      PointsDistribution eliteDistribution = new PointsDistribution();
      eliteDistribution.setDegirity(1);
      eliteDistribution.setForce(1);
      eliteDistribution.setResistance(1);
      eliteDistribution.setConstitution(1);
      eliteDistribution.setInitiative(1);
      StudentType eliteType = new StudentType(StudentTypeName.ELITE, eliteDistribution);
      this.students.add(new Student(this, nameGenerator.generateName(), eliteType));
    }

    /*
     * Finally, we create the last student
     * with type Master Gobi.
     */
    PointsDistribution gobiDistribution = new PointsDistribution();
    gobiDistribution.setDegirity(2);
    gobiDistribution.setForce(2);
    gobiDistribution.setResistance(2);
    gobiDistribution.setConstitution(2);
    gobiDistribution.setInitiative(2);
    StudentType eliteType = new StudentType(StudentTypeName.MASTER_GOBI, gobiDistribution);
    this.students.add(new Student(this, "Master " + nameGenerator.generateName(), eliteType));
  }

  /**
   * Initializes the player's students with default values.
   * Overloads method
   * <code>public void initializeStudents(String[] namesToExclude)</code>
   * and delegates the work to it.
   */
  public void initializeStudents() {
    this.initializeStudents(new ArrayList<String>());
  }

  /**
   * Checks if a points distribution is valid.
   * Validation rules depend on the constraints of each
   * of the characteristics and the player's points left.
   * 
   * If there are validation errors, then it returns
   * list of string values representing error messages.
   * 
   * @param student      Student that is target of distribution
   * @param distribution Points distribution
   * @return List of string values representing error messages
   */
  public ArrayList<String> getDistributionErrors(Student student, PointsDistribution distribution) {
    ArrayList<String> errors = new ArrayList<>();

    if (distribution.getDegirity() < 0 || distribution.getDegirity() > 10) {
      errors.add("Degirity should be between 0 and 10.");
    }

    if (distribution.getForce() < 0 || distribution.getForce() > 10) {
      errors.add("Force should be between 0 and 10.");
    }

    if (distribution.getResistance() < 0 || distribution.getResistance() > 10) {
      errors.add("Resistance should be between 0 and 10.");
    }

    if (distribution.getConstitution() < 0 || distribution.getConstitution() > 10) {
      errors.add("Constitution should be between 0 and 10.");
    }

    if (distribution.getInitiative() < 0 || distribution.getInitiative() > 10) {
      errors.add("Initiative should be between 0 and 10.");
    }

    PointsDistribution currentDistribution = student.getDistribution();

    int nextPointsLeft = this.pointsLeft - distribution.calculateDiff(currentDistribution);

    if (nextPointsLeft < 0) {
      errors.add("There are not enough points for this distribution.");
    }

    return errors;
  }

  /**
   * Distributes points for a student.
   * It throwns exception if the points
   * distribution is not valid.
   * 
   * @param student      Student that is target of distribution
   * @param distribution Points distribution
   * @throws Exception
   */
  public void distributePoints(Student student, PointsDistribution distribution) throws Exception {
    ArrayList<String> errors = getDistributionErrors(student, distribution);

    if (errors.size() > 0) {
      throw new Exception("Points distribution is not valid for this student (" + student.getName() + ").");
    }

    PointsDistribution currentDistribution = student.getDistribution();

    this.pointsLeft = this.pointsLeft - distribution.calculateDiff(currentDistribution);
    student.setDistribution(distribution);
  }

  /**
   * Extracts reservists from all the player's students.
   * 
   * @return List of reservist students
   */
  public ArrayList<Student> getReservists() {
    List<Student> filteredList = students
        .stream()
        .filter(student -> student.getIsReservist())
        .collect(Collectors.toList());

    ArrayList<Student> reservists = new ArrayList<Student>();
    reservists.addAll(filteredList);

    return reservists;
  }

  /**
   * Validates change of isReservist flag.
   * Returns true if change is valid.
   * 
   * According to requirements maximum
   * amount of reservists is 5.
   * 
   * @param student Target student
   * @param value   Value of isReservist flag
   * @return Boolean value representing that change is valid
   */
  public boolean validateIsReservistChange(Student student, boolean value) {
    ArrayList<Student> reservists = this.getReservists();
    reservists.remove(student);

    int nextReservistsAmount = reservists.size();

    if (value) {
      nextReservistsAmount++;
    }

    return nextReservistsAmount <= 5;
  }

  /**
   * Change isReservist flag for a student.
   * It throws exception if the change is not valid.
   * 
   * @param student Target student
   * @param value   Value of isReservist flag
   * @throws Exception
   */
  public void changeIsReservist(Student student, boolean value) throws Exception {
    if (!validateIsReservistChange(student, value)) {
      StringBuffer sb = new StringBuffer();
      sb.append("Flag isReservist cannot be set to ");
      sb.append(value);
      sb.append(" for the student (");
      sb.append(student.getName());
      sb.append(").");
      throw new Exception(sb.toString());
    }

    student.setIsReservist(value);
  }

  /**
   * Name getter.
   * 
   * @return String value representing name of the player.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Branch getter.
   * 
   * @return String value representing branch name of the player.
   */
  public Branch getBranch() {
    return this.branch;
  }

  /**
   * Students getter.
   * 
   * @return List of the player's students.
   */
  public ArrayList<Student> getStudents() {
    return this.students;
  }

  /**
   * Points left getter.
   * 
   * @return Number value of points left to distribute.
   */
  public int getPointsLeft() {
    return this.pointsLeft;
  }
}
