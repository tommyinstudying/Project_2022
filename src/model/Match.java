package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Match {
  private ArrayList<Zone> zones;
  private ArrayList<Player> players;

  Match() {
    Zone[] zoneArray = {
        new Zone("The Library"),
        new Zone("The Student Office"),
        new Zone("The Administrative Quarter"),
        new Zone("The Industrial Halls"),
        new Zone("The Sports Hall")
    };

    this.zones.addAll(Arrays.asList(zoneArray));
  }

  public void distributePoints() {

  }

  public void addPlayer(Player player) throws Exception {
    if (players.size() == 2) {
      throw new Exception("Only 2 players are allowed");
    }

    this.players.add(player);
  }
}

/*
 * 
 * 1. Initialize match
 * 2. Create students and distribute points
 * 3. Choose reservists
 * 4. Allocate them in zones
 * 
 * 
 */