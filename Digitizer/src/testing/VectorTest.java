package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.Vector;

class VectorTest
{

  @Test
  void testDot()
  {
    double[] v = {1, 2};
    double[] w = {4, 5};
    assertEquals(14.0, Vector.dot(v, w));
    assertEquals(14.0, Vector.dot(w, v));
    v = new double[] {491, 347};
    w = new double[] {345, 953};
    assertEquals(500086.0, Vector.dot(v, w));
    assertEquals(500086.0, Vector.dot(w, v));
    v = new double[] {65.92983, 44.83067};
    w = new double[] {23.47355, 33.29401};
    assertEquals(3040.1999362832, Vector.dot(v, w));
    assertEquals(3040.1999362832, Vector.dot(w, v));
  }

  @Test
  void testMinus()
  {
    double[] v = {1, 2};
    double[] w = {4, 5};
    double[] expectedResultForwards = {-3, -3};
    double[] expectedResultBackwards = {3, 3};
    assertArrayEquals(expectedResultForwards, Vector.minus(v, w));
    assertArrayEquals(expectedResultBackwards, Vector.minus(w, v));
    v = new double[] {65.92983, 44.83067};
    w = new double[] {23.47355, 33.29401};
    expectedResultForwards = new double[] {42.45626, 11.536};
    expectedResultBackwards = new double[] {-42.45626, -11.536};
    assertArrayEquals(expectedResultForwards, Vector.minus(v, w), .001);
    assertArrayEquals(expectedResultBackwards, Vector.minus(w, v), .001);
  }

  @Test
  void testNorm()
  {
    double[] v = {24, 12};
    assertEquals(26.832815729997476, Vector.norm(v), .001);
    v = new double[] {13, 7};
    assertEquals(14.764823060233401, Vector.norm(v), .001);
  }

  @Test
  void testNormalize()
  {
    double[] v = {14, 5};
    double[] expected = {0.0672673 * 14, 0.0672673 * 5};
    assertArrayEquals(expected, Vector.normalize(v), 0.001);
    v = new double[] {17.03975, 19.69533};
    expected = new double[] {0.654283, 0.75625};
    assertArrayEquals(expected, Vector.normalize(v), 0.001);
  }

  @Test
  void testPerp()
  {
    double[] v = {3, 19};
    double[] vPerp = {-19, 3};
    assertArrayEquals(vPerp, Vector.perp(v));
    v = new double[] {104.24, 43.817};
    vPerp = new double[] {-43.817, 104.24};
    assertArrayEquals(vPerp, Vector.perp(v));
  }

  @Test
  void testPlus()
  {
    double[] v = {1, 2};
    double[] w = {4, 5};
    double[] expectedResultForwards = {5, 7};
    double[] expectedResultBackwards = {5, 7};
    assertArrayEquals(expectedResultForwards, Vector.plus(v, w));
    assertArrayEquals(expectedResultBackwards, Vector.plus(w, v));
    v = new double[] {65.92983, 44.83067};
    w = new double[] {23.47355, 33.29401};
    expectedResultForwards = new double[] {89.40338, 78.12468};
    expectedResultBackwards = new double[] {89.40338, 78.12468};
    assertArrayEquals(expectedResultForwards, Vector.plus(v, w), .001);
    assertArrayEquals(expectedResultBackwards, Vector.plus(w, v), .001);
  }

  @Test
  void testTimes()
  {
    double s = 42;
    double[] v = {2, 45};
    double[] product = {84, 1890};
    assertArrayEquals(product, Vector.times(s, v), .001);
    assertArrayEquals(product, Vector.times(v, s), .001);
    s = 12.4523;
    v = new double[] {4.12, 8};
    product = new double[] {12.4523 * 4.12, 12.4523 * 8};
    assertArrayEquals(product, Vector.times(s, v), .001);
    assertArrayEquals(product, Vector.times(v, s), .001);
  }
}
