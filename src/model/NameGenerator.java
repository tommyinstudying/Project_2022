package model;

import java.util.Random;
import java.util.ArrayDeque;
import java.util.List;

/**
 * Class for generating random names.
 * It is used for generating unique names for students.
 * Even though that the full list of the available names is predefined,
 * each new instance has its own (random) order in which the names are
 * generated.
 */
public class NameGenerator {
  /**
   * Name list that stored as double-ended queue.
   * When initialized it contains predefined string values
   * representing names in random order.
   */
  private ArrayDeque<String> nameList = new ArrayDeque<String>();

  /**
   * Constructor of class StudentNameGenerator.
   * Basically, it just sets initial value for nameList.
   * 
   */
  public NameGenerator() {
    String[] names = {
        "Tom", "Liam", "Oliver", "Elijah", "Lucas",
        "Amara", "Zion", "Maeve", "Mia", "Remi",
        "Ari", "Blake", "Axel", "Molly", "Alice",
        "Maverick", "Jesse", "Isha", "Mae", "Brian",
        "Leon", "Eloise", "Josephine", "Louis", "Yves",
        "Margot", "Sophie", "Beau", "Caroline", "Juliet",
        "Sylvie", "Harvey", "Charles", "Emmeline", "Elise",
        "Lucien", "Estelle", "Dion", "Delphine", "Harriet",
        "Rosalie", "Colette", "Kiki", "Remi", "Isabelle",
        "Soleil", "Elaine", "Bruce", "Nancy", "Esme",
    };

    Random rand = new Random();

    for (String name : names) {
      if (rand.nextDouble() >= 0.5) {
        nameList.offerFirst(name);
        continue;
      }

      nameList.offerLast(name);
    }
  }

  /**
   * Gets generated random name.
   * 
   * @return Generated name
   */
  public String generateName() {
    String name = this.nameList.pollFirst();
    this.nameList.offerLast(name);
    return name;
  }

  /**
   * Exclude certain names from the list of available.
   * 
   * @param namesToExclude Names to exlude
   */
  public void excludeNames(List<String> namesToExclude) {
    for (String name : namesToExclude) {
      this.nameList.remove(name);
    }
  }
}
