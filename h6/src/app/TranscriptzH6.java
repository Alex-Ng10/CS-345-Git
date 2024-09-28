package app;

import grading.*;
import gui.*;
import java.awt.*;
import java.lang.reflect.*;
import javax.swing.*;
import java.util.Map;

/**
 * Transciptz with a graphical user interface.
 * 
 * @author Ann. E. Koder, Sagacious Media
 * @version H6
 */
public class TranscriptzH6 implements Runnable
{
  private static final String[] DEFAULT_COURSES = {"CS149", "CS159", "CS227", "CS240", "CS261",
      "MATH235"};
  private static final Map<String, Double> COURSE_CREDITS = new JMUCourseTable();
  private String[] cmdArgs;

  /**
   * The main logic for setting up and displaying the GUI. Initializes the JFrame, grade entry
   * panel, GPA panel, and menu bar, and sets up event listeners.
   * @param args arguments
   */
  public TranscriptzH6(final String[] args)
  {
    this.cmdArgs = args;
  }
  
  /**
   * The entry point of the application. This method starts the Transcriptz GUI by scheduling it on
   * the event dispatch thread.
   * 
   * @param args
   *          The command-line arguments (ignored in this version)
   * @throws InterruptedException
   *           If the event dispatch thread is interrupted
   * @throws InvocationTargetException
   *           If an exception occurs while running the event dispatch thread
   */
  public static void main(final String[] args)
      throws InterruptedException, InvocationTargetException
  {
    SwingUtilities.invokeAndWait(new TranscriptzH6(args));
  }

  @Override
  public void run()
  {
    applyLookAndFeel();

    JFrame mainFrame = createMainFrame("Transcriptz");
    JPanel contentPane = (JPanel) mainFrame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Determine the courses to display
    String[] courses = (cmdArgs == null || cmdArgs.length == 0) ? DEFAULT_COURSES : cmdArgs;

    // Create panels
    GPAPanel gpaPanel = new GPAPanel();
    CompositeGradeEntryPanel gradeEntryPanel = new CompositeGradeEntryPanel(courses,
        COURSE_CREDITS);

    // Setup observer connection
    gpaPanel.setCompositeGradeSubject(gradeEntryPanel);
    gradeEntryPanel.addActionListener(gpaPanel);

    // Add panels to the content pane
    contentPane.add(gradeEntryPanel, BorderLayout.CENTER); 
    contentPane.add(gpaPanel, BorderLayout.SOUTH);

    // Initialize the controller and menu bar
    TranscriptzController controller = new TranscriptzController(gradeEntryPanel);
    JMenuBar menuBar = createMenuBar(controller);

    mainFrame.setJMenuBar(menuBar);
    mainFrame.setSize(600, 200);
    mainFrame.setVisible(true);
  }

  /**
   * Set the look and feel for the application.
   */
  private void applyLookAndFeel()
  {
    try
    {
      // Attempt to set Nimbus look and feel
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          UIManager.setLookAndFeel(info.getClassName());
          return;
        }
      }
      // Fallback to the system's default look and feel if Nimbus isn't available
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException ignore)
    {
      // Ignore exceptions without any action
    }
  }

  /**
   * Creates the main JFrame for the application.
   * 
   * @param title
   *          The title for the application window
   * @return The created JFrame object
   */
  private JFrame createMainFrame(final String title)
  {
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }

  /**
   * Creates the menu bar with File and Grades menus. Adds Quit and Reset options to their
   * respective menus.
   * 
   * @param controller
   *          The controller that handles menu actions
   * @return The created JMenuBar object
   */
  private JMenuBar createMenuBar(final TranscriptzController controller)
  {
    JMenuBar menuBar = new JMenuBar();

    // File menu with Quit option
    JMenu fileMenu = new JMenu(TranscriptzController.FILE);
    fileMenu.add(controller.getQuitItem());

    // Grades menu with Reset option
    JMenu gradesMenu = new JMenu(TranscriptzController.GRADES);
    gradesMenu.add(controller.getResetItem());

    // Add both menus to the menu bar
    menuBar.add(fileMenu);
    menuBar.add(gradesMenu);

    return menuBar;
  }
}
