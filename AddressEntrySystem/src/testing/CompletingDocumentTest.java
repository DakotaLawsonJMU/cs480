package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.BadLocationException;

import org.junit.jupiter.api.Test;

import gui.CompletingDocument;
import gui.CompletingField;

class CompletingDocumentTest {

  @Test
  void testConstructor() {
    CompletingDocument document = new CompletingDocument(null);
    assertNotNull(document);
  }

  @Test
  void testInsert() {
    CompletingField field = new CompletingField();
    CompletingDocument document = new CompletingDocument(field);

    String testString = "ImNotRealStreetName";

    try {
      document.insertString(0, testString, null);
      assertEquals(testString, document.getText(0, testString.length()));
      document.remove(0, testString.length());
    }
    catch (BadLocationException e) {
      fail("insertion without finder failed");
    }

    document.setWordList("street-names.txt");

    try {
      document.insertString(0, testString, null);
      assertEquals(testString, document.getText(0, testString.length()));
      document.remove(0, testString.length());
    }
    catch (BadLocationException e) {
      fail("insertion without successful search failed");
    }

    String wheat = "Wheatfield";
    
    try {
      document.insertString(0, "Wheatf", null);
      assertEquals(wheat, document.getText(0, wheat.length()));
    }
    catch (BadLocationException e) {
      fail("insertion without successful search failed");
    }
  }
}
