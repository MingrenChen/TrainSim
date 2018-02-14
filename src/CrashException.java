
// AccidentException is exception when train
// meet accident.

import static java.lang.Thread.sleep;

import javax.swing.JOptionPane;

/**
 * handle the exception when trains meet accident.
 */
class CrashException extends Exception {

  /**
   * time to wait.
   */
  private static final int WT = 99999999;

  /**
   * initialize an accident.
   *
   * @param msg String Accident type
   * @throws InterruptedException InterruptedException
   */
  CrashException(final String msg) throws InterruptedException {
    JOptionPane.showMessageDialog(null, msg);
    if (msg.equalsIgnoreCase("One of your train run out of rails")) {
      sleep(WT);
    } else if (msg.equalsIgnoreCase("Your two trains crash!")) {
      System.exit(1);
    }
  }
}
