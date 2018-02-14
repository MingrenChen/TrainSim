
//The CrossRail class.  A CrossRail object has four ends.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The CrossRail class.  A CrossRail object has four ends.
 */
class CrossRail extends Rail {

  /**
   * 0.5.
   */
  private static final double POINTFIVE = 0.5;

  /**
   * My line coordinates for drawing myself.
   */
  private double x1, y1, x2, y2, x3, y3, x4, y4;

  /**
   * (end1,end2) and (end3,end4) are the two pairs.
   * The are, in order, always 'north', 'south', 'east', and 'west'.
   */
  private Direction end1, end2, end3, end4;

  /**
   * The Rail in the direction end1, end2, end3, end4.
   */
  private Rail neighbour1, neighbour2, neighbour3, neighbour4;


  /**
   * create a cross rail from four directions.
   *
   * @param loc GridLoc
   */
  CrossRail(final GridLoc loc) {
    super(loc);
    this.setColor(Color.darkGray);
    end1 = new Direction("north");
    end2 = new Direction("south");
    end3 = new Direction("east");
    end4 = new Direction("west");

    setLoc(loc);

  }

  /**
   * return if train can exit from direction.
   *
   * @param d Direction
   * @return boolean
   */
  boolean exitOK(final Direction d) {
    return d.equals(end1) || d.equals(end2) || d.equals(end3) || d.equals(end4);
  }

  //

  /**
   * Return true if d is valid for this Rail,
   * return false and print an error otherwise.
   *
   * @param d Direction direction of exit
   * @return boolean
   */
  private boolean validDir(final Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.getDirection() + " " + end2.getDirection()
          + " " + d.getDirection());
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * set location of the rail.
   *
   * @param loc GridLoc
   */
  public void setLoc(final GridLoc loc) {
    super.setLoc(loc);
    x1 = 0.0;
    y1 = POINTFIVE;
    x2 = 1.0;
    y2 = POINTFIVE;

    x3 = POINTFIVE;
    y3 = 0.0;
    x4 = POINTFIVE;
    y4 = 1.0;
  }

  /**
   * connect with a rail r on a direction.
   *
   * @param r Rail Another rail
   * @param d Direction
   */
  void register(final Rail r, final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else if (d.equals(end2)) {
        neighbour2 = r;
      } else if (d.equals(end3)) {
        neighbour3 = r;
      } else if (d.equals(end4)) {
        neighbour4 = r;
      }
    }
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d Direction where a Car entered
   * @return Direction where the Car will exit
   */
  Direction exit(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return end2;
      } else if (d.equals(end2)) {
        return end1;
      } else if (d.equals(end3)) {
        return end4;
      } else if (d.equals(end4)) {
        return end3;
      }
    }

    return null;
  }

  /**
   * d is the direction that I entered from, and must be one of end1 and end2.
   * Return the Rail at the other end.
   *
   * @param d Direction
   * @return Rail
   */
  Rail nextRail(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return neighbour2;
      } else if (d.equals(end2)) {
        return neighbour1;
      } else if (d.equals(end3)) {
        return neighbour4;
      } else if (d.equals(end4)) {
        return neighbour3;
      }
    }

    return null;
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
    g.drawLine((int) (x3 * b.width), (int) (y3 * b.height),
        (int) (x4 * b.width), (int) (y4 * b.height));
  }

  /**
   * @return String
   */
  public String toString() {
    return "CrossRail";
  }

}

