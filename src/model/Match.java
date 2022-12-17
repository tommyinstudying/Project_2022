package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that contains high level game logic.
 * It incorporates all the necessary API to
 * interact with the core of the game from client code.
 */
public class Match {
  /**
   * List of all the zones that exist in the game.
   * Potentially it can contain any number of Zone objects,
   * but by requirements there are 5 predefined zones.
   */
  private ArrayList<Zone> zones = new ArrayList<Zone>();

  /**
   * List of all the players that exist in the game.
   * Potentially it can contain any number of Player objects,
   * but by requirements there are only 2 players.
   */
  private ArrayList<Player> players = new ArrayList<Player>();

  /**
   * Number of the previous round.
   * Initially it is 0.
   */
  private int previousRound = 0;

  /**
   * The winning player in the match.
   * It can be updated in the end of a round.
   */
  private Player winner = null;

  /**
   * Finds the winner of the match.
   * It returns null if there is
   * no winner.
   * 
   * @return The winning player in the match
   */
  private Player findWinner() {
    for (Player player : this.players) {
      if (player.getControlledZones().size() >= 3) {
        return player;
      }
    }

    return null;
  }

  /**
   * Constructor of class Match.
   */
  public Match() {
    Zone[] zoneArray = {
        new Zone("The Library"),
        new Zone("The Student Office"),
        new Zone("The Administrative Quarter"),
        new Zone("The Industrial Halls"),
        new Zone("The Sports Hall")
    };

    this.zones.addAll(Arrays.asList(zoneArray));

    Player p1 = new Player("Player 1");

    p1.initializeStudents();

    Player p2 = new Player("Player 2");

    List<String> namesToExclude = p1.getStudents()
        .stream().map(student -> student.getName())
        .collect(Collectors.toList());

    p2.initializeStudents(namesToExclude);

    this.players.add(p1);
    this.players.add(p2);
  }

  /**
   * Makes a fight in all the uncontrolled zones.
   * Fight is being performed until any of involved zones is controlled.
   */
  public void fight() {
    if (this.winner != null) {
      return;
    }

    boolean isFinished = false;

    while (!isFinished) {
      for (Zone zone : this.zones) {

        isFinished = zone.fight();

        if (isFinished) {
          break;
        }
      }
    }

    Player winner = this.findWinner();
    this.winner = winner;

    if (winner == null) {
      this.previousRound++;
    }
  }

  /**
   * Extracts all the zones that exist in the game.
   * It can be useful when it is needed to access zones from client code.
   * 
   * @return List of all the zones that exist in the game.
   */
  public ArrayList<Zone> getZones() {
    return this.zones;
  }

  /**
   * Extracts all the players that exist in the game.
   * It can be useful when it is needed to access players from client code.
   * 
   * @return List of all the players that exist in the game.
   */
  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  /**
   * Previous round getter.
   * 
   * @return Number of the previous round
   */
  public int getPreviousRound() {
    return this.previousRound;
  }

  /**
   * Winner getter.
   * 
   * @return Player that is the winner of the match
   */
  public Player getWinner() {
    return this.winner;
  }
}
