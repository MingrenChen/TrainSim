
/**
 * <h1>Factory to produce all the rail</h1>
 * The Rail Factory can produce different types of rail (straight
 * rail, switch rail, cross rail and corner rail.
 */
final class RailFactory {

  /**
   * hidden constructor.
   */
  private RailFactory() {

  }

  /**
   * 0.5.
   */
  private static final double POINTFIVE = 0.5;
  /**
   * 180.
   */
  private static final int START1 = 180;

  /**
   * 270.
   */
  private static final int START2 = 270;

  /**
   * 90.
   */
  private static final int START3 = 90;

  /**
   * This method is to create a straight rail by telling it the direction of
   * the rail (NSRail or EWRail) and the location of the rail.
   *
   * @param railType This is the type of the rail you wanna produce
   * @param loc this is the location of the rail you want to produce.
   * @return StraightRail this is the rail you want to produce.
   */
  static StraightRail getStraightRail(final String railType,
      final GridLoc loc) {
    // produce straight rail by using factory pattern.
    if (railType.equalsIgnoreCase("NSRail")) {
      double[] xy = {POINTFIVE, 0.0, POINTFIVE, 1.0};
      return new StraightRail(new Direction("north"),
          new Direction("south"), loc, railType, xy);
    }
    if (railType.equalsIgnoreCase("EWRail")) {
      double[] xy = {0.0, POINTFIVE, 1.0, POINTFIVE};

      return new StraightRail(new Direction("east"),
          new Direction("west"), loc, railType, xy);
    }
    return null;
  }

  /**
   * This method is to create a Corner rail by telling it the direction of
   * the rail (SWRail, SERail, NWRail or NERail) and the location of the rail.
   *
   * @param railType This is the type of the rail you wanna produce
   * @param loc this is the location of the rail you want to produce.
   * @return CornerRail this is the rail you want to produce.
   */
  static CornerRail getCornerRail(final String railType,
      final GridLoc loc) {
    if (railType.equalsIgnoreCase("SWRail")) {
      return new CornerRail(new Direction("south"),
          new Direction("west"), loc, "SWRail",
          0, -POINTFIVE, POINTFIVE);
    }
    if (railType.equalsIgnoreCase("SERail")) {
      return new CornerRail(new Direction("south"),
          new Direction("east"), loc, "SERail",
          START3, POINTFIVE, POINTFIVE);
    }
    if (railType.equalsIgnoreCase("NWRail")) {
      return new CornerRail(new Direction("north"),
          new Direction("west"), loc, "NWRail",
          START2, -POINTFIVE,
          -POINTFIVE);
    }
    if (railType.equalsIgnoreCase("NERail")) {
      return new CornerRail(new Direction("north"),
          new Direction("east"), loc, "NERail",
          START1, POINTFIVE, -POINTFIVE);
    }
    return null;
  }

  /**
   * This method is to create a Cross rail by telling it the
   * location of the rail. Unlike other methods,
   * it doesn't need a direction because it has
   * four directions.
   *
   * @param loc this is the location of the rail you want to produce.
   * @return CrossRail this is the rail you want to produce.
   */
  static CrossRail getCrossRail(final GridLoc loc) {
    return new CrossRail(loc);
  }

  /**
   * This method is to create a Switch rail by telling it the direction of
   * the rail (EWNRail, EWSRail, NSERail, NSWRail, SNERail, SNWRail, WENRail
   * or WESRail) and the location of the rail.
   *
   * @param railType This is the type of the rail you wanna produce
   * @param loc this is the location of the rail you want to produce.
   * @return SwitchRail this is the rail you want to produce.
   */
  static SwitchRail getSwitchRail(final String railType, final GridLoc loc) {
    // Eight switch rails
    if (railType.equalsIgnoreCase("EWNRail")) {
      double[] xy = {0.0, POINTFIVE, 1.0, POINTFIVE, POINTFIVE, -POINTFIVE};
      return new SwitchRail(new Direction("east"), new Direction("west"),
          new Direction("north"), loc, START1, xy, "EWNRail");
    }
    if (railType.equalsIgnoreCase("EWSRail")) {
      double[] xy = {0.0, POINTFIVE, 1.0, POINTFIVE, POINTFIVE, POINTFIVE};
      return new SwitchRail(new Direction("east"), new Direction("west"),
          new Direction("south"), loc, START3, xy, "EWSRail");
    }
    if (railType.equalsIgnoreCase("NSERail")) {
      double[] xy = {POINTFIVE, 0.0, POINTFIVE, 1.0, POINTFIVE, -POINTFIVE};
      return new SwitchRail(new Direction("north"), new Direction("south"),
          new Direction("east"), loc, START1, xy, "NSERail");
    }
    if (railType.equalsIgnoreCase("NSWRail")) {
      double[] xy = {POINTFIVE, 0.0, POINTFIVE, 1.0, -POINTFIVE, -POINTFIVE};
      return new SwitchRail(new Direction("north"), new Direction("south"),
          new Direction("west"), loc, START2, xy, "NSWRail");
    }
    if (railType.equalsIgnoreCase("SNERail")) {
      double[] xy = {POINTFIVE, 0.0, POINTFIVE, 1.0, POINTFIVE, POINTFIVE};
      return new SwitchRail(new Direction("south"), new Direction("north"),
          new Direction("east"), loc, START3, xy, "SNERail");
    }
    if (railType.equalsIgnoreCase("SNWRail")) {
      double[] xy = {POINTFIVE, 0.0, POINTFIVE, 1.0, -POINTFIVE, POINTFIVE};
      return new SwitchRail(new Direction("south"), new Direction("north"),
          new Direction("west"), loc, 0, xy, "SNWRail");
    }
    if (railType.equalsIgnoreCase("WENRail")) {
      double[] xy = {0.0, POINTFIVE, 1.0, POINTFIVE, -POINTFIVE, -POINTFIVE};
      return new SwitchRail(new Direction("west"), new Direction("east"),
          new Direction("north"), loc, START2, xy, "WENRail");
    }
    if (railType.equalsIgnoreCase("WESRail")) {
      double[] xy = {0.0, POINTFIVE, 1.0, POINTFIVE, -POINTFIVE, POINTFIVE};
      return new SwitchRail(new Direction("west"), new Direction("east"),
          new Direction("south"), loc, 0, xy, "WESRail");
    }
    return null;
  }

  /**
   * return a empty rail.
   *
   * @return EmptyRail.
   */
  static EmptyRail getEmptyRail() {
    return new EmptyRail();
  }
}
