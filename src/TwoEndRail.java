/*

The TwoEndRail class.  A TwoEndRail object has two ends,
which may or may be not be opposite each other.

*/

import java.awt.Color;

/**
 * The TwoEndRail class.  A TwoEndRail object has two ends,
 * which may or may be not be opposite each other.
 */
abstract class TwoEndRail extends Rail {

  /**
   * They are direction of rails..
   */
  private Direction end1, end2;

  /**
   * The Rail in the direction end1 and 2.
   */
  private Rail neighbour1, neighbour2;

  /**
   * create a cross rail from four directions.
   *
   * @param e1 Direction Starting direction of the two end rail
   * @param e2 Direction Ending direction of the two end rail
   * @param loc GridLoc
   */
  TwoEndRail(final Direction e1, final Direction e2, final GridLoc loc) {
    super(loc);
    this.setColor(Color.black);
    end1 = e1;
    end2 = e2;
  }

  /**
   * return weather d is a valid direction for rail.
   *
   * @param d Direction
   * @return boolean
   */
  public boolean exitOK(final Direction d) {
    return d.equals(end1) || d.equals(end2);
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
      } else {
        neighbour2 = r;
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
        return end2;
      } else {
        return end1;
      }
    }
    return null;
  }

  /**
   * d is the direction that I entered from, and must be one of end1,
   * end2 and end3. Return the Rail at the other end.
   *
   * @param d Direction
   * @return Rail
   */
  public Rail nextRail(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return neighbour2;
      } else {
        return neighbour1;
      }
    }

    return null;
  }

  /**
   * @return String
   */
  public String toString() {
    return "TwoEndRail";
  }

}

