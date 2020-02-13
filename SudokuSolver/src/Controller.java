import javax.swing.*;

public class Controller {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	//findPrimes(100);
	//Sudoku win = new Sudoku();
	//win.createAndShowGUI();
	
	Sudoku S = new Sudoku();
	Gui g = new Gui();
	//S.takeInput();
	S.print();
	S.setAllPossibles();
	JFrame alertBox = new JFrame();
	// JOptionPane.showMessageDialog(alertBox, "this is an alertBox\n" + S.print());
	//System.out.println("done");
	
    }
}