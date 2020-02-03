import java.util.*;
public class Square {
	int x;
	int y;
	Box myBox;
	Row myRow;
	Column myCol;
	ArrayList<Integer> possibles;
	int number;
	public Square(int X, int Y) {
		this.x = X;
		this.y = Y;
		this.possibles = new ArrayList<Integer>();
		this.number = 0;
	}
	
	// setters and getters 
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
		// 
		Box box = this.getBox();
		Row row = this.getRow();
		Column col = this.getColumn();
		ArrayList<Integer> possibleNums = new ArrayList<Integer>();
		for(int i = 1; i < 10; i ++) {
			possibleNums.add(i);
		}
		for(int n = 1; n < 10 ; n++) {
			if(box.containsSquare(n) || row.containsSquare(n) || col.containsSquare(n)) {//write a contain method in "section" that returns a boolean for whether the squares in the array have the number in question
				possibleNums.set(n-1, 0);
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
		changeCount += this.arrayLooper(this.myBox.allMySquares, n);
		changeCount += this.arrayLooper(this.myRow.allMySquares, n);
		changeCount += this.arrayLooper(this.myCol.allMySquares, n);
		
		
		return changeCount;
	}
	public int arrayLooper(ArrayList<Square> sList, int n) {
		int listCount = 0; // resets the count each time
		for(Square s : sList) {
			if(s.possibles.contains(n)) {
				listCount += 1; // only adds to count if list contains the parameter number
			}
		}
		// finishes loop with a list count of the amount of times the target number showed in a list
		if(listCount == 1 && this.number == 0 && this.possibles.contains(n)) {
			this.number = n;
			return 1;
		}
		return 0;
	}
	
	
	
		/*//for each array from the number
			int result = 0;
			int listCount = 0;
			ArrayList<Integer> lst = new ArrayList<Integer>();
			ArrayList<ArrayList<Integer>> rowlist = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> colslist = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> boxlist = new ArrayList<ArrayList<Integer>>();
			//find list of each number in a row
			for(int t = 0; t<9;t++) {
				if(this.sudoku[r][t] == 0) {// adds list to check through 
					int[][] all = makeThreeArrays(r, t);
					lst = findNums(all);
					rowlist.add(lst);
				}
				else {// adds list to keep numbers in line 
					lst.add(0); 
					rowlist.add(lst);
				}
			}
			for(ArrayList<Integer> x : rowlist) {
				if(x.contains(n)) {
					listCount += 1;
				}
			}
			if(listCount == 1 && rowlist.get(i).contains(n)) {// ensures the only list that contains the target number, is from the square being worked from
				this.sudoku[r][i] = n;
				result += 1;
			}
			*/
}
