package text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * WordFinder class.
 */
public class WordFinder {
  // I would love to make this a tree, but that seems like making a lot of work out of something
  // that can be done simply with a list.
  private ArrayList<String> words;

  /**
   * Constructor for a word finder object.
   * 
   * @param fileName
   *          the word file to load into the word list.
   */
  public WordFinder(String fileName) {
    words = new ArrayList<String>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        words.add(line);
      }
    }
    catch (IOException e) {
      e.printStackTrace(); // Handle exceptions appropriately
    }

    Collections.sort(words);
  }

  /**
   * Finds the first word with the provided prefix.
   * 
   * @param prefix
   *          the beginning of a word.
   * @return the first word (alphabetically, in the list of valid words) that begins with the given
   *         prefix (or null if there is no such word).
   */
  public String find(String prefix) {
    // This takes O(n) time, however, if this was done with a tree this would be O(log n)
    for (String word : words) {
      if (word.startsWith(prefix))
        return word;
    }
    return null;
  }
}
