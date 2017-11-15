import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Josh Klein
 *
 */
public class Variables extends JPanel implements ActionListener{

	/*
	 *Long variables intended to store values that user chooses 
	 */
	private static long xVar;
	public long yVar = 0;
	public long zVar = 0;
	public long nVar = 0;
	public long tVar = 0;
	public long aVar = 0;
	public long[] vars = {xVar, yVar, zVar, nVar, tVar, aVar};//Array meant to be looped through to save values. Not otherwise accessed
	
	/*
	 * Labels mark text fields. Inform users which field operates on which variable
	 */
	private JLabel xLabel = new JLabel("x =");
	private JLabel yLabel = new JLabel("y =");
	private JLabel zLabel = new JLabel("z =");
	private JLabel nLabel = new JLabel("n =");
	private JLabel tLabel = new JLabel("t =");
	private JLabel aLabel = new JLabel("a =");
	
	/*
	 * Text field initialization. Default to 0; will return 0 if user inputs no values
	 * or clears values
	 */
	private JTextField xTF = new JTextField("0");
	private JTextField yTF = new JTextField("0");
	private JTextField zTF = new JTextField("0");
	private JTextField nTF = new JTextField("0");
	private JTextField tTF = new JTextField("0");
	private JTextField aTF = new JTextField("0");
	private JTextField[] fields = {xTF, yTF, zTF, nTF, tTF, aTF};//Array meant to loop through to save field text. Not otherwise accessed
	
	/*
	 * Buttons for saving and clearing values
	 */
	private JButton OK = new JButton("OK");	
	private JButton Clear = new JButton("Clear");
	
	/*
	 * Panels that get added to the window. varPanel hold JLabels and JTextFields;
	 * buttonPanel holds OK and Clear buttons
	 */
	private JPanel varPanel = new JPanel(new GridLayout(2,6));
	private JPanel buttonPanel = new JPanel(new GridLayout(2,0));
	
	private static JFrame window;//Display window
	
	public Variables() {
		super(new BorderLayout());
		
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
		
		/*
		 * Buttons receive ActionCommands and ActionListeners, then added to buttonPanel
		 */
		OK.setActionCommand("OK");
		Clear.setActionCommand("Clear");
		OK.addActionListener(this);
		Clear.addActionListener(this);
		buttonPanel.add(OK);
		buttonPanel.add(Clear);
		
		/*
		 * Panels added to window
		 */
		add(varPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		
		/*
		 * OK is pressed, values are saved based on text fields, and window becomes invisible
		 */
		//TODO Make values actually save
		if (e.getActionCommand().equals("OK")) {
			for (int i = 0; i < vars.length; i++) {
				vars[i] = Long.parseLong(fields[i].getText());
				window.setVisible(false);
			}
		}
		
		//Test case for only xVar
		/*if (e.getActionCommand().equals("OK") && !xTF.getText().equals("0")) {
			xVar = Long.parseLong(xTF.getText());
			System.out.println(xVar);
			window.setVisible(false);
		}*/
		
		/*
		 * Clear is pressed, values become 0, and text field set ot default
		 */
		else if (e.getActionCommand().equals("Clear")){
			for (int i = 0; i < vars.length; i++) {
				vars[i] = 0;
				fields[i].setText("0");
			}
		}
	}	
	
	/*
	 * Window is initialized, but hidden. Made visible when VAR key in CalcWindow.java is pressed
	 */
	private static void createWindow() {
		window = new JFrame("Variables");
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JComponent newContentPane = new Variables();
		newContentPane.setOpaque(true);
		window.setContentPane(newContentPane);
		
		window.pack();
		window.setVisible(false);
	}
	
	/*
	 * Runs class
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}
	
	/*
	 * Makes Variable window visible, called when VAR key in CalcWindow.java is pressed
	 */
	public static void makeVisible () {
		window.setVisible(true);
	}
	
	/*
	 * Returns xVar value
	 */
	public long getXVal() {
		return xVar;
	}
}
