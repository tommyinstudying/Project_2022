package model;

import java.util.List;

/**
 * Class that represents the defensive strategy.
 * If the student owns this strategy, then during
 * their turn they heal the ally with the least ECTS
 * which is selected from the list of potential targets.
 * 
 * Class that represents the random strategy.
 * If the students owns this strategy, then during
 * their turn they act according to one of two strategies:
 * the offensive strategy or the defensive strategy.
 * The choice of strategy is random.
 */
public class RandomStrategy implements Strategy {
  /**
   * The offensive strategy which is potentially applicable.
   */
  private OffensiveStrategy offensiveStrategy = new OffensiveStrategy();

  /**
   * The defensive strategy which is potentially applicable.
   */
  private DefensiveStrategy defensiveStrategy = new DefensiveStrategy();

  @Override
  public void makeTurn(Student actor, List<Student> studentList) {
    double random = Math.random();

    if (random >= 0.5) {
      this.offensiveStrategy.makeTurn(actor, studentList);
      return;
    }

    this.defensiveStrategy.makeTurn(actor, studentList);
  }

  @Override
  public String toString() {
    return "R";
  }
}
