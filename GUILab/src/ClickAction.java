/**
 * 
 */

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * 
 */
public class ClickAction extends AbstractAction
{
  private JButton button;
  
  public ClickAction(JButton button) {
    super();
    this.button = button;
  }

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    button.grabFocus();
    button.doClick(50);
  }
}
