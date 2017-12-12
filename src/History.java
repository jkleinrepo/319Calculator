import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class to store solutions and equations
 * NOTE: Does not function properly
 * @author Josh Klein
 */
public class History extends JPanel implements ActionListener{
	
	/**
	 * scrollLeft- Scrolls backwards through solutions and equations
	 * scrollRight- Scroll forwards through solutions and equations
	 * clearStack- Deletes all values currently stored
	 */
	private JButton scrollLeft = new JButton("<"), scrollRight = new JButton(">"), clearStack = new JButton("Clear");
	
	/**
	 * stackDisplay- Shows currently selected solution
	 */
	public static JTextField stackDisplay;
	
	/**
	 * scrollButtons- Holds buttons to move through solutions
	 * displayPanel- Holds text field
	 * clearPanel- Holds button to delete stored solutions
	 */
	private JPanel scrollButtons, displayPanel, clearPanel;
	
	/**
	 * window- Holds history menu
	 */
	private static JDialog window;
	
	/**
	 * solutions- Holds solutions of equations from CalcWindow
	 */
	public static LinkedList<String> solutions = new LinkedList();
	
	/**
	 * itr- ListIterator to progress through solutions
	 */
	public ListIterator<String> itr;

	/**
	 * History- Constructor to set up window
	 */
	public History() {
		
		//Sets up layout for window
		super(new GridLayout(3,0));
		
		//Panels given layouts
		scrollButtons = new JPanel(new GridLayout(0,2));
		displayPanel = new JPanel(new GridLayout(0,1));
		clearPanel = new JPanel(new GridLayout(0,1));
		
		//Text field initialized
		stackDisplay = new JTextField(32);
		stackDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		stackDisplay.setEditable(false);
		stackDisplay.setBackground(Color.WHITE);
		
		//Buttons receive commands and listeners
		scrollLeft.setActionCommand("<");
		scrollRight.setActionCommand(">");
		clearStack.setActionCommand("Clear");
		scrollLeft.addActionListener(this);
		scrollRight.addActionListener(this);
		clearStack.addActionListener(this);
		
		//Navigation buttons added to panel
		scrollButtons.add(scrollLeft);
		scrollButtons.add(scrollRight);
		
		//Text field added to panel
		displayPanel.add(stackDisplay);
		
		//Clear button added to panel
		clearPanel.add(clearStack);
		
		//Panels added to window
		add(scrollButtons);
		add(displayPanel);
		add(clearPanel);
	}
	
	/**
	 * Window created and given attributes
	 */
	private static void createWindow() {
		window = new JDialog(window, "History");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setModal(true);
		
		JComponent newContentPane = new History();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setModalityType(ModalityType.APPLICATION_MODAL);
		window.pack();
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
	 * actionPerformed- Progresses through solutions or clears all depending on button pressed
	 * @param e- Action received from button press
	 */
	public void actionPerformed(ActionEvent e) {
		itr = solutions.listIterator(solutions.size() - 1);
		if (e.getActionCommand().equals(">")) {
			while (itr.hasNext()) {
				stackDisplay.setText(itr.next());
			}
		}
		if (e.getActionCommand().equals("<")) {
			while (itr.hasPrevious()) {
				stackDisplay.setText(itr.previous());
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			solutions.clear();
		}
	}
	
	/**
	 * makeVisible- window is set to be visible
	 */
	public void makeVisible() {
		window.setVisible(true);
	}
}