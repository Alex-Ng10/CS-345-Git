import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A numeric keypad
 */
public class NumberPad extends JPanel
{
  private static final Font BUTTON_FONT = new Font("Dejavu Sans", Font.PLAIN, 12);
  private ActionListener listener;
  
  /**
   * Default Constructor
   */
  public NumberPad(ActionListener listener)
  {
    super();
    this.listener = listener;
    setupLayout();
  }

  private void addButton(String text)
  {
    JButton button = new JButton(text);
    button.setFont(BUTTON_FONT);
    button.addActionListener(listener);
    add(button);


  }

  /**
   * Setup and layout this NumberPad
   */

  private void setupLayout()
  {
    JButton button;
    setLayout(new GridLayout(4, 3));
    for (int i = 1; i <= 9; i++)
    {
      addButton(String.format("%1d", i));
    }

    button = new JButton("\u232B");
    add(button);

    button = new JButton("0");
    add(button);

    button = new JButton("C");
    add(button);
    // Setup and layout this NumberPad
  }
  
}
