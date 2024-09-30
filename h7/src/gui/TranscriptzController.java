package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * TranscriptzController is responsible for managing user actions from the menu It listens for
 * specific actions triggered by menu items and performs the corresponding logic.
 */
public class TranscriptzController implements ActionListener
{
  public static final String FILE = "File";
  public static final String GRADES = "Grades";
  public static final String QUIT = "Quit";
  public static final String RESET = "Reset";
  public static final String EXIT = "Exit";

  private CompositeGradeEntryPanel entryPanel;

  private JMenuItem itemQuit;
  private JMenuItem itemReset;

  /**
   * Constructor that initializes the controller with the grade entry panel. It also sets up the
   * action listeners for the quit and reset menu items.
   * 
   * @param entryPanel
   *          The CompositeGradeEntryPanel object containing the grade panels
   */
  public TranscriptzController(final CompositeGradeEntryPanel entryPanel)
  { 
    this.entryPanel = entryPanel;
    entryPanel.addActionListener(this);

    itemQuit = new JMenuItem(QUIT);
    itemReset = new JMenuItem(RESET);

    // Check this part for the alt part
    itemQuit.setMnemonic('Q');
    itemReset.setMnemonic('R');

    //
    setupAccelerators();

    itemQuit.addActionListener(this);
    itemReset.addActionListener(this);

  }

  /**
   * Sets up keyboard shortcuts (accelerators) for the Quit and Reset menu items.
   * 
   * Ctrl + Q for quitting the application and Ctrl +R for resetting the grades.
   */
  private void setupAccelerators()
  {
    itemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    itemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
  }

  /**
   * ActionListener method that handles menu item actions. If the Quit or Exit menu item is
   * selected, the application closes. If the Reset menu item is selected, the grade entry panel is
   * reset.
   * 
   * @param e
   *          The ActionEvent triggered by a menu item selection
   */
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getSource().getClass() == JMenuItem.class)
    {
      String event = ((JMenuItem) e.getSource()).getText();
      switch (event)
      {
        case QUIT:
          System.exit(0);
          break;
        case EXIT:
          System.exit(0);
          break;
        case RESET:
          entryPanel.reset();
          break;
        default:
          break;
      }
    }
  }

  /**
   * Returns the Quit menu item so it can be added to the menu.
   * 
   * @return The quit JMenuItem
   */
  public JMenuItem getQuitItem()
  {
    return itemQuit;
  }

  /**
   * Returns the Reset menu item so it can be added to the menu.
   * 
   * @return The reset JMenuItem
   */
  public JMenuItem getResetItem()
  {
    return itemReset;
  }
}
