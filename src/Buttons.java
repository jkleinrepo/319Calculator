import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * 
 * @author Josh Klein
 *
 */
public class Buttons{

	public JButton button;
	
	public Buttons (JButton ID, String label, JTextField textField) {
		button = new JButton();
		button.setSize(25,25);
		button.setText(label);
		if (isDigit(label) == true) {
			button.setFont(button.getFont().deriveFont(Font.PLAIN, 24f));
		}
		button.setVisible(true);
		button.setActionCommand(label);
	}
	
	public boolean isDigit(String label) {
		try {
			int digit = Integer.parseInt(label);
		}
		
		catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}