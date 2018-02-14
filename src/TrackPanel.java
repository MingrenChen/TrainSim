
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;

/**
 * TrackPanel class.
 */
class TrackPanel extends Panel {

  /**
   * 3.
   */
  private static final int BOUND = 10;
  /**
   * The buffer in which to draw the image; used for double buffering.
   */
  private Image backBuffer;

  /**
   * The graphics context to use when double buffering.
   */
  private Graphics backG;

  /**
   * add rails to panel..
   *
   * @param r Rail[][]
   */
  void addToPanel(final Rail[][] r) {

    setLayout(new GridLayout(r.length, r[0].length, 0, 0));

    for (Rail[] aR : r) {
      for (int col = 0; col < r[0].length; col++) {
        add("", aR[col]);
      }
    }
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
   * Insets.
   *
   * @return Insets
   */
  public Insets getInsets() {
    return new Insets(BOUND, BOUND, BOUND, BOUND);
  }

  /**
   * Update the display; tell all my Tracks to update themselves.
   *
   * @param g Graphics
   */
  public void update(final Graphics g) {

    // Get my width and height.
    int w = getBounds().width;
    int h = getBounds().height;

    // If we don't yet have an Image, create one.
    if (backBuffer == null
        || backBuffer.getWidth(null) != w
        || backBuffer.getHeight(null) != h) {
      backBuffer = createImage(w, h);
      if (backBuffer != null) {

        // If we have a backG, it belonged to an old Image.
        // Get rid of it.
        if (backG != null) {
          backG.dispose();
        }
        backG = backBuffer.getGraphics();
      }
    }

    if (backBuffer != null) {

      // Fill in the Graphics context backG.
      g.setColor(Color.white);
      g.fillRect(0, 0, w, h);

      // Now copy the new image to g.
      // g.drawImage(backBuffer, 0, 0, null);
    }

  }
}

