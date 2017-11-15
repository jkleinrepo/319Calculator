import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author Josh Klein
 *
 */

public class CalcWindow extends JPanel implements ActionListener{

	//Panels that hold either buttons or text fields
	private static JPanel numSet1, numSet2, numSet3, nums, opSet, funcSet, extraPanel, opSet2, fieldPanel;
	
	//Buttons for digits, operators, and extra functions
	private static JButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nDec, nPN, nParen, nFact, nPlus, nMinus, nMult, nDiv, nEqu, nHist, nVar, nTrig, nBCon,
			nClear;
	
	//Array of buttons that is looped through for assignments
	private static JButton[] buttonIDs = {n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nDec, nPN, nParen, nFact, nPlus, nPlus, nMinus, nMult, nDiv, nEqu, nHist, nVar, nTrig, nBCon,
			nClear};
	
	//Array of Strings that buttons are labeled with. Assigned in loop when buttons are initialized
	private static String[] buttonLabels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "±"," ()", "!", "+", "-", "×", "÷", "=", "HIST",
			"VAR", "TRIG", "BASE CONV", "CLEAR"};
	
	//textField is editable, outputField will contain in-progress equations and results
	private static JTextField textField, outputField;
	
	//input filled with equation as user types
	public StringBuilder input = new StringBuilder();
	
	//Made from input and sent to textField
	public String updatedInput;
	
	//Buttons for all functions are created
	private static Buttons b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDec, bPN, bParen, bFact, bPlus, bMinus, bMult, bDiv, bEqu, bHist, bVar, bTrig, bBCon,
	bClear;
	
	//Buttons placed into arrya for initialization
	private static Buttons[] buttons = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDec, bPN, bParen, bFact, bPlus, bMinus, bMult, bDiv, bEqu, bHist, bVar, bTrig, bBCon,
			bClear};
	
	//New instance of Variable menu class
	private Variables var = new Variables();
	
	/*
	 * Constructor for calculator window
	 */
	public CalcWindow() {
		super(new BorderLayout());
		
		/*
		 * Panels assigned layouts and field given lengths
		 */
		numSet1 = new JPanel(new GridLayout(0,4));
		numSet2 = new JPanel(new GridLayout(0,3));
		numSet3 = new JPanel(new GridLayout(0,3));
		nums = new JPanel(new GridLayout(3,0));
		opSet = new JPanel(new GridLayout(0,4));
		opSet2 = new JPanel(new GridLayout(0,5));
		funcSet = new JPanel(new GridLayout(0,5));
		extraPanel = new JPanel(new GridLayout(3,0));
		textField = new JTextField(32);
		outputField = new JTextField(32);
		outputField.setEditable(false);
		outputField.setBackground(Color.WHITE);
		fieldPanel = new JPanel(new GridLayout(3,0));
		var.main(null);//Variable menu is created, but remains invisible
		
		/*
		 * Buttons are initialized and given labels, ActionCommands, and ActionListeners
		 */
		for (int i = 0; i < buttonLabels.length; i++) {
			buttons[i] = new Buttons(buttonIDs[i], buttonLabels[i], textField);
			if (i >= 0 && i <= 3) {
				numSet1.add(buttons[i].button);
			}
			
			if (i >= 4 && i <= 6) {
				numSet2.add(buttons[i].button);
			}
			
			if (i >= 7 && i <= 9) {
				numSet3.add(buttons[i].button);
			}
			
			if (i >= 10 && i <= 13) {
				opSet.add(buttons[i].button);
			}
			
			if (i >= 14 && i <= 18) {
				opSet2.add(buttons[i].button);
			}
			
			if (i >= 19 && i <= 23) {
				funcSet.add(buttons[i].button);
			}
			buttons[i].button.setActionCommand(buttonLabels[i]);
			buttons[i].button.addActionListener(this);
		}
		
		/*
		 * Sub-panels added to main panels
		 */
		fieldPanel.add(textField, BorderLayout.NORTH);
		fieldPanel.add(new JSeparator());
		fieldPanel.add(outputField, BorderLayout.SOUTH);
		nums.add(numSet1, BorderLayout.NORTH);
		nums.add(numSet2, BorderLayout.CENTER);
		nums.add(numSet3, BorderLayout.SOUTH);
		nums.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		extraPanel.add(opSet, BorderLayout.NORTH);
		extraPanel.add(opSet2, BorderLayout.CENTER);
		extraPanel.add(funcSet, BorderLayout.SOUTH);
		
		/*
		 * Text field formatted and given default
		 */
		textField.setFont(textField.getFont().deriveFont(Font.PLAIN, 24f));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0");
		
		/*
		 * Main panels added to window
		 */
		add(fieldPanel, BorderLayout.NORTH);
		add(nums, BorderLayout.CENTER);
		add(extraPanel, BorderLayout.SOUTH);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
	}
	
	/*
	 * Window is created
	 */
	private static void createWindow() {
		JFrame window = new JFrame("Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new CalcWindow();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setSize(640, 400);
		window.setVisible(true);
	}
	
	/*
	 * Calculator program started
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}
	
	/*
	 * Buttons respond based on user input
	 */
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * Base conversion menu opened
		 */
		if (e.getActionCommand().equals("BASE CONV")) {
			BaseConvert bc = new BaseConvert();
			bc.main(null);
		}
		
		/*
		 * Text field is set to default
		 */
		if (e.getActionCommand().equals("CLEAR")) {
			input = new StringBuilder();
			textField.setText("0");
		}
		
		/*
		 * Variable menu is made visible
		 */
		if (e.getActionCommand().equals("VAR")) {
			var.makeVisible();
		}
		
		/*
		 * Digit keys sent to text field
		 */
		//TODO operators
		for (int i = 0; i < 23; i ++) {
			if (e.getActionCommand().equals(buttonLabels[i])) {
				if ((i >= 0 && i <= 10) || (i >= 13 && i <= 17)) {
				input.append(buttonLabels[i]);
				updatedInput = input.toString();
				textField.setText(updatedInput);
				}
				if (i == 11) {
					
				}
			}
		}
		
		/*
		 * Currently a test case. The pos/neg key will later be used for expected purpose.
		 * The key should print the x value from the variable menu, but the function is incomplete
		 */
		if (e.getActionCommand().equals("±")) {
			long x = var.getXVal();
			input.append(Long.toString(x));
			updatedInput = input.toString();
			textField.setText(updatedInput);
		}
	}	
}