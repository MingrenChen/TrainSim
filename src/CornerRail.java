
/*

The CornerRail class.  A CornerRail object has two ends,
which must be not be opposite each other.

*/

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>This is a corner rail</h1>
 * There are four different types of corner rail and
 * we can produce each corner rail in RailFactory class.
 */
class CornerRail extends TwoEndRail {

  /**
   * ARCANGLE.
   */
  private static final int ARCANGLE = 90;
  /**
   * The multipliers for the width and height.
   */
  private double x1, y1;
  /**
   * The start angle of the rail.
   */
  private int startAngle;
  /**
   * the type of rail depends on direction.
   */
  private String rType;

  /**
   * contributor to produce a corner rail.
   *
   * @param e1 Direction Starting direction of the corner rail
   * @param e2 Direction Ending direction of the corner rail
   * @param loc GridLoc Location of a corner rail.
   * @param railtype type of a corner rail
   * @param start the startAngle of the rail.
   * @param someX x-coordinate of the rail
   * @param someY y-coordinate of the rail
   */
  CornerRail(final Direction e1, final Direction e2,
      final GridLoc loc, final String railtype,
      final int start, final double someX,
      final double someY) {
    super(e1, e2, loc);
    startAngle = start;
    super.setLoc(loc);
    rType = railtype;
    x1 = someX;
    y1 = someY;
  }

  /**
   * Redraw myself.
   *
   * @param g Graphics
   */
  public void draw(final Graphics g) {
    super.draw(g);
    g.setColor(this.getColor());
    Rectangle b = getBounds();
    g.drawArc((int) (x1 * b.width), (int) (y1 * b.height),
        b.width, b.height, startAngle, ARCANGLE);
  }

  /**
   * @return String
   */
  public String toString() {
    return rType;
  }
}
