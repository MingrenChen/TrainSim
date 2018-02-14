
/**
 * <h1>A direction</h1>
 * one of 'north', 'south', 'east' and 'west'.
 */
class Direction {

  /**
   * direction of a Direction.
   */
  private String direction;

  /**
   * a direction in north south west and east.
   *
   * @param s String
   */
  Direction(final String s) {
    if (!s.equals("north") && !s.equals("south")
        && !s.equals("east") && !s.equals("west")) {
      System.err.println(s + " is an invalid direction. Must be "
          + "'north', 'south', 'east' or 'west'");
      System.exit(0);
    }

    direction = s;

  }

  /**
   * return direction.
   *
   * @return Direction
   */
  String getDirection() {
    return direction;
  }

  /**
   * set direction.
   *
   * @param d String
   */
  public void setDirection(final String d) {
    this.direction = d;
  }

  /**
   * equals method of direction and direction.
   *
   * @param d Direction
   * @return boolean
   */
  boolean equals(final Direction d) {
    return d.equals(direction);
  }

  /**
   * equals method of direction and string.
   *
   * @param s String
   * @return boolean
   */
  boolean equals(final String s) {
    return s.equals(direction);
  }

  /**
   * return exit.
   *
   * @param r Rail
   */
  void sdir(final Rail r) {
    r.exit(this.opposite());
  }

  /**
   * @return String
   */
  public String toString() {
    return direction;
  }

  /**
   * find the opposite direction of given direction.
   *
   * @return Direction
   */
  Direction opposite() {
    Direction d = null;
    switch (direction) {
      case "north":
        d = new Direction("south");
        break;
      case "south":
        d = new Direction("north");
        break;
      case "east":
        d = new Direction("west");
        break;
      case "west":
        d = new Direction("east");
        break;
      default:
        break;
    }

    return d;
  }

}

