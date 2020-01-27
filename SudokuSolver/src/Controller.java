import java.lang.reflect.Array;

import java.util.*;
public class Controller {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	//findPrimes(100);
	//Sudoku win = new Sudoku();
	//win.createAndShowGUI();
	
	Sudoku S = new Sudoku();
	
	S.setupBoxes();
	S.printNumbers();
	boolean changeMade = false;
	do {
		changeMade = false;
		for(int r = 0; r < 9; r++) {
			for(int i = 0; i < 9 ; i++) {
				if(S.sudoku[r][i] == 0) {
					changeMade = S.findAvailableNumbers(r, i);
				}
				
				
			}
		}
		System.out.print(1);
		S.printNumbers();
	}while(changeMade);
	System.out.print("");
	
	
}

}
