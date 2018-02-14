
//The Car class. A Car object is a car in a train.  It has
//weight and color, and can draw() and move().

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>The Car class.</h1>
 * A Car object is a car in a train.  It has
 * weight and color, and can draw() and move().
 */
class Car {

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
   * 7.
   */
  private static final int SEVEN = 7;

  /**
   * 8.
   */
  private static final int EIGHT = 8;

  /**
   * The Rail that I am currently occupying.
   */
  private Rail currentRail;
  /**
   * My color.
   */
  private Color color;
  /**
   * The Car that immediately follows me.
   */
  private Car nextCar;
  /**
   * The direction in which I entered the current Rail.
   */
  private Direction dir;

  /**
   * Initialize a car with given color.
   *
   * @param c Color color of the car on GUI.
   */
  Car(final Color c) {
    color = c;
  }

  /**
   * Set the car moving in direction d.
   *
   * @param d Direction direction the car is moving.
   */
  void setDirection(final Direction d) {
    dir = d;
  }

  /**
   * Place this Car on Rail r.
   *
   * @param r Rail The rail we want the car on.
   */
  void setRail(final Rail r) {
    currentRail = r;
  }

  /**
   * Move forward one TrackPiece; t is the current TrackPiece. Tell
   * all of the cars I am pulling to move as well.
   *
   * @throws CrashException @CrashException
   * @throws InterruptedException InterruptedException
   */
  void move() throws CrashException, InterruptedException {
    Direction nD = currentRail.exit(dir);

    Direction nextDir = nD.opposite();
    Rail nextRail = currentRail.nextRail(dir);
    if (nextRail == null) {
      throw new CrashException("One of your train run out of rails");
    }
    if (nextRail.occupied()) {
      throw new CrashException("Your two trains crash!");
    }
    if (nextRail instanceof SwitchRail) {
      if (((SwitchRail) nextRail).getEnd2().equals(nextDir)) {
        if (!((SwitchRail) nextRail).isGoingStraight()) {
          throw new CrashException("One of your train run out of rails");
        }
      } else if (((SwitchRail) nextRail).getEnd3().equals(nextDir)) {
        if (((SwitchRail) nextRail).isGoingStraight()) {
          throw new CrashException("One of your train run out of rails");
        }
      }
    }
    dir = nextDir;

    if (nextRail.enter(this)) {
      currentRail.leave();
      currentRail = nextRail;

      // We have to call this here rather than within currentRail.enter()
      // because otherwise the wrong Rail is used...
      currentRail.repaint();

      if (nextCar != null) {
        nextCar.move();
      }
    }
  }

  /**
   * return next car.
   *
   * @return Car
   */
  Car getNextCar() {
    return nextCar;
  }

  /**
   * set next car.
   *
   * @param thisCar Car
   */
  void setNextCar(final Car thisCar) {
    nextCar = thisCar;
  }

  /**
   * return current rail.
   *
   * @return Rail
   */
  Rail getCurrentRail() {
    return currentRail;
  }

  /**
   * Return true if the current Rail is a SwitchRail and I am going
   * straight through it.
   *
   * @return boolean
   */
  private boolean switchStraight() {
    return currentRail instanceof SwitchRail
        && ((SwitchRail) currentRail).isGoingStraight();
  }

