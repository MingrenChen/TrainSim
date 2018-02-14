
/*
The StraightRail class.  A StraightRail object has two ends,
which must be opposite each other.
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>This is a straight rail</h1>
 * There are two different types of corner rail and
 * we can produce each corner rail in RailFactory class.
 */
class StraightRail extends TwoEndRail {

  /**
   * 3.
   */
  private static final int THREE = 3;
  /**
   * My line coordinates for drawing myself.
   */
  private double x1, y1, x2, y2;
  /**
   * the type of rail depends on direction.
   */
  private String rType;

  /**
   * This method is used to create a straight rail on grid board.
   *
   * @param e1 started direction
   * @param e2 ended direction
   * @param loc location of rail on grid board
   * @param railtype direction of the rail
   * @param xy x,y location
   */
  StraightRail(final Direction e1, final Direction e2, final GridLoc loc,
      final String railtype,
      final double[] xy) {
    super(e1, e2, loc);
    final int two = 2;
    final int one = 1;
    final int zero = 0;
    this.setColor(Color.blue);
    x1 = xy[zero];
    y1 = xy[one];
    x2 = xy[two];
    y2 = xy[THREE];
    super.setLoc(loc);
    rType = railtype;
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
    g.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
        (int) (x2 * b.width), (int) (y2 * b.height));
  }

  /**
   * @return String
   */
  public String toString() {
    return rType;
  }
}
