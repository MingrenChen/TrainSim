import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>Switch Rail on track panel</h1>
 * A SwitchRail object has three ends, and a controller
 * which determines which ends are used.  If a Car moves on from the first end,
 * the switch determines which of the other two ends it leaves from.  If it
 * moves on from one of the other two ends, it automatically
 * leaves by the first end.
 */
class SwitchRail extends Rail {

  /**
   * ARCANGLE.
   */
  private static final int ARCANGLE = 90;
  /**
   * 3.
   */
  private static final int THREE = 3;

  /**
   * 4.
   */
  private static final int FOUR = 4;

  /**
   * 5.
   */
  private static final int FIVE = 5;
  /**
   * My line coordinates for drawing myself.
   */
  private double x1, y1, x2, y2, x3, y3;
  /**
   * (end1,end2) and (end1,end3) are the two pairs.
   * end1 and end2 are the straight directions (i.e., they are
   * opposite each other), and end1 and end3 form the corner.
   */
  private Direction end1;
  /**
   * (end1,end2) and (end1,end3) are the two pairs.
   * end1 and end2 are the straight directions (i.e., they are
   * opposite each other), and end1 and end3 form the corner.
   */
  private Direction end2, end3;
  /**
   * The Rail in the direction end1,2,3.
   */
  private Rail neighbour1, neighbour2, neighbour3;

  /**
   * Info for my corner portion.
   */
  private int startAngle;
  /**
   * Whether I am aligned to go straight.
   */
  private boolean goingStraight;
  /**
   * the type of rail depends on direction.
   */
  private String rType;

  /**
   * create a switch rail.
   *
   * @param e1 Direction
   * @param e2 Direction
   * @param e3 Direction
   * @param loc GridLoc
   * @param start int
   * @param xy double[]
   * @param railtype String
   */
  SwitchRail(final Direction e1, final Direction e2, final Direction e3,
      final GridLoc loc,
      final int start, final double[] xy, final String railtype) {
    super(loc);
    this.setColor(Color.magenta);
    end1 = e1;
    end2 = e2;
    end3 = e3;
    x1 = xy[0];
    y1 = xy[1];
    x2 = xy[2];
    y2 = xy[THREE];
    x3 = xy[FOUR];
    y3 = xy[FIVE];
    rType = railtype;
    startAngle = start;
    super.setLoc(loc);
  }

  /**
   * return weather d is a valid direction for rail.
   *
   * @param d Direction
   * @return boolean
   */
  public boolean exitOK(final Direction d) {
    return d.equals(end1) || d.equals(end2) || d.equals(end3);
  }

  /**
   * get end2.
   *
   * @return Direction
   */
  Direction getEnd2() {
    return end2;
  }

  /**
   * get end3.
   *
   * @return Direction
   */
  Direction getEnd3() {
    return end3;
  }

  /**
   * if the rail is going straight now.
   *
   * @return boolean
   */
  boolean isGoingStraight() {
    return goingStraight;
  }

  /**
   * Return true if d is valid for this Rail, return false
   * and print an error otherwise.
   *
   * @param d Direction
   * @return boolean
   */
  private boolean validDir(final Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.getDirection() + " "
          + end2.getDirection() + " " + d.getDirection());
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * Register that r is adjacent to me from direction d.
   *
   * @param r Rail
   * @param d Direction
   */
  public void register(final Rail r, final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else if (d.equals(end2)) {
        neighbour2 = r;
      } else {
        neighbour3 = r;
      }
    }
  }

  /**
   * report where the Car will exit.
   * Note that if d is not end1's Direction, then it will have to
   * exit toward end1.
   *
   * @param d Direction Direction from which a Car entered,
   * @return Direction
   */
  public Direction exit(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        if (goingStraight) {
          return end2;
        } else {
          return end3;
        }
      } else {
        return end1;
      }
    }
    return null;
  }

  /**
   * d is the direction that I entered from, and must
   * be one of end1, end2 and end3.
   * Return the Rail at the other end.
   *
   * @param d Direction
   * @return Rail
   */
  public Rail nextRail(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        if (goingStraight) {
          return neighbour2;
        } else {
          return neighbour3;
        }
      } else {
        return neighbour1;
      }
    }
    return null;
  }

  /**
   * Handle a mouse click.  This will toggle the direction of the switch.
   *
   * @param evt Event
   * @return boolean
   */
  public boolean handleEvent(final Event evt) {
    if (evt.id == Event.MOUSE_DOWN && !occupied()) {
      goingStraight = !goingStraight;
      repaint();
      return true;
    }
    // If we get this far, I couldn't handle the event
    return false;
  }

  /**
   * Redraw myself.
   *
   * @param g Graphics
   */
  public void draw(final Graphics g) {

    super.draw(g);

    Rectangle b = getBounds();

    // Draw current direction of the switch darker.
    int arcAngle = ARCANGLE;
    if (goingStraight) {
      g.setColor(Color.lightGray);
      g.drawArc((int) (x3 * b.width), (int) (y3 * b.height),
          b.width, b.height, startAngle, arcAngle);
      g.setColor(this.getColor());
      g.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
          (int) (x2 * b.width), (int) (y2 * b.height));
    } else {
      g.setColor(Color.lightGray);
      g.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
          (int) (x2 * b.width), (int) (y2 * b.height));
      g.setColor(this.getColor());
      g.drawArc((int) (x3 * b.width), (int) (y3 * b.height),
          b.width, b.height, startAngle, arcAngle);
    }
  }

  /**
   * @return String
   */
  public String toString() {
    return rType;
  }
}
