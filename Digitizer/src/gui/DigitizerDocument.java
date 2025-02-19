package gui;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * A DigitizingDocument is the model component (in the sense of the Model-View-Controller Pattern)
 * of the system.
 */
public interface DigitizerDocument
{
  /**
   * Adds a line to the model.
   * 
   * @param start
   *          the start point of the line to add
   * @param stop
   *          the stop point of the line to add
   */
  public void addLine(double[] start, double[] stop);

  /**
   * Finds the approximately closest line segment to the given point.
   * 
   * @param point
   *          the given point
   * @return the approximately closest line
   */
  public Line2D getClosest(double[] point);

  /**
   * Getter for the model.
   * 
   * @return the list of lines store in the model
   */
  public List<Line2D.Double> getLines();

  /**
   * Removes a line from the model.
   * 
   * @param line
   *          the line to remove from the model
   */
  public void removeLine(Line2D line);
}
