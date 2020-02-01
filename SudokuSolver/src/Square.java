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
		// make a list of all nums 1-9
		// get box, row or col, 
		// for each one, delete whatever numbers match. 
		// set s.possibles to be the result
		// 
		Box box = this.getBox();
		Row row = this.getRow();
		Column col = this.getColumn();
		ArrayList<Integer> possibleNums = new ArrayList();
		for(int i = 1; i < 10; i ++) {
			possibleNums.add(i);
		}
		System.out.print(possibleNums.get(5));
		for(int n : possibleNums) {
			if(box.containsSquare(n)) {//write a contain method in "section" that returns a boolean for whether the squares in the array have the number in question
				possibleNums.remove(n);
			}
		}
		this.possibles = possibleNums;
	}
		
}
