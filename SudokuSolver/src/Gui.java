import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
public class Gui extends JFrame{
	Sudoku s;
	ArrayList<ArrayList<JNumberTextField>> numFields;
	// new gui to have 81 text fields max input 0ne character, and only numbers. 
	// each text field to assign value to their corresponding cell
	
	// loop creating all textfields and assigning them cells.
	public void initializeNumFields() {
		this.numFields = new ArrayList<ArrayList<JNumberTextField>>();
		for(int y = 0; y < 9; y++) {
			ArrayList<JNumberTextField> row = new ArrayList<JNumberTextField>();
			for(int x = 0; x < 9; x++) {
				Cell c = s.getCellByXY(x, y);
				JNumberTextField box = new JNumberTextField(c);
				box.setPreferredSize(new Dimension(20,20));
				row.add(box);
			}
			numFields.add(row);
		}
	}
	
	public void inputOnGui() { 
		JPanel p = new JPanel(new GridLayout(9, 9));
		
		for(int y = 0 ; y < 9; y++) {
			for(int x = 0; x<9 ; x++) {
				p.add(numFields.get(y).get(x));
			}
		}
		this.inputPanel.add(p);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		//inputTxt = new JNumberTextField(); no longer working because it now needs a cell input
		addBtn = new JButton("add Line");
		solveBtn = new JButton("solve!");
		
		// edit object properties 
		//inputTxt.setPreferredSize(new Dimension(150,20));
		
		
		// add objects to frame
		this.getContentPane().add(panel);
		panel.add(inputPanel,BorderLayout.NORTH);
		panel.add(outputPanel, BorderLayout.CENTER);
		inputPanel.add(inputLabel);
		//inputPanel.add(inputTxt);
		inputPanel.add(addBtn);
		inputPanel.add(solveBtn);
		outputPanel.add(original, BorderLayout.WEST);
		outputPanel.add(solved);
		
		
		// add event listeners.
		ActionListener myListener = new MyActionListener();
		addBtn.addActionListener(myListener);
		solveBtn.addActionListener(myListener);
		/*inputTxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addLineToDisplay();				
			}
		});*/		
		initializeNumFields();
		inputOnGui();
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
	public void showResult() {
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
		solved.setText("");
		JFrame alertBox = new JFrame();
		JOptionPane.showMessageDialog(alertBox, "this is an alertBox with your solution\n" + f);
		
		
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

			if(e.getSource()==solveBtn) {
				s.solve();
				
				showResult();
			}
		}
		
	}


	private class JNumberTextField extends JTextField{
		Cell myCell;
		JNumberTextField thisTxt;
		private JNumberTextField(Cell c) {
			myCell = c; 
			thisTxt = this;
			this.setHorizontalAlignment(JNumberTextField.CENTER);
			//add focuslistener so function happens when focus is gained/lost
			//setFocusable(true);
			//addFocusListener();
			this.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					if(thisTxt.getText().length() > 0)
						myCell.number = Integer.parseInt(thisTxt.getText());
					int x = 0;
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		@Override
		public void processKeyEvent(KeyEvent ev) {
			/*if(KeyEvent.VK_ENTER == ev.getKeyCode()) {
				addLineToDisplay();
			}*/
			if(Character.isDigit(ev.getKeyChar())) {
				
				if(this.getText().length()<1) { // only allows one character to be input
					super.processKeyEvent(ev);
				}
				
			}
			else if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				if(this.getText().length()>0) // just gets rid of the annoying baloop that windows gives you when deleting an empty field
				super.processKeyEvent(ev);
			}
			ev.consume();
			return;	
		}
		
		
	}
}






















