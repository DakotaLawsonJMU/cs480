package math;

/**
 * Utility class that can be used to perform operations on 2D vectors.
 */
public class Vector
{
  /**
   * Since all the methods are static, this does nothing I guess?
   */
  private Vector()
  {
  }

  /**
   * Takes the dot product of two 2D vectors.
   * 
   * @param v
   *          first 2D vector
   * @param w
   *          second 2D vector
   * @return the dot product of vectors v and w (v ・ w)
   */
  public static double dot(final double[] v, final double[] w)
  {
    return v[0] * w[0] + v[1] * w[1];
  }

  /**
   * Subtracts two 2D vectors.
   * 
   * @param v
   *          first 2D vector
   * @param w
   *          second 2D vector
   * @return the result of subtracting vectors v and w (v - w)
   */
  public static double[] minus(final double[] v, final double[] w)
  {
    return new double[] {v[0] - w[0], v[1] - w[1]};
  }

  /**
   * Calculates the norm of a 2D vector.
   * 
   * @param v
   *          2D vector
   * @return the norm of v (||v||)
   */
  public static double norm(final double[] v)
  {
    return Math.sqrt(dot(v, v));
  }

  /**
   * Normalizes a 2D vector.
   * 
   * @param v
   *          2D vector
   * @return the normalized vector of v (v / ||v||)
   */
  public static double[] normalize(final double[] v)
  {
    return times(1 / norm(v), v);
  }

  /**
   * Finds the perpendicular vector of the given vector.
   * 
   * @param v
   *          2D vector
   * @return the perpendicular vector of v
   */
  public static double[] perp(final double[] v)
  {
    return new double[] {-v[1], v[0]};
  }

  /**
   * Adds two 2D vectors.
   * 
   * @param v
   *          first 2D vector
   * @param w
   *          second 2D vector
   * @return the result of adding vectors v and w (v + w)
   */
  public static double[] plus(final double[] v, final double[] w)
  {
    return new double[] {v[0] + w[0], v[1] + w[1]};
  }

  /**
   * Multiplies a 2D vector by a scalar.
   * 
   * @param s
   *          scalar
   * @param v
   *          2D vector
   * @return the result of the vector v multiplied by the scalar s (s * v)
   */
  public static double[] times(final double s, final double[] v)
  {
    return new double[] {s * v[0], s * v[1]};
  }

  /**
   * Multiplies a 2D vector by a scalar.
   * 
   * @param v
   *          2D vector
   * @param s
   *          scalar
   * @return the result of the vector v multiplied by the scalar s (s * v)
   */
  public static double[] times(final double[] v, final double s)
  {
    return times(s, v);
  }
}
