import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Opens menu for user to store variables that can be used in equations
 * @author Josh Klein
 */
public class Variables extends JPanel implements ActionListener{

	/**
	 * xVar- stores value of x variable
	 * yVar- stores value of y variable
	 * zVar- stores value of z variable
	 * nVar- stores value of n variable
	 * tVar- stores value of t variable
	 * aVar- stores value of a variable
	 */
	public static double xVar, yVar, zVar, nVar, tVar, aVar;
	
	/**
	 * vars- Double array containing variables, looped through to save values
	 */
	public static double[] vars = {xVar, yVar, zVar, nVar, tVar, aVar};
	
	/**
	 * xLabel- Marks field used for storing value in xVar
	 * yLabel- Marks field used for storing value in yVar
	 * zLabel- Marks field used for storing value in zVar
	 * nLabel- Marks field used for storing value in nVar
	 * tLabel- Marks field used for storing value in tVar
	 * aLabel- Marks field used for storing value in aVar
	 */
	private JLabel xLabel = new JLabel("x ="), yLabel = new JLabel("y ="), zLabel = new JLabel("z ="),
			nLabel = new JLabel("n ="), tLabel = new JLabel("t ="), aLabel = new JLabel("a =");
	
	/**
	 * xTF- Text field that displays values currently stored in xVar
	 * yTF- Text field that displays values currently stored in yVar
	 * zTF- Text field that displays values currently stored in zVar
	 * nTF- Text field that displays values currently stored in nVar
	 * tTF- Text field that displays values currently stored in tVar
	 * aTF- Text field that displays values currently stored in aVar
	 */
	private JTextField xTF = new JTextField("0"), yTF = new JTextField("0"), zTF = new JTextField("0"),
			nTF = new JTextField("0"), tTF = new JTextField("0"), aTF = new JTextField("0");
	
	/**
	 * fields- JTextField array containing variable values, looped through to save values
	 */
	private JTextField[] fields = {xTF, yTF, zTF, nTF, tTF, aTF};
	
	/**
	 * OK- Saves values and hides window
	 * Clear- Wipes all values and returns text fields to default
	 */
	private JButton OK = new JButton("OK"), Clear = new JButton("Clear");
	
	/**
	 * varPanel- Holds variable labels and text fields
	 * buttonPanel - Holds OK and Clear buttons
	 */
	private JPanel varPanel = new JPanel(new GridLayout(2,6)), buttonPanel = new JPanel(new GridLayout(2,0));
	
	/**
	 * window- JDialog holding variable menu
	 */
	private static JDialog window;
	
	/**
	 * Variables- Constructor for setting up window
	 */
	public Variables() {
		
		//Window given layout
		super(new GridLayout(2,0));
		
		//Labels and fields added to varPanel
		varPanel.add(xLabel);
		varPanel.add(xTF);
		varPanel.add(yLabel);
		varPanel.add(yTF);
		varPanel.add(zLabel);
		varPanel.add(zTF);
		varPanel.add(nLabel);
		varPanel.add(nTF);
		varPanel.add(tLabel);
		varPanel.add(tTF);
		varPanel.add(aLabel);
		varPanel.add(aTF);
		
		//Buttons receive ActionCommands and ActionListeners, then added to buttonPanel
		OK.setActionCommand("OK");
		Clear.setActionCommand("Clear");
		OK.addActionListener(this);
		Clear.addActionListener(this);
		buttonPanel.add(OK);
		buttonPanel.add(Clear);
		
		//Panels added to window
		add(varPanel);
		add(buttonPanel);
	}

	/**
	 * actionPerformed- Saves values and hides window or clears all values
	 * @param e- Action received from button press
	 */
	public void actionPerformed(ActionEvent e) {
		
		//OK is pressed, values are saved based on text fields, and window becomes invisible
		if (e.getActionCommand().equals("OK")) {
			for (int i = 0; i < vars.length; i++) {
				vars[i] = Double.parseDouble(fields[i].getText());
				window.setVisible(false);
			}
		}
		
		//Clear is pressed, values become 0, and text field set to default
		else if (e.getActionCommand().equals("Clear")){
			for (int i = 0; i < vars.length; i++) {
				vars[i] = 0;
				fields[i].setText("0");
			}
		}
	}	
	
	/**
	 * createWindow- Sets up window and attributes of window
	 */
	private static void createWindow() {
		window = new JDialog(window, "Variables");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setModal(true);
		
		JComponent newContentPane = new Variables();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setModalityType(ModalityType.APPLICATION_MODAL);
		window.pack();
		window.setVisible(false);
	}
	
	/**
	 * main- Called in CalcWindow, allows windows creation
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
	public static void makeVisible () {
		window.setVisible(true);
	}
}