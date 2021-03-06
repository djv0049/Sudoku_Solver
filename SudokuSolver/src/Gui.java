import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuBar;

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
	JLabel inputLabel;
	JButton solveBtn;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenu viewMenu;
	JMenuItem theme;
	JMenuItem save;
	JMenuItem open;
	JMenuItem exit;

	// constructor
	public Gui() {
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setup();
		this.setVisible(true);
	}
	
	// Gui setup
	private void setup() {
		
		// instantiate variables
		s = new Sudoku();
		panel = new JPanel(new BorderLayout());
		inputPanel = new JPanel();
		inputLabel = new JLabel("");// keep label for future use
		solveBtn = new JButton("solve!");
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		viewMenu = new JMenu("View");
		theme = new JMenuItem("Theme");
		save = new JMenuItem("Save");
		open = new JMenuItem("Open");
		exit = new JMenuItem("Exit");
		
		// shortcuts
		fileMenu.setMnemonic('F');
		viewMenu.setMnemonic('V');
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
		theme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK));
		
		// add objects to frame
		this.getContentPane().add(panel);
		viewMenu.add(theme);
		fileMenu.add(save);
		fileMenu.add(open);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		this.setJMenuBar(menuBar);
		panel.add(inputPanel,BorderLayout.NORTH);
		initializeNumFields();
		inputOnGui();
		inputPanel.add(inputLabel);
		inputPanel.add(solveBtn);
		
		// add event listeners.
		ActionListener myListener = new MyActionListener();
		solveBtn.addActionListener(myListener);		
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
				setTextFieldColor(box, x,y, new Color(255,255,255), new Color(200,200,255));
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
		this.inputPanel.add(p, CENTER_ALIGNMENT);
	}
	public void setTextFieldColor(JNumberTextField box, int x, int y, Color primaryColor, Color secondaryColor){
		if(((x < 3 || x > 5) && (y < 3 || y > 5 )) || //corner boxes
				 ((x < 6 && x > 2) && (y < 6 && y > 2))){ //  center box
			box.setBackground(primaryColor);
		}
		else {
			box.setBackground(secondaryColor);
		}
	}
	
	public void setTextColor(JTextField box, Color color) {
		box.setForeground(color);
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
			ArrayList<Integer> allowedExtraKeys = new ArrayList<Integer>(); // list of exception keys to allow mnemonics to work among other benefits.
			allowedExtraKeys.add(KeyEvent.VK_ALT);
			allowedExtraKeys.add(KeyEvent.VK_CONTROL);
			allowedExtraKeys.add(KeyEvent.VK_ESCAPE);
			
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
			else if(allowedExtraKeys.contains(ev.getKeyCode())) {
				solveBtn.requestFocus();
			}
			ev.consume();
			return;	
		}
	}
}
