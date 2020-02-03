import java.lang.reflect.Array;

import java.util.*;
public class Controller {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	//findPrimes(100);
	//Sudoku win = new Sudoku();
	//win.createAndShowGUI();
	
	Sudoku S = new Sudoku();
	//S.takeInput();
	S.presetInput();
	S.print();
	S.setAllPossibles();
	S.solve();
	System.out.println("done");
	S.print();
	
    }
}