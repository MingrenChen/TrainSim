
import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;


/**
 * <h1>The Track class.</h1>
 * A Track object is made up of Rails, and
 * has zero or more trains in it.
 */
class Track extends Frame {

  /**
   * The Panel on which the Track appears.
   */
  private TrackPanel trackPanel;

  /**
   * Maximum number of trains.
   */
  private static final int MAX_TRAIN = 10;

  /**
   * The maximum number of trains I can hold.
   */
  private Train[] trainList = new Train[MAX_TRAIN];
  /**
   * number of trains.
   */
  private int numTrains = 0;

  /**
   * The grid of rails.
   */
  private Rail[][] rails;

  /**
   * BoardSize, always 20.
   */
  private static final int BOARDSIZE = 20;

  /**
   * Row with rails on it.
   */
  private static final int ROWSIZE = 6;

  /**
   * Set up a new, simple Track.
   */
  Track() {
    rails = new Rail[BOARDSIZE][BOARDSIZE];
    Direction west = new Direction("west");
    Direction east = new Direction("east");
    Direction north = new Direction("north");
    Direction south = new Direction("south");
    buildTrack();

    String[] row0 = {"SERail", "EWRail", "SWRail", "",
        "", "", "", "", "", "SERail", "SWRail", "", "",
        "", "", "", "", "", "", ""};

    String[] row1 = {"NSRail", "", "NSRail", "", "", "SERail",
        "EWRail", "WESRail", "EWRail", "NSWRail", "NSRail",
        "", "", "", "", "", "", "", "", ""};

    String[] row2 = {"NERail", "EWRail", "Cross", "EWRail",
        "EWRail", "Cross", "SWRail", "SNERail", "EWRail",
        "EWNRail", "NWRail", "", "", "", "", "", "", "", "", ""};

    String[] row3 = {"", "SERail", "Cross", "EWSRail",
        "SWRail", "NERail", "NWRail", "NSRail", "",
        "", "", "", "", "", "", "", "", "", "", ""};

    String[] row4 = {"", "NERail", "WENRail", "SNWRail",
        "NERail", "WESRail", "EWRail", "NWRail", "",
        "", "", "", "", "", "", "", "", "", "", ""};

    String[] row5 = {"", "", "", "NERail", "EWRail", "NWRail",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", ""};

    String[][] board = {row0, row1, row2, row3, row4, row5, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null};
    for (int i = ROWSIZE; i < BOARDSIZE; i++) {
      String[] emptyrow = {"", "", "", "", "", "", "", "", "", "",
          "", "", "", "", "", "", "", "", "", ""};
      board[i] = emptyrow;
    }

    for (int i = 0; i < BOARDSIZE; i++) {
      for (int j = 0; j < BOARDSIZE; j++) {
        if (board[i][j].equals("NSRail") || board[i][j].equals("EWRail")) {
          rails[i][j] = RailFactory.getStraightRail(board[i][j],
              new GridLoc(i, j));
        } else if (board[i][j].equals("Cross")) {
          rails[i][j] = RailFactory.getCrossRail(new GridLoc(i, j));
        } else if (board[i][j].equals("")) {
          rails[i][j] = RailFactory.getEmptyRail();
        } else if (board[i][j].length() == ROWSIZE + 1) {
          rails[i][j] = RailFactory.getSwitchRail(board[i][j],
              new GridLoc(i, j));
        } else {
          rails[i][j] = RailFactory.getCornerRail(board[i][j],
              new GridLoc(i, j));
        }
      }
    }
    for (int i = 0; i < BOARDSIZE; i++) {
      for (int j = 0; j < BOARDSIZE - 1; j++) {
        if (rails[i][j].exitOK(east) && rails[i][j + 1].exitOK(west)) {
          connectRails(rails[i][j], rails[i][j + 1], east);
        }
      }
    }
    for (int i = 0; i < BOARDSIZE - 1; i++) {
      for (int j = 0; j < BOARDSIZE; j++) {
        if (rails[i][j].exitOK(south) && rails[i + 1][j].exitOK(north)) {
          connectRails(rails[i][j], rails[i + 1][j], south);
        }
      }
    }
    trackPanel.addToPanel(rails);
  }

  /**
   * Add the buttons for creating Rails.
   */
  private void buildTrack() {
    trackPanel = new TrackPanel();
    add("Center", trackPanel);

    Button runStopButton = new Button("Run");
    Button quitButton = new Button("Quit");
    Button accelButton = new Button("Accelerate");
    Button decelButton = new Button("Decelerate");
    Button accelLotsButton = new Button("Accelerate A Lot");
    Button decelLotsButton = new Button("Decelerate A Lot");

    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(0, 1));
    p2.add(runStopButton);
    p2.add(accelLotsButton);
    p2.add(decelLotsButton);
    p2.add(accelButton);
    p2.add(decelButton);
    p2.add(quitButton);
    add("East", p2);

    pack();
  }

  /**
   * Read Rail-placing commands from the user.
   *
   * @param evt Event
   * @return boolean
   */
  public boolean handleEvent(final Event evt) {
    Object target = evt.target;

    if (evt.id == Event.ACTION_EVENT) {
      if (target instanceof Button) {
        if ("Run".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].start();
          }
          ((Button) target).setLabel("Suspend");
        } else if ("Suspend".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].suspend();
          }
          ((Button) target).setLabel("Resume");
        } else if ("Resume".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].resume();
          }
          ((Button) target).setLabel("Suspend");
        } else if ("Accelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerate();
          }
        } else if ("Decelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerate();
          }
        } else if ("Accelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerateALot();
          }
        } else if ("Decelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerateALot();
          }
        } else if ("Quit".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].stop();
          }
          System.exit(0);
        }
        return true;
      }
    }

    // If we get this far, I couldn't handle the event
    return false;
  }

  /**
   * Connect rails r1 and r2; r2 is in direction d from r1.
   *
   * @param r1 Rail
   * @param r2 Rail
   * @param d Direction
   */
  private void connectRails(final Rail r1, final Rail r2, final Direction d) {
    r1.register(r2, d);
    r2.register(r1, d.opposite());
  }

  /**
   * Add e to the rail at location loc.
   *
   * @param loc GridLoc
   * @param e Car
   */
  void addCar(final GridLoc loc, final Car e) {
    rails[loc.getRow()][loc.getCol()].enter(e);
    e.setRail(rails[loc.getRow()][loc.getCol()]);
  }

  /**
   * Paint the display.
   *
   * @param g Graphics
   */
  public void paint(final Graphics g) {
    update(g);
  }

  /**
   * update the display.
   *
   * @param g Graphics
   */
  public void update(final Graphics g) {

    trackPanel.repaint();
  }

  /**
   * add a Train.
   *
   * @param t Train
   */
  void addTrain(final Train t) {
    trainList[numTrains] = t;
    numTrains++;
  }

}
