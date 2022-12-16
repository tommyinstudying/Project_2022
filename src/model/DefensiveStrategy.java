package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class that represents the defensive strategy.
 * If the student owns this strategy, then during
 * their turn they heal the ally with the least ECTS
 * which is selected from the list of potential targets.
 */
public class DefensiveStrategy implements Strategy {
  /**
   * Finds a target ally of healing from
   * the list of all the potential targets.
   * 
   * @param actor       Student that makes a turn
   * @param studentList List of all the students that
   *                    potentially can be a target
   * @return Target of healing
   */
  private Student findTarget(Student actor, List<Student> studentList) {
    Player playerOfActor = actor.getPlayer();

    List<Student> allies = studentList.stream()
        .filter(student -> !student.isOutOfGame()
            && student != actor
            && student.getPlayer() == playerOfActor)
        .collect(Collectors.toList());

    System.out.println(allies.size());

    if (allies.size() == 0) {
      return null;
    }

    Student target = Collections.min(allies, new Comparator<Student>() {
      public int compare(Student s1, Student s2) {
        return ((Integer) s1.getEcts()).compareTo((Integer) s2.getEcts());
      }
    });

    return target;
  }

  /**
   * Checks if healing is successful.
   * If it returns <code>false</code>,
   * then the actor student does nothing.
   * 
   * @param actor Student that makes a turn
   * @return Boolean value representing whether healing is successful
   */
  private boolean checkHealingIsSuccessful(Student actor) {
    PointsDistribution actorDistribution = actor.getDistribution();

    Random rand = new Random();

    int randomNumber = rand.nextInt(101);

    int successUpperBound = 20 + actorDistribution.getDegirity() * 6;

    boolean attackIsSuccessful = randomNumber >= 0 && randomNumber <= successUpperBound;

    return attackIsSuccessful;
  }

  /**
   * Calculates the amount of ECTS
   * that the student heals depending on
   * characteristics of the target student.
   * 
   * @param target Student that is the target of healing
   * @return Amount of ECTS that as result of healing
   */
  private int calculateHeal(Student target) {
    PointsDistribution targetDistribution = target.getDistribution();

    Random rand = new Random();

    double y = rand.nextFloat() * 0.6;

    int targetConstitution = targetDistribution.getConstitution();

    int maxPossibleHeal = 30 + targetConstitution;

    int resultHeal = Math.min((int) Math.floor(y * (10 + targetConstitution)), maxPossibleHeal);

    return resultHeal;
  }

  @Override
  public void makeTurn(Student actor, List<Student> studentList) {
    Student target = this.findTarget(actor, studentList);

    if (target == null) {
      return;
    }

    boolean healingIsSuccessful = checkHealingIsSuccessful(actor);

    if (healingIsSuccessful) {
      int heal = calculateHeal(target);
      target.setEcts(target.getEcts() + heal);
    }
  }

  public void test(Student actor, Student target) {
    System.out.println("===");
    System.out.println("Actor: " + actor.getDistribution());
    System.out.println("Target: " + target.getDistribution());
    System.out.println("Heal: " + this.calculateHeal(target));
  }

  public void testFind(Student actor, List<Student> studentList) {
    // System.out.println("(D) Targets: " + );
    Student s = findTarget(actor, studentList);

    System.out.println(s);
  }
}
