import java.util.ArrayList;

import model.Match;
import model.Player;
import model.Student;
import model.PointsDistribution;
import model.Zone;

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

      System.out.println("\n--- Create match ---\n");
      Match match = new Match();
      System.out.println("Match created");

      /*
       * Distribute points
       */

      System.out.println("\n--- Distribute points ---\n");

      ArrayList<Player> players = match.getPlayers();

      Player player1 = players.get(0);
      this.distributePoints(player1, distMatrix1);
      System.out.println("Points left (P1): " + player1.getPointsLeft());
      this.showDistribution(player1);
      System.out.println();

      Player player2 = players.get(1);
      this.distributePoints(player2, distMatrix2);
      System.out.println("Points left (P2): " + player2.getPointsLeft());
      this.showDistribution(player1);
      System.out.println();

      System.out.println("Points distributed");

      /*
       * Choose reservists
       */

      System.out.println("\n--- Choose reservists ---\n");

      chooseReservists(player1);
      System.out.println("Reservists amount (P1): " + player1.getReservists().size());
      System.out.println();

      chooseReservists(player2);
      System.out.println("Reservists amount (P2): " + player2.getReservists().size());
      System.out.println();

      System.out.println("Reservists chosen");

      /*
       * Distribute students
       */

      distributeStudents(match, player1);
      distributeStudents(match, player2);

      for (Zone zone : match.getZones()) {
        System.out.println(zone);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
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

    zone1.addStudent(students.get(1));
    zone1.addStudent(students.get(3));
    zone1.addStudent(students.get(5));

    zone2.addStudent(students.get(7));
    zone2.addStudent(students.get(9));
    zone2.addStudent(students.get(10));

    zone3.addStudent(students.get(11));
    zone3.addStudent(students.get(12));
    zone3.addStudent(students.get(13));

    zone4.addStudent(students.get(14));
    zone4.addStudent(students.get(15));
    zone4.addStudent(students.get(16));

    zone5.addStudent(students.get(17));
    zone5.addStudent(students.get(18));
    zone5.addStudent(students.get(19));
  }
}
