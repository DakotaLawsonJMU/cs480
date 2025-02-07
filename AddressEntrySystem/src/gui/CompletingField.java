package gui;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * CompletingField class that serves as the view and controller part of the controller view model
 * design pattern.
 */
public class CompletingField extends JTextField
{
  private static final long serialVersionUID = 1L;
  private CompletingDocument document;

  /**
   * Creates a CompletingDocument as its default model as opposed to a PlainDocument.
   * 
   * @return returns the created model
   */
  public Document createDefaultModel()
  {
    document = new CompletingDocument(this);
    return document;
  }

  /**
   * Sets the wordList of the model the file who's name is provided.
   * 
   * @param filename
   *          the name of the file to be loaded as the word list
   */
  public void setWordList(final String filename)
  {
    document.setWordList(filename);
  }
}
