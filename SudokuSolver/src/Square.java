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
}
