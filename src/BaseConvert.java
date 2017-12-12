import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Opens up a base conversion window with a drop down menu for initial base, a text field,
 * and 4 buttons for determining result.
 * @author Josh Klein
 */
public class BaseConvert extends JPanel implements ActionListener{
	
	/**
	 * convOptions- Panel for buttons that perform conversions
	 */
	private JPanel convOptions;
	
	/**
	 * inputField- Text field for user to input values
	 * outputField- Text field for result of conversion
	 */
	private JTextField inputField, outputField;
	
	/**
	 * initVal- Drop down list for user to set initial base
	 */
	private JComboBox<String> initVal;
	
	/**
	 * bases- Array of strings that will fill initVal
	 */
	private String[] bases = {"Decimal", "Binary", "Hexadecimal", "Octal"};
	
	/**
	 * baseVals- Array of base values corresponding to base names in bases. Used to perform conversions
	 */
	private int[] baseVals = {10, 2, 16, 8};
	
	/**
	 * pos- Integer value to link base name and value
	 */
	public int pos;
	
	/**
	 * window- JDialog holding base conversion menu
	 */
	private static JDialog window;

	/**
	 * BaseConvert- Sets up conversion window and listeners
	 */
	public BaseConvert() {
		
		//Window given layout
		super(new GridLayout(4,0));
		
		//bases array assigned to drop down list and list is given default position
		initVal = new JComboBox<String>(bases);
		initVal.setSelectedIndex(0);
		
		//Buttons created and given labels
		JButton dec = new JButton(bases[0]);
		JButton bin = new JButton(bases[1]);
		JButton hex = new JButton(bases[2]);
		JButton oct = new JButton(bases[3]);
		
		//Buttons given base names as action commands, corresonds to base output
		dec.setActionCommand(bases[0]);
		bin.setActionCommand(bases[1]);
		hex.setActionCommand(bases[2]);
		oct.setActionCommand(bases[3]);
		
		//Text field given length
		inputField = new JTextField(32);
		outputField = new JTextField(32);
		outputField.setEditable(false);
		outputField.setBackground(Color.WHITE);
		outputField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		//Buttons assigned action listeners
		dec.addActionListener(this);
		bin.addActionListener(this);
		hex.addActionListener(this);
		oct.addActionListener(this);
		
		//Button panel initialized and given buttons
		convOptions = new JPanel(new GridLayout(1,0));
		convOptions.add(dec);
		convOptions.add(bin);
		convOptions.add(hex);
		convOptions.add(oct);
		
		//Drop down, text field, and button panel added to window, window given border
		add(initVal);
		add(inputField);
		add(outputField);
		add(convOptions);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * actionPerformed- Determines base based on button pressed. Hex values are ran through
	 * 					for loop to capitalize letters.
	 * @param e- Action received from button press
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Array indexes are aligned
		pos = baseVals[initVal.getSelectedIndex()];
		
		//Output string created, but remains empty
		String val = null;
		
		//Conversion performed based on base in drop down list and text in text field
		try {
		if (e.getActionCommand().equals("Decimal")) {
			val = Long.toString(Long.parseLong(inputField.getText(), pos));
		}
		if (e.getActionCommand().equals("Binary")) {
			val = Long.toBinaryString(Long.parseLong(inputField.getText(), pos));
		}
		if (e.getActionCommand().equals("Hexadecimal")) {
			val = Long.toHexString(Long.parseLong(inputField.getText(), pos));
			
			//Hex output has any present letters capitalized
			StringBuilder update = new StringBuilder();
			for (int m = 0; m < val.length(); m++) {
				update.append(Character.toUpperCase(val.charAt(m)));
			}
			val = update.toString();
		}
		if (e.getActionCommand().equals("Octal")) {
			val = Long.toOctalString(Long.parseLong(inputField.getText(), pos));
		}
		}
		
		catch (NumberFormatException error) {
			val = "Error";
		}
		
		//Text field displays converted value
		outputField.setText(val);
	}
	
	/**
	 * createWindow- Creates base conversion window
	 */
	private static void createWindow() {
		window = new JDialog(window, "Base Convert");
		window.setModal(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JComponent newContentPane = new BaseConvert();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setAlwaysOnTop(true);
		window.setModalityType(ModalityType.APPLICATION_MODAL);
		window.pack();
		window.setResizable(false);
		window.setVisible(false);
	}
	
	/**
	 * main- Called in CalcWindow, allows window creation
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}

	/**
	 * makeVisible- window is set to be visible
	 */
	public void makeVisible() {
		window.setVisible(true);
	}
}