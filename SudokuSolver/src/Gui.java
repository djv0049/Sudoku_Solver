import java.awt.Dimension;
import javax.swing.*;
public class Gui extends JFrame{
	public static Gui gui;
	
	public Gui() {
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setup();
		this.setVisible(true);
	}
	
	public void setup() {
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		JLabel label = new JLabel("type out a line");
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(100,50));
		JButton add = new JButton("add Line");
		panel.add(label);
		panel.add(input);
		panel.add(add);
		
	}
	
	// to do: 
	// make a function to take the input from text box 
	// ensure the function is only triggered when add button is pressed.
	// functions should operate in a similar fashion to the sudoku.takeInput function
	// have the window print out each line in a sudoku format when add is pressed.
	// add a delete line button
	// add a solve button when the ninth line has been entered.
	// have the solution display in an alertBox fashion
	
	//// messagae ////
	
}
