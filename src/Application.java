import java.util.ArrayList;

import helpers.Color;
import model.Match;
import model.Player;
import model.Student;
import model.PointsDistribution;
import model.Zone;
import model.RandomStrategy;
import model.OffensiveStrategy;
import model.DefensiveStrategy;

public class Application {
  public void run() {
    /*
     * ============= NOTE =============
     * 
     * Just an imitation of
     * the game flow in client code.
     * 
     * All the logic related to the game core
     * is placed in package "model".
     * 
     * ================================
     */

    try {
      /*
       * Create match
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- Create match ---\n" + Color.RESET);

      Match match = new Match();
      ArrayList<Player> players = match.getPlayers();
      players.get(0).setName("Player Dino");
      players.get(1).setName("Player Franco");

      System.out.println("Match created");

      /*
       * Distribute points
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- Distribute points ---\n" + Color.RESET);

      System.out.println(Color.CYAN_BRIGHT + players.get(0).getName() + ":" + Color.RESET);
      Player player1 = players.get(0);
      this.distributePoints(player1, distMatrix1);
      System.out.println("Points left: " + player1.getPointsLeft());
      this.showDistribution(player1);
      System.out.println();

      System.out.println(Color.CYAN_BRIGHT + players.get(1).getName() + ":" + Color.RESET);
      Player player2 = players.get(1);
      this.distributePoints(player2, distMatrix2);
      System.out.println("Points left: " + player2.getPointsLeft());
      this.showDistribution(player2);

      /*
       * Choose reservists
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- Choose reservists ---\n" + Color.RESET);

      System.out.println(Color.CYAN_BRIGHT + players.get(0).getName() + ":" + Color.RESET);
      chooseReservists(player1);
      ArrayList<Student> reservists1 = player1.getReservists();
      System.out.println("Reservists amount: " + reservists1.size());
      for (int i = 0; i < reservists1.size(); i++) {
        Student st = reservists1.get(i);
        System.out.print(st.getName());

        if (i + 1 != reservists1.size()) {
          System.out.print(", ");
        }
      }
      System.out.println("\n");

      System.out.println(Color.CYAN_BRIGHT + players.get(1).getName() + ":" + Color.RESET);
      chooseReservists(player2);
      ArrayList<Student> reservists2 = player2.getReservists();
      System.out.println("Reservists amount: " + reservists2.size());
      for (int i = 0; i < reservists2.size(); i++) {
        Student st = reservists2.get(i);
        System.out.print(st.getName());

        if (i + 1 != reservists2.size()) {
          System.out.print(", ");
        }
      }
      System.out.println();

      /*
       * Distribute students into zones
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- Distribute students into zones ---\n" + Color.RESET);

      distributeStudents(match, player1);
      distributeStudents(match, player2);

      for (Zone zone : match.getZones()) {
        System.out.println(zone);
      }

      /*
       * Play
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- Play ---\n" + Color.RESET);

      while (match.getWinner() == null) {
        /*
         * Distribute reservists after 2 round
         * in first found zone without winner.
         */
        if (match.getPreviousRound() == 2) {
          System.out.println(Color.YELLOW + "\n--- Distribute reservists ---\n" + Color.RESET);

          for (Zone zone : match.getZones()) {
            if (zone.getWinner() == null) {
              for (Player player : players) {
                for (Student reservist : player.getReservists()) {
                  reservist.setStrategy(new RandomStrategy());
                  zone.addStudent(reservist);
                }
              }

              break;
            }
          }

          for (Zone zone : match.getZones()) {
            System.out.println(zone);
          }
        }

        /*
         * Relocate students from controlled zones
         * to uncontrolled zones
         */
        if (match.getPreviousRound() != 0) {
          System.out.println(Color.YELLOW + "\n--- Relocate students from controlled zones ---\n" + Color.RESET);

          for (Zone zone : match.getZones()) {
            if (zone.getWinner() == null) {
              continue;
            }

            ArrayList<Student> zoneStudents = zone.getStudents();
            ArrayList<Student> studentsToRelocate = new ArrayList<Student>();

            for (int i = 0; i < zoneStudents.size(); i++) {
              if (zoneStudents.get(i).isOutOfGame()) {
                continue;
              }

              for (int j = i + 1; j < zoneStudents.size(); j++) {
                Student studentToRelocate = zoneStudents.get(j);

                if (studentToRelocate.isOutOfGame()) {
                  continue;
                }

                studentToRelocate.setStrategy(new RandomStrategy());
                studentsToRelocate.add(studentToRelocate);

              }
              break;
            }

            for (Zone destination : match.getZones()) {
              if (destination != zone && destination.getWinner() == null) {
                for (Student studentToRelocate : studentsToRelocate) {
                  zone.relocateStudent(studentToRelocate, destination);
                }
              }
            }
          }

          for (Zone zone : match.getZones()) {
            System.out.println(zone);
          }
        }

        System.out.println(Color.YELLOW + "\n--- Round " + (match.getPreviousRound() + 1) + " ---\n" + Color.RESET);

        match.fight();

        for (Zone zone : match.getZones()) {
          System.out.println(zone);
        }
      }

      /*
       * The end
       */

      System.out.println(Color.YELLOW_BOLD + "\n--- THE END ---\n" + Color.RESET);

      System.out.print("The winner is ");
      System.out.println(Color.CYAN_BRIGHT + match.getWinner().getName() + Color.RESET);
      System.out.println();
    } catch (Exception e) {
      System.out.println("Something went wrong...");
      System.out.println(e);
    }
  }

  final private int[][] distMatrix1 = {
      { 2, 2, 5, 2, 8 },
      { 4, 2, 4, 0, 2 },
      { 8, 2, 4, 9, 2 },
      { 4, 5, 10, 2, 5 },
      { 2, 2, 6, 6, 4 },
      { 4, 7, 2, 9, 1 },
      { 2, 1, 3, 4, 4 },
      { 1, 4, 2, 8, 6 },
      { 9, 9, 2, 1, 0 },
      { 2, 4, 2, 6, 2 },
      { 8, 7, 4, 5, 2 },
      { 2, 4, 2, 3, 8 },
      { 2, 3, 5, 3, 1 },
      { 2, 5, 7, 2, 2 },
      { 10, 4, 5, 2, 4 },
      { 2, 3, 4, 1, 10 },
      { 3, 5, 8, 1, 2 },
      { 10, 2, 1, 8, 8 },
      { 1, 6, 1, 1, 1 },
      { 5, 6, 0, 2, 10 }
  };

  final private int[][] distMatrix2 = {
      { 2, 3, 5, 10, 2 },
      { 4, 5, 2, 9, 5 },
      { 0, 2, 5, 3, 2, 8 },
      { 4, 4, 2, 5, 5 },
      { 2, 8, 5, 6, 7 },
      { 9, 1, 2, 2, 0 },
      { 4, 2, 1, 9, 0 },
      { 5, 4, 4, 9, 3 },
      { 3, 4, 5, 4, 3 },
      { 8, 3, 0, 10, 2 },
      { 2, 10, 4, 1, 4 },
      { 5, 2, 4, 4, 5 },
      { 3, 3, 3, 2, 5 },
      { 6, 2, 1, 9, 2 },
      { 7, 4, 5, 5, 2 },
      { 10, 4, 4, 0, 2 },
      { 4, 3, 4, 4, 5 },
      { 2, 2, 1, 10, 0 },
      { 4, 4, 5, 10, 2 },
      { 4, 4, 1, 5, 2 }
  };

  private void distributePoints(Player player, int[][] distMatrix) throws Exception {
    ArrayList<Student> students = player.getStudents();

    for (int i = 0; i < distMatrix.length; i++) {
      Student student = students.get(i);
      PointsDistribution dist = new PointsDistribution();
      int j = 0;
      dist.setDegirity(distMatrix[i][j++]);
      dist.setForce(distMatrix[i][j++]);
      dist.setResistance(distMatrix[i][j++]);
      dist.setConstitution(distMatrix[i][j++]);
      dist.setInitiative(distMatrix[i][j++]);
      player.distributePoints(student, dist);
    }
  }

  private void showDistribution(Player player) {
    ArrayList<Student> students = player.getStudents();

    for (Student student : students) {
      System.out.println(student.getDistribution());
    }
  }

  private void chooseReservists(Player player) throws Exception {
    ArrayList<Student> students = player.getStudents();

    Student r1 = students.get(0);
    player.changeIsReservist(r1, true);

    Student r2 = students.get(2);
    player.changeIsReservist(r2, true);

    Student r3 = students.get(4);
    player.changeIsReservist(r3, true);

    Student r4 = students.get(6);
    player.changeIsReservist(r4, true);

    Student r5 = students.get(8);
    player.changeIsReservist(r5, true);
  }

  private void distributeStudents(Match match, Player player) {
    ArrayList<Student> students = player.getStudents();
    ArrayList<Zone> zones = match.getZones();

    Zone zone1 = zones.get(0);
    Zone zone2 = zones.get(1);
    Zone zone3 = zones.get(2);
    Zone zone4 = zones.get(3);
    Zone zone5 = zones.get(4);

    students.get(1).setStrategy(new OffensiveStrategy());
    zone1.addStudent(students.get(1));
    students.get(3).setStrategy(new DefensiveStrategy());
    zone1.addStudent(students.get(3));
    students.get(5).setStrategy(new RandomStrategy());
    zone1.addStudent(students.get(5));

    students.get(7).setStrategy(new OffensiveStrategy());
    zone2.addStudent(students.get(7));
    students.get(9).setStrategy(new DefensiveStrategy());
    zone2.addStudent(students.get(9));
    students.get(10).setStrategy(new RandomStrategy());
    zone2.addStudent(students.get(10));

    students.get(11).setStrategy(new OffensiveStrategy());
    zone3.addStudent(students.get(11));
    students.get(12).setStrategy(new DefensiveStrategy());
    zone3.addStudent(students.get(12));
    students.get(13).setStrategy(new RandomStrategy());
    zone3.addStudent(students.get(13));

    students.get(14).setStrategy(new OffensiveStrategy());
    zone4.addStudent(students.get(14));
    students.get(15).setStrategy(new DefensiveStrategy());
    zone4.addStudent(students.get(15));
    students.get(16).setStrategy(new RandomStrategy());
    zone4.addStudent(students.get(16));

    students.get(17).setStrategy(new OffensiveStrategy());
    zone5.addStudent(students.get(17));
    students.get(18).setStrategy(new DefensiveStrategy());
    zone5.addStudent(students.get(18));
    students.get(19).setStrategy(new RandomStrategy());
    zone5.addStudent(students.get(19));
  }
}
