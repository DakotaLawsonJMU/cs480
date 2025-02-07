package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import text.WordFinder;

/**
 * Completing Document Class which serves as the model component in a model view controller design.
 */
public class CompletingDocument extends PlainDocument {
  private static final long serialVersionUID = 1L;
  private CompletingField field;
  WordFinder finder;

  /**
   * Constructor for the CompletingDocument class.

   * @param field
   *          the CompletingField associated with this model
   */
  public CompletingDocument(CompletingField field) {
    this.field = field;
  }

  /**
   * Overloads the insertString method from PlainDocument, checks to see if there exists a known
   * auto-completion and inserts it into the model if it exists.
   */
  public void insertString(int offset, String s, AttributeSet as) throws BadLocationException {
    if (finder == null) {
      super.insertString(offset, s, as);
      return;
    }

    // Get current text and construct what the user is typing
    String currentText = getText(0, getLength());
    String newText = new StringBuilder(currentText).insert(offset, s).toString();

    // Find a word that starts with the new input
    String result = finder.find(newText);
    if (result != null) {
      // Replace the entire text with the suggested word
      super.remove(0, getLength());
      super.insertString(0, result, as);

      // Highlight the auto-filled part
      field.setSelectionStart(newText.length());
      field.setSelectionEnd(result.length());
    } else {
      // If no match found, insert as normal
      super.insertString(offset, s, as);
    }
  }

  public void setWordList(String fileName) {
    finder = new WordFinder(fileName);
  }
}
