package model;

import java.util.ArrayList;
import java.util.Arrays;

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
    Player p2 = new Player("Player 2");

    this.players.add(p1);
    this.players.add(p2);
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
}

/*
 * TO DO:
 * 1. Initialize match
 * 2. Create students and distribute points
 * 3. Choose reservists
 * 4. Allocate them in zones
 * 
 */