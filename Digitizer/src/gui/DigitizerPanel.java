package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 * The DigitizerPanel is the view and controller component (in the sense of the
 * Model-View-Controller Pattern).
 */
public class DigitizerPanel extends JPanel implements MouseListener, MouseMotionListener
{
  public static final int ADD = 0;
  public static final int DELETE = 1;

  private static final long serialVersionUID = 1L;

  private BufferedImage ortho;
  private DigitizerDocument model;
  private int mode;
  private Line2D currentLine;
  private double[] origin;
  private boolean wasDragged;

  /**
   * Constructor for a DigitizerPanel.
   * 
   * @param ortho
   *          the image to digitizeF
   */
  public DigitizerPanel(final BufferedImage ortho)
  {
    this.ortho = ortho;
    createDefaultModel();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }

  /**
   * Creates the default model for this DigitizerPanel.
   * 
   * @return the created model
   */
  public DigitizerDocument createDefaultModel()
  {
    this.model = new DisplayDigitizerDocument(this);
    return this.model;
  }

  /**
   * Gets the lines from the model.
   * 
   * @return the lines
   */
  public List<Line2D.Double> getLines()
  {
    return model.getLines();
  }

  /**
   * Prints the image and lines to the screen.
   * 
   * @param g
   *          the graphics element to be drawn to
   */
  public void paint(final Graphics g)
  {
    // Clear the component
    super.paint(g);

    // Draw the image
    g.drawImage(ortho, 0, 0, null);

    // Draw the lines in the model
    g.setColor(Color.GREEN);
    List<Line2D.Double> lines = getLines();
    for (Line2D line : lines)
    {
      g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
    }

    // Draw the line being drawn
    if (currentLine != null)
    {
      g.setColor(Color.YELLOW);
      g.drawLine((int) currentLine.getX1(), (int) currentLine.getY1(), (int) currentLine.getX2(),
          (int) currentLine.getY2());
    }
  }

  /**
   * Sets the mode of the panel to add or delete.
   * 
   * @param mode
   *          the mode (ADD or DELETE)
   */
  public void setMode(final int mode)
  {
    this.mode = mode;
  }

  @Override
  public void mouseDragged(final MouseEvent e)
  {
    wasDragged = true;
    if (mode == ADD)
    {
      currentLine = new Line2D.Double(origin[0], origin[1], e.getX(), e.getY());
      repaint();
    }
  }

  @Override
  public void mouseMoved(final MouseEvent e)
  {
    // Do nothing
  }

  @Override
  public void mouseClicked(final MouseEvent e)
  {
    // Do nothing
  }

  @Override
  public void mousePressed(final MouseEvent e)
  {
    origin = new double[] {e.getX(), e.getY()};
    wasDragged = false;
  }

  @Override
  public void mouseReleased(final MouseEvent e)
  {
    if (mode == DELETE)
    {
      if (!wasDragged)
      {
        Line2D lineToRemove = model.getClosest(origin);
        model.removeLine(lineToRemove);
      }
    }
    else
    {
      double[] end = {e.getX(), e.getY()};
      model.addLine(origin, end);
      currentLine = null;
    }
    wasDragged = false;
    repaint();
  }

  @Override
  public void mouseEntered(final MouseEvent e)
  {
    // Do nothing
  }

  @Override
  public void mouseExited(final MouseEvent e)
  {
    // Do nothing
  }
}
