import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * Main class for the calculator project. Other classes are all access through here.
 * @author Josh Klein
 *
 */

public class CalcWindow extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728451083525013874L;

	/**
	 * numSet1- Panel for keys 0-3
	 * numSet2- Panel for keys 4-6
	 * numSet3- Panel for keys 7-9
	 * opSet- Panel for decimal point, parentheses, square root, and exponent keys
	 * opSet2- Panel for plus, minus (negation and subtraction compatible), multiplication, division, and equals keys
	 * varSet- Panel for x, y, z, n, t, and a variable keys
	 * trigSet- Panel for sin, cosine, tangent, cotangent, secant, and cosecant keys
	 * backspaceSet- Panel that holds two JSeparators and the backspace keys
	 */
	private static JPanel numSet1, numSet2, numSet3, opSet, funcSet, opSet2, varSet, trigSet, backspaceSet;
	
	/**
	 * buttonLabels- String array of labels for calculator buttons. Applied in loop.
	 */
	private static String[] buttonLabels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "(", ")", 
			"sqrt(", "^", "+", "-", "*", "/", "=", "sin(", "cos(", "tan(", "cot(", "sec(", "csc(", "x", "y", "z", "n", "t", 
			"a", "HIST", "VAR", "BASE CONV", "CLEAR"};
	
	/**
	 * inputField- Text field that will contain input as user presses keys
	 * outputField- Text field that will contain result of equation when user presses equals key
	 */
	public static JTextField inputField, outputField;
	
	/**
	 * backspace- Button for removing text from inputField one character at a time
	 */
	private static JButton backspace = new JButton("<-");
	
	/**
	 * input- Constantly updates as user presses keys
	 */
	public StringBuilder input = new StringBuilder();
	
	/**
	 * updatedInput- Contains text from input and sends contents to inputField
	 */
	public String updatedInput;
	
	/**
	 * var- New instance of Variables class
	 */
	private Variables var = new Variables();
	
	/**
	 * hist- New instance of History class
	 * NOTE: History does not properly function
	 */
	private History hist = new History();
	
	/**
	 * baseCon- New instance of BaseConvert class
	 */
	private BaseConvert baseCon = new BaseConvert();
	
	/**
	 * variables- Variable set for allowing variable function with Javaluator library
	 */
	final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
	
	/**
	 * variableValues- Variable list for assignments with variables Set
	 */
	public String variableValues[] = {"x", "y", "z", "n", "t", "a"};
	
	/**
	 * result- Contains solved equation from inputField which has been processed through ExtendedDoubleEvaluator
	 */
	private Double result;
	
	/**
	 * solution- Contains String form of result for sending to outputField
	 */
	private String solution;
	
	
	/**
	 * CalcWindow- Constructor method, sets up main window
	 */
	public CalcWindow() {
		//Sets layout for window
		super(new GridLayout(11,0));
		
		/*
		 * Panels assigned layouts and text fields given lengths
		 */
		numSet1 = new JPanel(new GridLayout(0,4));
		numSet2 = new JPanel(new GridLayout(0,3));
		numSet3 = new JPanel(new GridLayout(0,3));
		backspaceSet = new JPanel(new GridLayout(0,3));
		opSet = new JPanel(new GridLayout(0,5));
		opSet2 = new JPanel(new GridLayout(0,5));
		funcSet = new JPanel(new GridLayout(0,4));
		varSet = new JPanel(new GridLayout(0,6));
		trigSet = new JPanel(new GridLayout(0,6));
		inputField = new JTextField(32);
		inputField.setEditable(false);
		inputField.setBackground(Color.WHITE);
		outputField = new JTextField(32);
		outputField.setEditable(false);
		outputField.setBackground(Color.WHITE);
		
		/*
		 * Submenus created but remain invisible
		 */
		var.main(null);
		hist.main(null);
		baseCon.main(null);
		
		/*
		 * Buttons are initialized and given labels, ActionCommands, and ActionListeners
		 */
		for (int i = 0; i < buttonLabels.length; i++) {
			JButton addedButton = createButton(buttonLabels[i], i);
			if (i >= 0 && i <= 3) {
				numSet1.add(addedButton);
			}
			
			if (i >= 4 && i <= 6) {
				numSet2.add(addedButton);
			}
			
			if (i >= 7 && i <= 9) {
				numSet3.add(addedButton);
			}
			
			if (i >= 10 && i <= 14) {
				opSet.add(addedButton);
			}
			
			if (i >= 15 && i <= 19) {
				opSet2.add(addedButton);
			}
			
			if (i >= 20 && i <= 25) {
				trigSet.add(addedButton);
			}
			if (i >= 26 && i <= 31) {
				varSet.add(addedButton);
			}
			if (i >= 32 && i <= 35) {
				funcSet.add(addedButton);
				
				if (i == 32) {
					addedButton.setEnabled(false);
					addedButton.setToolTipText("To be included in future release.");
				}
			}
			addedButton.setActionCommand(buttonLabels[i]);
			addedButton.addActionListener(this);
		}
		
		/*
		 * Backspace panel created separately due to different format
		 */
		backspaceSet.add(new JSeparator());
		backspaceSet.add(new JSeparator());
		backspace.setActionCommand("<-");
		backspace.addActionListener(this);
		backspaceSet.add(backspace);
		
		/*
		 * Panels added to windows
		 */
		add(inputField);
		add(backspaceSet);
		add(outputField);
		add(numSet1);
		numSet1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(numSet2);
		add(numSet3);
		add(opSet);
		add(opSet2);
		add(trigSet);
		add(varSet);
		add(funcSet);
		
		/*
		 * Text fields formatted and given default
		 */
		inputField.setFont(inputField.getFont().deriveFont(Font.PLAIN, 24f));
		inputField.setHorizontalAlignment(SwingConstants.RIGHT);
		outputField.setFont(outputField.getFont().deriveFont(Font.PLAIN, 24f));
		outputField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Window given space around buttons and fields
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * createWindow- Main window created and given attributes
	 */
	private static void createWindow() {
		JFrame window = new JFrame("Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new CalcWindow();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setSize(640, 500);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	/**
	 * main- Called at start, allows window creation
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}
	
	/**
	 * createButton- Used in loop to create buttons. Font changed depending on what button's label is
	 * @param label- Text for button, pulled from buttonLabels
	 * @param indexInList- Position in buttonLabels, used to decode how to format text
	 * @return button- Button which will print dtext based on label
	 */
	public JButton createButton(String label, int indexInList) {
		JButton button = new JButton(label);
		//Buttons before extra functions
		if (indexInList < 32) {
			button.setFont(button.getFont().deriveFont(Font.PLAIN, 24f));
		}
		//Extra function buttons
		else {
			button.setFont(button.getFont().deriveFont(Font.BOLD, 14f));
		}
		button.setVisible(true);
		button.setActionCommand(label);
		return button;
	}
	
	/**
	 * actionPerformed- Peforms action based on button pressed by user
	 * @param e- Action received from button press
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Backspace one character at a time from inputField
		if (e.getActionCommand().equals("<-")) {
			input.deleteCharAt(input.length() -1);
			updatedInput = input.toString();
			inputField.setText(updatedInput);
		}
		
		//Index of button found in buttonLabels
		for (int i = 0; i < 36; i ++) {
			
			//Action performed based on index in buttonLabels
			if (e.getActionCommand().equals(buttonLabels[i])) {
				
				//Digits, decimal point, operators, and functions sent to inputField
				if ((i >= 0 && i <= 18) || (i >= 20 && i <= 25)) {
					
					//outputField's text is brought to inputField
					if (outputEmpty(outputField) == false && (i >= 14 && i <= 18)) {
						input = new StringBuilder();
						input.append(outputField.getText());
						outputField.setText("");
					}
					
					//inputField starts fresh input
					else if (outputEmpty(outputField) == false && inputEmpty(inputField) == false) {
						input = new StringBuilder();
						outputField.setText("");
					}
					input.append(buttonLabels[i]);
					updatedInput = input.toString();
					inputField.setText(updatedInput);
				}
				
				//Equals key is pressed
				if (i == 19) {
					updatedInput = input.toString();
					
					//If input is valid, the equation is solved
					try {
						result = new ExtendedDoubleEvaluator().evaluate(updatedInput, variables);
						solution = result.toString();
						solution = solution.indexOf(".") < 0 ? solution : solution.replaceAll("0*$", "").replaceAll("\\.$", "");
						if (solution.equals("NaN")) {
							solution = "Error";
						}
					}
					
					//ExtendedDoubleEvaluator is given invalid input
					catch (IllegalArgumentException error){
						solution = "Error";
					}
					
					/*outputField displays answer and answer is pushed to solution list in History
					 * NOTE: History does not properly function
					 */
					outputField.setText(solution);
					hist.solutions.add(solution);
					hist.stackDisplay.setText(hist.solutions.peek());
				}
				
				//Variable values are obtained and added to input
				if (i >= 26 && i <= 31) {
					 variables.set(variableValues[i-26], var.vars[i-26]);
					 input.append(buttonLabels[i]);
					 updatedInput = input.toString();
					 inputField.setText(updatedInput);
				}
				
				//History menu is made visible
				if (i == 32) {
					hist.makeVisible();
				}				
				
				//Variable menu is made visible
				if (i == 33) {
					var.makeVisible();
				}
				
				//Base conversion menu is made visible
				if (i == 34) {
					baseCon.makeVisible();
				}
				
				//Text fields are emptied
				if (i == 35) {
					input = new StringBuilder();
					inputField.setText("");
					outputField.setText("");
				}
			}
		}
	}
	
	/**
	 * outputEmpty- Checks if outputField currently contains text
	 * @param output- outputField, either has content or does not
	 * @return true- if outputField is empty; false- if outputField has text
	 */
	private boolean outputEmpty(JTextField output) {
		if (output.getText().equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * inputEmpty- Checks if outputField currently contains text
	 * @param input- inputField, either has content or does not
	 * @return true- if inputField is empty; false- if inputField has text
	 */	
	private boolean inputEmpty(JTextField input) {
		if (input.getText().equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
}