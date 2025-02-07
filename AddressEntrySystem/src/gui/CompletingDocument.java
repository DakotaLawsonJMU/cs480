package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import text.WordFinder;

/**
 * Completing Document Class which serves as the model component in a model view controller design.
 */
public class CompletingDocument extends PlainDocument
{
  private static final long serialVersionUID = 1L;
  private CompletingField field;
  private WordFinder finder;

  /**
   * Constructor for the CompletingDocument class.
   * 
   * @param field
   *          the CompletingField associated with this model
   */
  public CompletingDocument(final CompletingField field)
  {
    this.field = field;
  }

  /**
   * Overloads the insertString method from PlainDocument, checks to see if there exists a known
   * auto-completion and inserts it into the model if it exists.
   * 
   * @param offset
   *          the offset into the model to insert the string
   * @param s
   *          the string to insert
   * @param as
   *          the attributes for the inserted content
   */
  public void insertString(final int offset, final String s, final AttributeSet as)
      throws BadLocationException
  {
    if (finder == null)
    {
      super.insertString(offset, s, as);
      return;
    }

    // Get current text and construct what the user is typing
    String currentText = getText(0, getLength());
    String newText = new StringBuilder(currentText).insert(offset, s).toString();

    // Find a word that starts with the new input
    String result = finder.find(newText);
    if (result != null)
    {
      // Replace the entire text with the suggested word
      super.remove(0, getLength());
      super.insertString(0, result, as);

      // Highlight the auto-filled part
      field.setSelectionStart(newText.length());
      field.setSelectionEnd(result.length());
    }
    else
    {
      // If no match found, insert as normal
      super.insertString(offset, s, as);
    }
  }

  /**
   * Creates a finder and sets the finder attribute.
   * 
   * @param fileName
   *          the file that includes the completion strings
   */
  public void setWordList(final String fileName)
  {
    finder = new WordFinder(fileName);
  }
}
