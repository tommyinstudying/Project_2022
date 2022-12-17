package model;

import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents the offensive strategy.
 * If the student owns this strategy, then during
 * their turn they attack the enemy with the least ECTS
 * which is selected from the list of potential targets.
 */
public class OffensiveStrategy implements Strategy {
  /**
   * Finds a target enemy of attack from
   * the list of all the potential targets.
   * 
   * @param actor       Student that makes a turn
   * @param studentList List of all the students that
   *                    potentially can be a target
   * @return Target of attack
   */
  private Student findTarget(Student actor, List<Student> studentList) {
    Player playerOfActor = actor.getPlayer();

    List<Student> enemies = studentList.stream()
        .filter(student -> !student.isOutOfGame() && (student.getPlayer() != playerOfActor))
        .collect(Collectors.toList());

    if (enemies.size() == 0) {
      return null;
    }

    Student target = Collections.min(enemies, new Comparator<Student>() {
      public int compare(Student s1, Student s2) {
        return ((Integer) s1.getEcts()).compareTo((Integer) s2.getEcts());
      }
    });

    return target;
  }

  /**
   * Checks if attack is successful.
   * If it returns <code>false</code>,
   * then the actor student does nothing.
   * 
   * @param actor Student that makes a turn
   * @return Boolean value representing whether attack is successful
   */
  private boolean checkAttackIsSuccessful(Student actor) {
    PointsDistribution actorDistribution = actor.getDistribution();

    Random rand = new Random();

    int randomNumber = rand.nextInt(101);

    int successUpperBound = 40 + actorDistribution.getDegirity() * 3;

    boolean attackIsSuccessful = randomNumber >= 0 && randomNumber <= successUpperBound;

    return attackIsSuccessful;
  }

  /**
   * Calculates the amount of ECTS representing
   * the damage that the actor student does to
   * the target student depending on their characteristics.
   * 
   * @param target Student that is the target of healing
   * @return Amount of ECTS that as result of healing
   */
  private int calculateDamage(Student actor, Student target) {
    PointsDistribution actorDistribution = actor.getDistribution();
    PointsDistribution targetDistribution = target.getDistribution();

    Random rand = new Random();

    float y = rand.nextFloat();
    int damageCoefficient = Math.max(0,
        Math.min(100, 10 * actorDistribution.getForce() - 5 * targetDistribution.getResistance()));
    int baseDamage = 10;

    int resultDamage = (int) Math.floor(y * (1 + damageCoefficient) * baseDamage);

    return resultDamage;
  }

  @Override
  public void makeTurn(Student actor, List<Student> studentList) {
    Student target = this.findTarget(actor, studentList);

    if (target == null) {
      return;
    }

    boolean attackIsSuccessful = checkAttackIsSuccessful(actor);

    if (attackIsSuccessful) {
      int damage = calculateDamage(actor, target);
      target.setEcts(target.getEcts() - damage);
    }
  }

  @Override
  public String toString() {
    return "O";
  }
}
