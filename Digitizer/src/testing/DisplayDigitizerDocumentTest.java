package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Line2D;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.DigitizerPanel;
import gui.DisplayDigitizerDocument;

class DisplayDigitizerDocumentTest {

    private DisplayDigitizerDocument document;
    private DigitizerPanel panel;

    @BeforeEach
    void setUp() {
        panel = new DigitizerPanel(null); // Passing null as BufferedImage isn't used in this test
        document = new DisplayDigitizerDocument(panel);
    }

    @Test
    void testAddLine() {
        double[] start = {10, 10};
        double[] stop = {50, 50};
        document.addLine(start, stop);
        
        List<Line2D.Double> lines = document.getLines();
        assertEquals(1, lines.size(), "Expected one line to be added.");
        assertEquals(10, lines.getFirst().getX1(), "Line should match the added one.");
        assertEquals(10, lines.getFirst().getY1(), "Line should match the added one.");
        assertEquals(50, lines.getFirst().getX2(), "Line should match the added one.");
        assertEquals(50, lines.getFirst().getY2(), "Line should match the added one.");
    }

    @Test
    void testGetClosest() {
        double[] start1 = {10, 10};
        double[] stop1 = {50, 50};
        document.addLine(start1, stop1);
        
        double[] start2 = {100, 100};
        double[] stop2 = {150, 150};
        document.addLine(start2, stop2);
        
        Line2D closest = document.getClosest(new double[]{15, 15});
        assertNotNull(closest, "Expected to find a closest line.");
        assertEquals(10, closest.getX1(), "Closest line should be the first one.");
        assertEquals(10, closest.getY1(), "Closest line should be the first one.");
        assertEquals(50, closest.getX2(), "Closest line should be the first one.");
        assertEquals(50, closest.getY2(), "Closest line should be the first one.");
    }

    @Test
    void testRemoveLine() {
        double[] start = {10, 10};
        double[] stop = {50, 50};
        document.addLine(start, stop);
        Line2D closest = document.getClosest(new double[]{15, 15});
        
        document.removeLine(closest);
        List<Line2D.Double> lines = document.getLines();
        assertEquals(0, lines.size(), "Expected line to be removed.");
    }

    @Test
    void testRemoveNonexistentLine() {
        double[] start = {10, 10};
        double[] stop = {50, 50};
        document.addLine(start, stop);
        
        double[] otherStart = {100, 100};
        double[] otherStop = {150, 150};
        Line2D.Double nonExistentLine = new Line2D.Double(otherStart[0], otherStart[1], otherStop[0], otherStop[1]);
        
        document.removeLine(nonExistentLine);
        List<Line2D.Double> lines = document.getLines();
        assertEquals(1, lines.size(), "Expected line list to remain unchanged.");
      }
}
