package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.DigitizerPanel;

class DigitizerPanelTest
{
  private DigitizerPanel panel;
  private BufferedImage mockImage;
  private BufferedImage canvas;
  private Graphics2D g2d;

  @BeforeEach
  void setUp()
  {
    mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    panel = new DigitizerPanel(mockImage);
    canvas = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    g2d = canvas.createGraphics();
  }

  @Test
  void testLineDrawingAndDeleting()
  {
    panel.setMode(DigitizerPanel.ADD);

    // Simulate mouse press at (10,10)
    panel.mousePressed(new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
        0, 10, 10, 1, false));

    // Simulate mouse dragging at (25,25)
    panel.mouseDragged(new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(),
        0, 25, 25, 1, false));

    // Simulate mouse release at (50,50)
    panel.mouseReleased(new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(),
        0, 50, 50, 1, false));

    // Ensure a line has been added
    assertFalse(panel.getLines().isEmpty());

    // Change mode to delete
    panel.setMode(DigitizerPanel.DELETE);

    // Simulate mouse press at (10,10)
    panel.mousePressed(new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
        0, 10, 10, 1, false));

    // Simulate mouse dragging at (25,25)
    panel.mouseDragged(new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(),
        0, 25, 25, 1, false));

    // Simulate mouse release at (50,50)
    panel.mouseReleased(new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(),
        0, 50, 50, 1, false));

    // Ensure no line has been removed
    assertFalse(panel.getLines().isEmpty());

    // Simulate mouse press and release at (10,10)
    panel.mousePressed(new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
        0, 10, 10, 1, false));
    panel.mouseReleased(new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(),
        0, 10, 10, 1, false));

    assertTrue(panel.getLines().isEmpty());
  }

  @Test
  void testPaintRendersLines()
  {
    // Simulate drawing a line
    panel.setMode(DigitizerPanel.ADD);
    panel.mousePressed(new java.awt.event.MouseEvent(panel, java.awt.event.MouseEvent.MOUSE_PRESSED,
        System.currentTimeMillis(), 0, 10, 10, 1, false));
    panel.mouseReleased(new java.awt.event.MouseEvent(panel,
        java.awt.event.MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false));
    panel.mousePressed(new java.awt.event.MouseEvent(panel, java.awt.event.MouseEvent.MOUSE_PRESSED,
        System.currentTimeMillis(), 0, 15, 15, 1, false));
    panel.mouseDragged(new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(),
        0, 30, 30, 1, false));
    // Call the paint method with our Graphics2D object
    panel.paint(g2d);

    // Check if something was drawn on the canvas
    boolean hasGreen = false;
    for (int x = 10; x <= 50; x++)
    {
      for (int y = 10; y <= 50; y++)
      {
        if (new Color(canvas.getRGB(x, y)).equals(Color.GREEN))
        {
          hasGreen = true;
          break;
        }
      }
    }

    assertTrue(hasGreen, "Expected a green line to be drawn.");


    // Check if something was drawn on the canvas
    boolean hasYellow = false;
    for (int x = 15; x <= 30; x++)
    {
      for (int y = 15; y <= 30; y++)
      {
        if (new Color(canvas.getRGB(x, y)).equals(Color.YELLOW))
        {
          hasYellow = true;
          break;
        }
      }
    }

    assertTrue(hasYellow, "Expected a yellow line to be drawn.");
  }

  @Test
  void testMethodsThatDoNothing()
  {
    panel.mouseMoved(null);
    panel.mouseClicked(null);
    panel.mouseEntered(null);
    panel.mouseExited(null);
  }
}
