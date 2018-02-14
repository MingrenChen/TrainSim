
//This is a place on the Track which does not have an actual
// piece of track.

/**
 * <h1>The EmptyRail class.</h1>
 * This is a place on the Track which does not have an actual
 * piece of track.
 */
class EmptyRail extends Rail {

  /**
   * Return true if d is a valid direction for me.
   *
   * @param d Direction
   * @return boolean
   */
  public boolean exitOK(final Direction d) {
    return false;
  }

  /**
   * Register that r is adjacent to me from direction d.
   *
   * @param r Rail
   * @param d Direction
   */
  public void register(final Rail r, final Direction d) {
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
    return null;
  }

  /**
   * d is the direction that I entered from, and must be one of
   * end1, end2 and end3. Return the Rail at the other end.
   *
   * @param d Direction
   * @return null
   */
  public Rail nextRail(final Direction d) {
    return null;
  }

}

