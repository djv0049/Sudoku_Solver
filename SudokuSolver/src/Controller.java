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
	int changeMade = 0;
	int runs = 0;
	int firstruns =0;
	int secindruns = 0;
	do {
		changeMade = 0;
		for(int r = 0; r < 9; r++) {
			for(int i = 0; i < 9 ; i++) {
				if(S.sudoku[r][i] == 0) {
					changeMade += S.hasAvailableNums(r, i);
					firstruns += 1;
				}
			}
		}if(changeMade == 0) {
			// do a thing
			/*
			 * for each square in the sudoku(r,i)
			 * 	  for each number 0-9 
			 *      for each array from the number (getboxnumbers(r,i)[0]) or this.sudoku[r] or makecol(i))
			 *      
			 *        for each square in array {
			 *           make a list of all the numbers that can be in each square 
			 *            
			 */
			for(int n = 1; n < 10; n++) {
				for(int r = 0; r < 9; r++) {
					for(int i = 0; i < 9; i++) {
						if(S.sudoku[r][i] == 0) {
							changeMade += S.isOnlyNumInLine(r,i,n);
							secindruns += 1;
							S.setupBoxes();
						}
					}
				}
			}
		}
		if (changeMade == 0 ) {
			System.out.println("no change made");
			
		}
		runs ++;
		System.out.println(runs);
		System.out.println(firstruns);
		System.out.println(secindruns);
		S.printNumbers();
	}while(changeMade > 0);
	System.out.print("");
	
	
}

}
