package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.Document;

import org.junit.jupiter.api.Test;

import gui.CompletingField;

class CompletingFieldTest {

  @Test
  void testCreateModel() {
    CompletingField test = new CompletingField();
    Document doc = test.createDefaultModel();
    assertNotNull(doc);
  }

  @Test
  void testSetWordList() {
    // This should do nothing, but I don't know how to make sure that is true
    // I am just going to do this and if I see nothing in the error console then its good
    CompletingField test = new CompletingField();
    test.setWordList("test");

    // This should cause the CompletingDocument to construct a new finder and store it
    // I also don't know how to check that this happens
    // I'm doing the same thing except this should produce an error
    Document doc = test.createDefaultModel();
    test.setWordList("error");
    // The checking the error log appears to be working although I was mistaken the first one also
    // throws an error because it automatically calls createDefaultModel when the constructor is
    // called
  }
}
