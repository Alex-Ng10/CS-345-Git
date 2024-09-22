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
  private static final String[] DEFAULT_COURSES = {"CS149", "CS159", "CS227", "CS240", 
      "CS261", "MATH235"};
  private static final Map<String,Double> MAP = new JMUCourseTable();
  
  private String[] args;
  
  /**
   * The entry point of the application.
   * 
   * @param args  The command-line arguments (which are ignored)
   * @throws InterruptedException If the system is interrupted
   * @throws InvocationTargetException If there is a problem starting the system
   */
  public static void main(final String[] args) throws InterruptedException, InvocationTargetException
  {
    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new TranscriptzH6(args));
  }
  
  /**
   * Explicit Value Constructor
   * 
   * @param args The command-line arguments
   */
  public TranscriptzH6(final String[] args)
  {
    // Store the command-line arguments in case they're needed
    this.args = args;
  }
  
//  public void run()
//  {
//    setLookAndFeel();
//    
//    JFrame frame = new JFrame("Transcriptz");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    JPanel contentPane = (JPanel)frame.getContentPane();
//    contentPane.setLayout(new BorderLayout());
//    
//    // Construct the various components
//    GPAPanel gpaPanel = new GPAPanel();
//    String[] courses;
//    if ((args == null) || (args.length == 0)) courses = DEFAULT_COURSES;
//    else courses = args;
//    CompositeGradeEntryPanel historyPanel = new CompositeGradeEntryPanel(courses, MAP);
//    
//    // Connect the various components
//    gpaPanel.setCompositeGradeSubject(historyPanel); 
//    historyPanel.addActionListener(gpaPanel);
//    
//    // Layout the GUI
//    contentPane.add(historyPanel, BorderLayout.NORTH);
//    contentPane.add(gpaPanel, BorderLayout.SOUTH);
//    
//    // Construct the controller
//    TranscriptzController controller = new TranscriptzController(historyPanel);
//    
//    // Add the menu
//    JMenuBar menuBar = new JMenuBar();
//    frame.setJMenuBar(menuBar);
//    
//    JMenu menu;
//    JMenuItem item;
//    
//    menu = new JMenu("File");
//    menuBar.add(menu);
//    item = new JMenuItem("Exit");
//    item.addActionListener(controller);
//    menu.add(item);
//
//    menu = new JMenu("Grades");
//    menuBar.add(menu);
//    item = new JMenuItem("Reset");
//    item.addActionListener(controller);
//    menu.add(item);
//    
//    
//    frame.setSize(600, 200);
//    frame.setVisible(true);
//  }
  
  public void run() {
    setLookAndFeel();

    JFrame frame = new JFrame("Transcriptz");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Construct the various components
    GPAPanel gpaPanel = new GPAPanel();
    String[] courses;
    if ((args == null) || (args.length == 0)) courses = DEFAULT_COURSES;
    else courses = args;
    CompositeGradeEntryPanel historyPanel = new CompositeGradeEntryPanel(courses, MAP);

    // Connect the components
    gpaPanel.setCompositeGradeSubject(historyPanel);
    historyPanel.addActionListener(gpaPanel);

    // Layout the GUI
    contentPane.add(historyPanel, BorderLayout.NORTH);
    contentPane.add(gpaPanel, BorderLayout.SOUTH);

    // Construct the controller
    TranscriptzController controller = new TranscriptzController(historyPanel);

    // Add the menu
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu menu;
    JMenuItem item;

    menu = new JMenu("File");
    menuBar.add(menu);
    item = new JMenuItem("Exit");
    item.addActionListener(controller);
    menu.add(item);

    menu = new JMenu("Grades");
    menuBar.add(menu);
    item = new JMenuItem("Reset");
    item.addActionListener(controller);
    menu.add(item);

    frame.setSize(600, 200);
    frame.setVisible(true);
}

  
  /**
   * Set the look and feel for the application.
   */
  private void setLookAndFeel()
  {
    // Setup the look and feel
    boolean done = false;
    try 
    {
      // Use Nimbus if it is available
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (int i=0; i<lfs.length && !done; i++)
      {
        if ("Nimbus".equals(lfs[i].getName())) 
        {
          UIManager.setLookAndFeel(lfs[i].getClassName());
          done = true;
        }
      }

      // If Nimbus isn't available, use the system look and feel
      if (!done)
      {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
      }
    } 
    catch (ClassNotFoundException cnfe)
    {
      // Use the default look and feel
    }
    catch(IllegalAccessException iae)
    {
      // Use the default look and feel
    }
    catch (InstantiationException ie)
    {
      // Use the default look and feel
    }
    catch (UnsupportedLookAndFeelException ulale)
    {
      // Use the default look and feel
    }
  }
}