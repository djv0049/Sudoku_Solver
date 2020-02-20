import java.util.*;

import javax.swing.JTextField;
public class Cell {
	int x;
	int y;
	Box myBox;
	Row myRow;
	Column myCol;
	JTextField myTextField;
	ArrayList<Integer> possibles;
	int number;
	public Cell(int X, int Y) {
		this.x = X;
		this.y = Y;
		this.possibles = new ArrayList<Integer>();
		this.number = 0;
	}
	
	// setters and getters  -- good practice. something to improve on.
	public void setBox(Box myBox){
		this.myBox = myBox;
	}
	public void setRow(Row myRow){
		this.myRow = myRow;
	}
	public void setCol(Column myCol){
		this.myCol = myCol;
	}
	
	public Box getBox() {
		return this.myBox;
	}
	public Row getRow() {
		return this.myRow;
	}
	public Column getColumn() {
		return this.myCol;
	}
	
	
	public void checkPossibles() {
		this.possibles.clear();
		// make a list of all nums 1-9
		// get box, row or col, 
		// for each one, delete whatever numbers match. 
		// set s.possibles to be the result
		Box box = this.getBox();
		Row row = this.getRow();
		Column col = this.getColumn();
		ArrayList<Integer> possibleNums = new ArrayList<Integer>();
		for(int i = 1; i < 10; i ++) {
			possibleNums.add(i);
		}
		for(int n = 1; n < 10 ; n++) {
			if(box.containsNumber(n) || row.containsNumber(n) || col.containsNumber(n)) {//write a contain method in "section" that returns a boolean for whether the squares in the array have the number in question
				possibleNums.set(n-1, 0); // n-1 because it's setting the index which starts from 0.
			}
		}
		for(int p : possibleNums) {
			if (p != 0) {
				this.possibles.add(p);
			}
		}		
	}
	
	public int changeSingles() {
		if(this.possibles.size() == 1 && this.number == 0) {
			this.number = this.possibles.get(0);
			return 1;
		}
		return 0;
	}
	
	public int isOnlyNumber(int n) { // takes number to check if it is in the square
		int changeCount = 0; // resets the count each time
		changeCount += this.arrayLooper(this.myBox.allMyCells, n);
		changeCount += this.arrayLooper(this.myRow.allMyCells, n);
		changeCount += this.arrayLooper(this.myCol.allMyCells, n);
		return changeCount;
	}
	
	public int arrayLooper(ArrayList<Cell> sList, int n) {
		int listCount = 0; // resets the count each time
		for(Cell s : sList) {
			if(s.possibles.contains(n)) {
				listCount += 1; // only adds to count if list contains the parameter number
			}
		}
		// finishes loop with a list count of the amount of times the target number showed in a list
		if(listCount == 1 && this.number == 0 && this.possibles.contains(n)) {
			this.number = n; // makes change
			return 1; // return 1 for a change made 
		}
		return 0; // returns 0 if no changes made 
	}
}