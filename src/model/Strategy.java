package model;

import java.util.List;

/**
 * Interface that represents the student's strategy which
 * influences on how the student acts during a turn.
 * To solve the student's strategy problem, pattern Strategy is implemented.
 * Therefore, <code>makeTurn</code> is a common operation
 * to all the algorithms provided by student strategies.
 */
public interface Strategy {
  /**
   * Algorighm of making a turn by the student.
   * 
   * @param actor       Student that makes a turn
   * @param studentList List of all the students that
   *                    potentially can be a target
   */
  public void makeTurn(Student actor, List<Student> studentList);
}
