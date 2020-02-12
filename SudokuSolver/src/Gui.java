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
	JLabel inputLabel;
	JLabel solution;
	JTextField inputTxt;
	JButton addBtn;
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
		panel = new JPanel();
		inputLabel = new JLabel("type out a line");
		solution = new JLabel("");
		inputTxt = new JNumberTextField();
		addBtn = new JButton("add Line");
		
		// edit object properties 
		inputTxt.setPreferredSize(new Dimension(150,20));
		
		// add objects to frame
		this.getContentPane().add(panel);
		panel.add(inputLabel);
		panel.add(inputTxt);
		panel.add(addBtn);
		panel.add(solution);
		
		
		// add event listeners.
		ActionListener myListener = new MyActionListener();
		addBtn.addActionListener(myListener);
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
	public void printSudokuLine(String row) {
		String[] s = row.split("(?<=\\G...)");
		String result = "";
		if(solution.getText().length() == 0 ) {
			result = "<html>";
		}
		for(String i : s) {
			result += i;
			result += "  ";
		}
		result +="<br/>";
		result += "</html>";
		System.out.print(solution.getText().length());
		if(solution.getText().length() > 0) {
			result = solution.getText().replaceAll("</html>",result);
		}
		System.out.print(result);
		int lineCount = result.split("<br/>").length;
		if(lineCount == 4 || lineCount == 7) {
			result = 
					result.replaceAll("</html>", "  <br/></html>");
		}
		if(lineCount <= 11) {
			solution.setText(result);
			this.s.addFromInput(inputTxt.getText());
			
		}
		else {
			System.out.println("\nthere are already 9 lines, click solve");
		}
		
	}
	
	public void addLineToDisplay() {
	// send the line to a function that adds the line to the array of lines
		if(inputTxt.getText().length() == 9) {
			printSudokuLine(inputTxt.getText());
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






















