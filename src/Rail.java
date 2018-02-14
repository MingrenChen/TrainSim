
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>The Rail class.</h1>
 * A Rail object is a piece of track.  It knows
 * whether there is a Train on it or not, and Trains can enter()
 * and leave().  Given an entrance, a Rail can report where the exit()
 * is.
 */
abstract class Rail extends Canvas {

  /**
   * size.
   */
  private static final int SIZE = 20;
  // True if a car entered or left.  Used to speed up redrawing.
  /**
   * color of a rail.
   */
  private Color color;

  /**
   * get color of rail.
   *
   * @return Color
   */
  public Color getColor() {
    return color;
  }

  /**
   * set color of a rail.
   *
   * @param c Color
   */
  public void setColor(final Color c) {
    this.color = c;
  }

  /**
   * if rail has a train on it.
   */
  private boolean haveATrain;
  /**
   * currentCar on it.
   */
  private Car currentCar;
  /**
   * location of the rail.
   */
  private GridLoc location;

  /**
   * create a new rail.
   *
   * @param loc GridLoc
   */
  Rail(final GridLoc loc) {
    location = loc;
    haveATrain = false;
  }

  /**
   * create a new rail.
   */
  Rail() {
    super();
    haveATrain = false;
    int size = SIZE;
    setSize(size, size);
  }

  /**
   * Return true iff I have a Car.
   *
   * @return boolean
   */
  boolean occupied() {
    return haveATrain;
  }

  /**
   * return location.
   *
   * @return GridLoc
   */
  public GridLoc getLoc() {
    return location;
  }

  /**
   * set location.
   *
   * @param loc GridLoc
   */
  public void setLoc(final GridLoc loc) {
    location = loc;
  }

  /**
   * Redraw myself.
   *
   * @param g Graphics
   */
  public void draw(final Graphics g) {

    Rectangle b = getBounds();
    g.setColor(Color.white);
    g.fillRect(0, 0, b.width - 1, b.height - 1);
    g.setColor(Color.lightGray);
    g.drawRect(0, 0, b.width - 1, b.height - 1);

    if (haveATrain) {
      currentCar.draw(g);
    }
  }

  //

  /**
   * Register that a Train is on me.  Return true if succesful,
   * false otherwise.
   *
   * @param newCar Car
   * @return boolean
   */
  boolean enter(final Car newCar) {
    haveATrain = true;
    currentCar = newCar;
    return true;
  }

  /**
   * Register that a Train is no longer on me.
   */
  void leave() {
    haveATrain = false;
    repaint();
  }

  /**
   * Update my display.
   *
   * @param g Graphics
   */
  public void paint(final Graphics g) {
    draw(g);
  }

  /**
   * Return true if d is a valid direction for me.
   *
   * @param d Direction
   * @return boolean
   */
  abstract boolean exitOK(Direction d);

  /**
   * Register that Rail r is in Direction d.
   *
   * @param r Rail
   * @param d Direction
   */
  abstract void register(Rail r, Direction d);

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d Direction
   * @return Direction
   */
  abstract Direction exit(Direction d);

  // Given that d is the Direction from which a Car entered,
  // report which Rail is next.

  /**
   * Given that d is the Direction from which a Car entered,
   * report which Rail is next.
   *
   * @param d Direction
   * @return Rail
   */
  abstract Rail nextRail(Direction d);

  /**
   * @return String
   */
  public String toString() {
    return "Rail";
  }

}

