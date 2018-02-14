
import java.awt.Color;
import java.awt.Frame;


/**
 * <h1>class TrainSimulation</h1>
 * Simulate trains running around a track.
 * The class TrainSimulation contains all the methods and instance variables
 * necessary to keep track of and run the train simulation.
 */
public class TrainSimulation extends Frame {

  /**
   * 4.
   */
  private static final int FOUR = 4;

  /**
   * 5.
   */
  private static final int FIVE = 5;

  /**
   * speed of a train.
   */
  private static final int SPEED1 = 620;

  /**
   * speed of a train.
   */
  private static final int SPEED2 = 350;

  /**
   * 8.
   */
  private static final int EIGHT = 8;
  /**
   * The Tracks on which the Trains run.
   */
  private static Track[] tracks = new Track[FOUR];

  /**
   * width of size.
   */
  private static final int W = 540;

  /**
   * height of size.
   */
  private static final int H = 400;
  /**
   * The Trains running on the Tracks.
   */
  private static Train[] trains = new Train[EIGHT];

  // main
  // ------------------------------------------------------------------
  // This is where it all starts.

  /**
   * main
   * This is where it all starts.
   *
   * @param args String[]
   */
  public static void main(final String[] args) {

    // Track 2.
    tracks[0] = new Track();
    tracks[0].setSize(W, H);
    tracks[0].setLocation(0, 0);
    tracks[0].setBackground(Color.white);
    tracks[0].setVisible(true);

    trains[0] = new Train("Train 0");
    trains[0].addToTrain(new Car(Color.GREEN));
    trains[0].addToTrain(new Car(Color.RED));

    trains[1] = new Train("Train 1");
    trains[1].addToTrain(new Car(Color.GREEN));
    trains[1].addToTrain(new Car(Color.RED));

//        T.trains[2] = new Train();
//        T.trains[2].addToTrain(new Engine());
//        T.trains[2].addToTrain(new Caboose());
//    trains[2] = new Train("Train 2");
//    trains[2].addToTrain(new Car(Color.GREEN));
//   trains[2].addToTrack(tracks[0], new Direction("west"), new GridLoc(10, 9));
//    trains[2].setSpeed(620);
    trains[0].addToTrack(tracks[0], new Direction("east"), new GridLoc(2, 2));
    trains[0].setSpeed(SPEED1);
    trains[1].addToTrack(tracks[0], new Direction("south"),
        new GridLoc(1, FIVE));
    trains[1].setSpeed(SPEED2);


  }
}

