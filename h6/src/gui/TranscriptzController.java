package gui;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class TranscriptzController
{
  public static final String FILE = "File";
  public static final String GRADES = "Grades";
  public static final String QUIT = "Quit";
  public static final String RESET = "Reset";

  private CompositeGradeEntryPanel entryPanel;

  public TranscriptzController(CompositeGradeEntryPanel entryPanel)
  {
    this.entryPanel = entryPanel;
  }

  public void actionPerformed(ActionEvent evt)
  {
    String command = evt.getActionCommand();

    switch (command)
    {
      case QUIT:
        System.exit(0);
        break;

      case RESET:
        entryPanel.reset();
        break;

      default:
        JOptionPane.showMessageDialog(null, "Unknown command: " + command);
    }
  }
}
