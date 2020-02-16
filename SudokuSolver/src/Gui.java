import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class Gui extends JFrame{
	// initialize class variables 
	public static Gui gui;
	JPanel panel;
	JPanel inputPanel;
	JPanel outputPanel;
	JLabel inputLabel;
	JLabel original;
	JLabel solved;
	JTextField inputTxt;
	JButton addBtn;
	JButton solveBtn;
	Sudoku s;
	
	public Gui() {
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setup();
		this.setVisible(true);
	}
	
	public void setup() {
		// instantiate variables
		s = new Sudoku();
		panel = new JPanel(new BorderLayout());
		inputPanel = new JPanel();
		outputPanel = new JPanel(new BorderLayout());
		inputLabel = new JLabel("type out a line");
		original = new JLabel("");
		solved = new JLabel("");
		inputTxt = new JNumberTextField();
		addBtn = new JButton("add Line");
		solveBtn = new JButton("solve!");
		
		// edit object properties 
		inputTxt.setPreferredSize(new Dimension(150,20));
		
		
		// add objects to frame
		this.getContentPane().add(panel);
		panel.add(inputPanel,BorderLayout.NORTH);
		panel.add(outputPanel, BorderLayout.CENTER);
		inputPanel.add(inputLabel);
		inputPanel.add(inputTxt);
		inputPanel.add(addBtn);
		inputPanel.add(solveBtn);
		outputPanel.add(original, BorderLayout.WEST);
		outputPanel.add(solved);
		
		
		// add event listeners.
		ActionListener myListener = new MyActionListener();
		addBtn.addActionListener(myListener);
		solveBtn.addActionListener(myListener);
		inputTxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addLineToDisplay();				
			}
		});		
		
	}
	/*
	 * need to change how all this works so that it'll work on other labels. not just the solution label
	 */
	public void printSudokuLine(String row, JLabel l) {
		String[] s = row.split("(?<=\\G...)");
		String result = "";
		if(l.getText().length() == 0 ) {
			result = "<html><p>";
		}
		for(String i : s) {
			i = i.replaceAll(".(?!$)", "$0 | ");
			result += i;
			result += " &nbsp&nbsp&nbsp ";
		}
		result +="<br/>";
		result += "</p></html>";
		System.out.print(l.getText().length());
		if(l.getText().length() > 0) {
			result = l.getText().replaceAll("</p></html>",result);
		}
		System.out.print(result);
		int lineCount = result.split("<br/>").length;
		if(lineCount == 4 || lineCount == 8) {
			result = 
					result.replaceAll("</p></html>", "  <br/></p></html>");
		}
		if(lineCount <= 12) {
			l.setText(result);
			if (l == original )this.s.addFromInput(inputTxt.getText());	
		}
		else {
			System.out.println("\nthere are already 9 lines, click solve");
		}
		if(lineCount == 13 && l == original) {
			// show solution button or make it available somehow
			
		}
	}
	
	
	// method to convert solved solution to string so it can be passed to method that will display it
	public void resultToString() {
		this.s.convertTo2dArray();
		String outputLine = "";
		for(int[] y : s.sudoku) {
			outputLine = "";
			for(int x : y) {
				outputLine += x;
			}
			printSudokuLine(outputLine, solved);
		}
		String f = solved.getText();
		JFrame alertBox = new JFrame();
		JOptionPane.showMessageDialog(alertBox, "this is an alertBox\n" + f);
		solved.setText("");
		
	}
	
	public void addLineToDisplay() {
	// send the line to a function that adds the line to the array of lines
		if(inputTxt.getText().length() == 9) {
			printSudokuLine(inputTxt.getText(),original);
			inputTxt.setText("");
			
		}
		else {
			System.out.print("please enter 9 digits without spaces");
		}
		System.out.println("this is displayed when you press the button");
	}
	
	// to do: 
	// make an event handler 
	// make a function to take the input from text box 
	// ensure the function is only triggered when add button is pressed.
	// functions should operate in a similar fashion to the sudoku.takeInput function
	// have the window print out each line in a sudoku format when add is pressed.
	// add a delete line button
	// add a solve button when the ninth line has been entered.
	// have the solution display in an alertBox fashion
	
	//// messagae ////
	
	
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addBtn) {
				addLineToDisplay();
			}
			if(e.getSource()==solveBtn) {
				s.solve();
				resultToString();
			}
		}
		
	}


	private class JNumberTextField extends JTextField{
		
		@Override
		public void processKeyEvent(KeyEvent ev) {
			if(KeyEvent.VK_ENTER == ev.getKeyCode()) {
				addLineToDisplay();
			}
			if(Character.isDigit(ev.getKeyChar()) || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				super.processKeyEvent(ev);
			}
			ev.consume();
			return;	
		}
		
	}
}






