  /**
   * draw myself.
   *
   * @param g Graphics
   */
  void draw(final Graphics g) {

    Rectangle b = currentRail.getBounds();

    // the polygon to draw on the screen.

    double width = b.width;
    double height = b.height;

    int sqrtOfHypotenuse = (int) Math.sqrt((width * width / FOUR)
        + (height * height / FOUR));

    int[] xPoints = new int[FIVE];
    int[] yPoints = new int[FIVE];

    if (currentRail instanceof StraightRail
        || currentRail instanceof CrossRail
        || switchStraight()) {
      if (dir.equals("north") || dir.equals("south")) {
        makeStraightPolygon(xPoints, yPoints);
      } else {
        makeStraightPolygon(yPoints, xPoints);
      }
    } else if (currentRail instanceof CornerRail || !switchStraight()) {
      if (currentRail.toString().equalsIgnoreCase("NERail")
          || currentRail.toString().equalsIgnoreCase("NSERail")
          || currentRail.toString().equalsIgnoreCase("EWNRail")) {
        int[] xy = {(int) width, (int) (width / 2),
            (int) (height / 2),
            0};
        makeCornerPolygon(xPoints, yPoints, -sqrtOfHypotenuse,
            sqrtOfHypotenuse, xy);
      } else if (currentRail.toString().equalsIgnoreCase("NWRail")
          || currentRail.toString().equalsIgnoreCase("NSWRail")
          || currentRail.toString().equalsIgnoreCase("WENRail")) {
        int[] xy = {(int) (width / 2), 0, 0, (int) (height / 2)};
        makeCornerPolygon(xPoints, yPoints, sqrtOfHypotenuse,
            sqrtOfHypotenuse, xy);
      } else if (currentRail.toString().equalsIgnoreCase("SERail")
          || currentRail.toString().equalsIgnoreCase("SNERail")
          || currentRail.toString().equalsIgnoreCase("EWNRail")) {
        int[] xy = {(int) (width / 2), (int) width,
            (int) height, (int) (height / 2)};
        makeCornerPolygon(xPoints, yPoints, -sqrtOfHypotenuse,
            -sqrtOfHypotenuse, xy);
      } else if (currentRail.toString().equalsIgnoreCase("SWRail")
          || currentRail.toString().equalsIgnoreCase("SNWRail")
          || currentRail.toString().equalsIgnoreCase("WESRail")) {
        int[] xy = {0, (int) (width / 2),
            (int) (height / 2), (int) height};
        makeCornerPolygon(xPoints, yPoints, sqrtOfHypotenuse,
            -sqrtOfHypotenuse, xy);
      }
    }

    g.setColor(color);
    g.drawPolygon(xPoints, yPoints, FIVE);

  }

  /**
   * The points, in order, are the back right of the car,
   * the front right of the car, the front left of the
   * car, and the back left of the car.
   *
   * @param xPoints int[]
   * @param yPoints int[]
   * @param xSideOffset int
   * @param ySideOffset int
   * @param mod int[]
   */
  private void makeCornerPolygon(final int[] xPoints, final int[] yPoints,
      final int xSideOffset, final int ySideOffset, final int[] mod) {

    xPoints[0] = mod[0];
    xPoints[1] = mod[1];
    xPoints[2] = xPoints[1] + xSideOffset / 2;
    xPoints[THREE] = xPoints[0] + xSideOffset / 2;
    xPoints[FOUR] = xPoints[0];

    yPoints[0] = mod[2];
    yPoints[1] = mod[THREE];
    yPoints[2] = yPoints[1] + ySideOffset / 2;
    yPoints[THREE] = yPoints[0] + ySideOffset / 2;
    yPoints[FOUR] = yPoints[0];
  }

  /**
   * draw car on straight rail.
   *
   * @param aPoints int[]
   * @param bPoints int[]
   */
  private void makeStraightPolygon(final int[] aPoints, final int[] bPoints) {
    Rectangle b = currentRail.getBounds();
    int width = b.width;
    int height = b.height;

    aPoints[0] = width / FOUR;
    aPoints[1] = THREE * width / FOUR;
    aPoints[2] = THREE * width / FOUR;
    aPoints[THREE] = width / FOUR;
    aPoints[FOUR] = aPoints[0];

    bPoints[0] = height / EIGHT;
    bPoints[1] = height / EIGHT;
    bPoints[2] = SEVEN * height / EIGHT;
    bPoints[THREE] = SEVEN * height / EIGHT;
    bPoints[FOUR] = bPoints[0];
  }

  /**
   * @return String
   */
  public String toString() {
    return "Car";
  }


}

