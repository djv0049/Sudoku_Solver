import java.awt.BorderLayout;
import java.awt.Color;
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
	// gui to have 81 text fields max input 0ne character, and only numbers. 
	// each text field to assign value to their corresponding cell
	
	// initialize class variables 
	public static Gui gui;
	Sudoku s;
	ArrayList<ArrayList<JNumberTextField>> numFields;
	ArrayList<JNumberTextField> allmyTextFields;
	JPanel panel;
	JPanel inputPanel;
	JPanel outputPanel;
	JLabel inputLabel;
	JLabel solved;
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
		solved = new JLabel("");
		solveBtn = new JButton("solve!");
		// add objects to frame
		this.getContentPane().add(panel);
		panel.add(inputPanel,BorderLayout.NORTH);
		panel.add(outputPanel, BorderLayout.CENTER);
		inputPanel.add(inputLabel);
		inputPanel.add(solveBtn);
		outputPanel.add(solved);
		// add event listeners.
		ActionListener myListener = new MyActionListener();
		solveBtn.addActionListener(myListener);		
		
		initializeNumFields();
		inputOnGui();
	}
	// loop creating all textfields and assigning them cells.	
	public void initializeNumFields() {
		this.numFields = new ArrayList<ArrayList<JNumberTextField>>();
		allmyTextFields = new ArrayList<Gui.JNumberTextField>();
		for(int y = 0; y < 9; y++) {
			ArrayList<JNumberTextField> row = new ArrayList<JNumberTextField>();
			for(int x = 0; x < 9; x++) {
				Cell c = s.getCellByXY(x, y);
				JNumberTextField box = new JNumberTextField(c);
				box.myCell.myTextField = box;
				box.setPreferredSize(new Dimension(20,20));
				row.add(box);
				allmyTextFields.add(box);
				setTextFieldColor(box, x,y);
			}
			numFields.add(row);
		}
	}
	
	public void inputOnGui() {  // Add all text fields to panels
		JPanel p = new JPanel(new GridLayout(9, 9));
		for(int y = 0 ; y < 9; y++) {
			for(int x = 0; x<9 ; x++) {
				p.add(numFields.get(y).get(x));
			}
		}
		this.inputPanel.add(p);
	}
	public void setTextFieldColor(JNumberTextField box, int x, int y){
		if(((x < 3 || x > 5) && (y < 3 || y > 5 )) || ((x < 6 && x > 2) && (y < 6 && y > 2))){ // should get the outside corner boxes
			box.setBackground(Color.LIGHT_GRAY);
		}
	}
		
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==solveBtn) {
				s.solve();
				JFrame alertBox = new JFrame();
				JOptionPane.showMessageDialog(alertBox, s.print());
			}
		}
	}

	private class JNumberTextField extends JTextField{ // new class to cover number only entry on specific feilds
		Cell myCell;
		JNumberTextField thisTxt;
		private JNumberTextField(Cell c) {
			myCell = c; 
			thisTxt = this;
			this.setHorizontalAlignment(JNumberTextField.CENTER);
			this.addFocusListener(new FocusListener() { // sets myCell to the text value when focus shifts
				@Override
				public void focusLost(FocusEvent e) {
					if(thisTxt.getText().length() > 0)
						myCell.number = Integer.parseInt(thisTxt.getText());
					else if(thisTxt.getText().length() == 0) {
						myCell.number = 0;
					}
				}
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated 
				}
			});
		}
		
		@Override
		public void processKeyEvent(KeyEvent ev) {
			if(Character.isDigit(ev.getKeyChar())) {
				if(this.getText().length()<1) { // only allows one character to be input
					super.processKeyEvent(ev);
					if(ev.getID() == KeyEvent.KEY_TYPED) // 400 -- i wanted to use the release ID for this but apparently that doesnt occur???/
					this.transferFocus(); // goes to next input box
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
