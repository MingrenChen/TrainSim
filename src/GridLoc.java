
// The (x,y) location on the Track.

/**
 * <h1>
 * Location.
 * </h1>
 * The Location on the Track
 */
public class GridLoc {

  /**
   * @param r int
   * @param c int
   */
  public GridLoc(final int r, final int c) {
    row = r;
    col = c;
  }

  /**
   * the location.
   */
  private int col, row;

  /**
   * get col.
   *
   * @return int
   */
  public int getCol() {
    return col;
  }

  /**
   * get row.
   *
   * @return int
   */
  final int getRow() {
    return row;
  }

  /**
   * set col --.
   */
  final void setColD() {
    this.col--;
  }

  /**
   * set col ++.
   */
  final void setColI() {
    this.col++;
  }

  /**
   * set row--.
   */
  final void setRowD() {
    this.row--;
  }

  /**
   * set row++.
   */
  final void setRowI() {
    this.row++;
  }

  /**
   * @return String
   */
  public String toString() {
    return (row + " " + col);
  }

}

