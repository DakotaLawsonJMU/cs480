package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import text.WordFinder;

class WordFinderTest {

  @Test
  void testConstructorAndFinder() {
    // Test a file that doesn't exist
    WordFinder badFinder = new WordFinder("badfilename.bad");
    assertNull(badFinder.find("test"));

    // Test a good file
    WordFinder finder = new WordFinder("street-names.txt");

    // Simple
    assertEquals("647 State Route", finder.find("647 State Route"));
    assertEquals("Abandoned Railroad", finder.find("Abandoned Railroad"));
    assertEquals("Mary Virginia's", finder.find("Mary Virginia's"));

    // Completions
    assertEquals("Na Trang", finder.find("Na"));
    assertEquals("Wheatfield", finder.find("Wheatf"));
    assertEquals("Wheatgrain", finder.find("Wheatg"));
    assertEquals("Wheathusk", finder.find("Wheath"));
    assertEquals("Wheatlan", finder.find("Wheatl"));
    assertEquals("Wheatland", finder.find("Wheatland"));
    assertEquals("Wheatland Acres", finder.find("Wheatland A"));
    assertEquals("Wheatland Farms", finder.find("Wheatland F"));

    // Non-existant words
    assertNull(finder.find("This is not an address that exists"));
    assertNull(finder.find("asdfjkl;asdfj;kl"));
  }

}
