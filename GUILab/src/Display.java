import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Display extends JLabel implements ActionListener
{

  private static final String CLEAR = "C";

  public Display()
  {
    super(" ");
    setBorder(BorderFactory.createEtchedBorder());
  }

  public void actionPerformed(ActionEvent ae)
  {
    String ac = ae.getActionCommand();
    if (ac.equals(CLEAR))
    {
      setText(" ");
    }
    else
    {
      setText(getText() + ac);
    }
  }
}
