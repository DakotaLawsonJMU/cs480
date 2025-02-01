package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import text.WordFinder;

public class CompletingDocument extends PlainDocument
{
  private static final long serialVersionUID = 1L;
  private CompletingField field;
  WordFinder finder;

  public CompletingDocument(CompletingField field)
  {
    this.field = field;
  }

  public void insertString(int offset, String s, AttributeSet as) throws BadLocationException
  {

  }

  public void setWordList(String fileName)
  {

  }

}
