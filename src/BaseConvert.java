/**
 * @author Josh Klein
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Opens up a base conversion window with a drop down menu for initial base, a text field,
 * and 4 buttons for determining result.
 * @author Josh Klein
 *
 */
public class BaseConvert extends JPanel implements ActionListener{
	
	private JPanel convOptions;
	private JTextField inputField;
	private JComboBox initVal;
	private String[] bases = {"Decimal", "Binary", "Hexadecimal", "Octal"};
	private int[] baseVals = {10, 2, 16, 8};
	public int pos;

	/**
	 * BaseConvert- Sets up conversion window and listeners
	 */
	public BaseConvert() {
		super(new BorderLayout());
		
		initVal = new JComboBox(bases);
		initVal.setSelectedIndex(0);
		
		JButton dec = new JButton(bases[0]);
		JButton bin = new JButton(bases[1]);
		JButton hex = new JButton(bases[2]);
		JButton oct = new JButton(bases[3]);
		
		dec.setActionCommand(bases[0]);
		bin.setActionCommand(bases[1]);
		hex.setActionCommand(bases[2]);
		oct.setActionCommand(bases[3]);
		
		inputField = new JTextField(32);
		
		dec.addActionListener(this);
		bin.addActionListener(this);
		hex.addActionListener(this);
		oct.addActionListener(this);
		
		convOptions = new JPanel(new GridLayout(1,0));
		convOptions.add(dec);
		convOptions.add(bin);
		convOptions.add(hex);
		convOptions.add(oct);
		
		add(initVal, BorderLayout.NORTH);
		add(inputField, BorderLayout.CENTER);
		add(convOptions, BorderLayout.SOUTH);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * actionPerformed- Determines base based on button pressed. Hex values are ran through
	 * 					for loop to capitalize letters.
	 * 
	 * pos- Determines base number by using baseVals array. Index is pulled from corresponding
	 * 		index in initVal array.
	 */
	public void actionPerformed(ActionEvent e) {
		pos = baseVals[initVal.getSelectedIndex()];
		String val = null;
		if (e.getActionCommand().equals("Decimal")) {
			val = Long.toString(Long.parseLong(inputField.getText(), pos));
		}
		if (e.getActionCommand().equals("Binary")) {
			val = Long.toBinaryString(Long.parseLong(inputField.getText(), pos));
		}
		if (e.getActionCommand().equals("Hexadecimal")) {
			val = Long.toHexString(Long.parseLong(inputField.getText(), pos));
			StringBuilder update = new StringBuilder();
			for (int m = 0; m < val.length(); m++) {
				update.append(Character.toUpperCase(val.charAt(m)));
			}
			val = update.toString();
		}
		if (e.getActionCommand().equals("Octal")) {
			val = Long.toOctalString(Long.parseLong(inputField.getText(), pos));
		}
		inputField.setText(val);
	}
	
	/**
	 * createWindow- Creates base conversion window. On close, only base conversion window is closed,
	 * 				 while main calculator window stays open.
	 */
	private static void createWindow() {
		JFrame window = new JFrame("Base Conversion");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JComponent newContentPane = new BaseConvert();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.pack();
		window.setVisible(true);
	}
	
	/**
	 * main- Called when BASE CONV button is pressed to allow access to base conversion function.
	 *
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}
}