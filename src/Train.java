
//      The Train class.Trains have an Engine, followed by one or more Cars,
//     followed by a Caboose.  There is no limit to the length of a Train.  The
//     train has a speed, which is related to the size of the engine, the weight
//     of the whole train, and the amount of power flowing through the tracks.
//     A train has a delay, which is directly related to the speed -- the faster
//     the train is moving, the shorter the delay.  Each turn, a Train will move
//     one track piece in the current direction.

/**
 * <h1>The Train class.</h1>
 * Trains have an Engine, followed by one or more Cars,
 * followed by a Caboose.  There is no limit to the length of a Train.  The
 * train has a speed, which is related to the size of the engine, the weight
 * of the whole train, and the amount of power flowing through the tracks.
 *
 * A train has a delay, which is directly related to the speed -- the faster
 * the train is moving, the shorter the delay.  Each turn, a Train will move
 * one track piece in the current direction.
 */
class Train extends Thread {

  /**
   * delayTime.
   */
  private static final int DELAYTIME = 20;
  /**
   * The amount of time between each of my turns.
   */
  private int delay;

  /**
   * The Car at the end of the train.
   */
  private Car caboose, engine;

  /**
   * Initialize a train.
   *
   * @param threadName String
   */
  Train(final String threadName) {
    super(threadName);
  }

  /**
   * Add Car T to the end of me.
   *
   * @param c Car
   */
  void addToTrain(final Car c) {
    if (engine != null) {
      caboose.setNextCar(c);
    } else {
      engine = c;
    }

    caboose = c;
  }

  /**
   * set speed of a train.
   *
   * @param d int
   */
  void setSpeed(final int d) {
    delay = d;
  }


  /**
   * Add me to Track T at location loc moving in direction dir.
   *
   * @param t Track
   * @param dir Direction
   * @param loc GridLoc
   */
  void addToTrack(final Track t, final Direction dir, final GridLoc loc) {
    t.addTrain(this);

    Car currCar = engine;
    while (currCar != null) {
      currCar.setDirection(dir);
      t.addCar(loc, currCar);

      // Now figure out the dir for the next Car,
      // and the next loc.

      if (dir.equals("north")) {
        loc.setRowD();
      } else if (dir.equals("south")) {
        loc.setRowI();
      } else if (dir.equals("east")) {
        loc.setColI();
      } else if (dir.equals("west")) {
        loc.setColD();
      }

      Direction nD = currCar.getCurrentRail().exit(dir);
      Rail nextRail = currCar.getCurrentRail().nextRail(nD);

      // Now I know the Rail on which the next currCar will
      // be.  Find out how it got on to it.
      dir.sdir(nextRail);

      currCar = currCar.getNextCar();
    }
  }

  /**
   * double the train's speed.
   */
  void accelerateALot() {
    delay /= 2;
  }

  /**
   * Half the train's speed.
   */
  void decelerateALot() {
    delay *= 2;
  }

  /**
   * Speed up by a factor of 20ms.
   */
  void accelerate() {
    delay -= DELAYTIME;
  }

  /**
   * Slow down by a factor of 20ms.
   */
  void decelerate() {
    delay += DELAYTIME;
  }

  /**
   * make the train run.
   */
  public void run() {
    while (true) {
      try {
        engine.move();
      } catch (CrashException ignored) {
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }

      // Sleep for 1 second.
      try {
        sleep(delay);
      } catch (InterruptedException ignored) {
      }
    }
  }

}

