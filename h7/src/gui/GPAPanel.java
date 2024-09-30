package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GPAPanel is responsible for displaying the GPA based on grade history.
 * It listens for actions and updates the GPA label accordingly.
 */
public class GPAPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private static final String GPA = "GPA: ";

  CompositeGradeSubject subject;
  JLabel gpaLabel;

  /**
   * Constructor for GPAPanel. Initializes the GPA display with a default value of 4.000000.
   */
  public GPAPanel()
  {
    gpaLabel = new JLabel(GPA + 4.000000);
    add(gpaLabel);
  }

  /**
   * Sets the subject that provides the grade history.
   * 
   * @param sub The CompositeGradeSubject providing the grade history
   */
  public void setCompositeGradeSubject(final CompositeGradeSubject sub)
  {
    this.subject = sub;
  }

  @Override
  public void actionPerformed(final ActionEvent evt)
  {
    Double number = subject.getGradeHistory().getValue();
    DecimalFormat decimal = new DecimalFormat();
    decimal.setMaximumFractionDigits(6);
    decimal.setMinimumFractionDigits(6);

    if (number != null)
    {
      gpaLabel.setText(GPA + decimal.format(number));
    }
    else
    {
      gpaLabel.setText(GPA + decimal.format(0.0));
    }
  }
}
