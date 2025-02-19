package gui;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * DisplayDigitizerDocument is a DigitizerDocument that uses display coordinates.
 */
public class DisplayDigitizerDocument implements DigitizerDocument
{
  protected DigitizerPanel panel;
  protected List<Line2D.Double> lines;

  /**
   * Constructor for a DisplayDigitizerDocument.
   * 
   * @param panel
   *          the view and controller associated with this model
   */
  public DisplayDigitizerDocument(final DigitizerPanel panel)
  {
    this.panel = panel;
    this.lines = new ArrayList<>();
  }

  @Override
  public void addLine(final double[] start, final double[] stop)
  {
    Line2D.Double line = new Line2D.Double(start[0], start[1], stop[0], stop[1]);
    lines.add(line);
  }

  @Override
  public Line2D getClosest(final double[] point)
  {
    Line2D closestLine = null;
    double minDist = Double.MAX_VALUE;
    for (Line2D line : lines)
    {
      double distA = line.getP1().distance(point[0], point[1]);
      double distB = line.getP2().distance(point[0], point[1]);
      double lineMinDist = min(distA, distB);
      if (lineMinDist < minDist)
      {
        minDist = lineMinDist;
        closestLine = line;
      }
    }
    return closestLine;
  }

  @Override
  public List<Line2D.Double> getLines()
  {
    return lines;
  }

  @Override
  public void removeLine(final Line2D line)
  {
    for (Line2D l : lines)
    {
      if (l.equals(line))
      {
        lines.remove(l);
        break;
      }
    }
  }

  /**
   * Finds the minimum value of n doubles.
   * 
   * @param terms
   *          the doubles
   * @return the minimum value of the terms
   */
  private static double min(final double... terms)
  {
    double min = Double.MAX_VALUE;
    for (double i : terms)
    {
      if (i < min)
      {
        min = i;
      }
    }
    return min;
  }
}
