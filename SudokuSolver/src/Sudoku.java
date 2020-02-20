import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.List;

public class Sudoku {
		int sudoku[][] = new int[9][9];
		ArrayList<Box> myBoxes;
		ArrayList<Row> myRows;
		ArrayList<Column> myColumns;
		ArrayList<Cell> allCells;
		
		public Sudoku() {
			this.myBoxes = new ArrayList<Box>();
			this.myRows = new ArrayList<Row>();
			this.myColumns = new ArrayList<Column>();
			this.allCells = new ArrayList<Cell>();
			// make all new rows collumns, boxes and squares, then organise em
			create();
			makeAllCells();
			sortCells();
		}
		
		public void create() { // makes 9 of all em, then adds them to their respective lists
			for(int i = 0; i < 9; i++) {
				Box b = new Box(i);
				Row r = new Row(i);
				Column c = new Column(i);
				this.myBoxes.add(b);
				this.myRows.add(r);
				this.myColumns.add(c);
			}
		}
		
		public void makeAllCells() { // 81 square objects with x and y values for sorting
			for(int x = 0; x < 9; x++) {
				for(int y = 0; y < 9; y++) {
					Cell s;
					s = new Cell(x,y);
					this.allCells.add(s);
				}
			}
		}
		
		public void sortCells() {  // assign squares to cols rows and boxes, and vice-versa
			for(Cell s : this.allCells) {
				for(int i = 0; i < 9; i++) {
					if(s.x == i) { // assign column
						this.myColumns.get(i).allMyCells.add(s);
						s.setCol(this.myColumns.get(i));
					}
					if(s.y == i) { // assign row
						this.myRows.get(i).allMyCells.add(s);
						s.setRow(this.myRows.get(i));
					}
				}
				for( int y = 0; y < 9; y++) {  // math to figure out which box the numbers go into.
					for(int x= 0;x<9; x++) {
						if(s.x == x&&s.y == y) {
							int box = 0;
							if(y < 3) {
								box += 0;
							}
							else if(y < 6) {
								box += 3;
							}
							else if (y < 9){
								box += 6;
							}
							box += x/3;
							this.myBoxes.get(box).allMyCells.add(s); // assign box
							s.myBox = this.myBoxes.get(box);
						}
					}
				}
			}
		}
		
		public Cell getCellByXY(int x, int y) {
			for(Cell c : this.allCells) {
				if(c.x == x && c.y == y) {
					return c;
				}
			}
			return null;
		}
		
		// this function is literally only used now for matts alert box which im not removing
		public void convertTo2dArray() {
			for(Row row : this.myRows) {
				for(int i = 0; i < 9;i++) {
					this.sudoku[row.number][i] = row.allMyCells.get(i).number;
				}
			}
		}
		
		public void setAllPossibles() {
			for(Cell s : this.allCells) {
				if(s.number == 0) {
					s.checkPossibles();
				}
			}
		}
		
		public void solve() {
			int singles;
			int hiddenSingles;	
			do {
				singles = 0 ;
				hiddenSingles= 0;
				for(Cell s : this.allCells) {
					singles += s.changeSingles(); // only available number in square
					this.setAllPossibles(); // update all squares possible nums
					for(int n = 1; n < 10; n++) {
						hiddenSingles += s.isOnlyNumber(n); // only square in arrays with number "n"
						this.setAllPossibles(); // update squares
					}
					if(s.number!=0)
					s.myTextField.setText(String.format("%s", s.number));
				}
			}while(singles + hiddenSingles != 0);
			this.convertTo2dArray(); // method for matts alert box
			
			// new alert box to go here 
		}
		
		public String print() {
			StringBuilder output = new StringBuilder("");
			//String output = "______________________________\n"; // line to separate iterations
			for(Row r : this.myRows) {
				int i = 0;
				for(Cell s : r.allMyCells) {
					output.append(s.number + ", ");
					if(i % 3 == 2) { // spaces boxes horizontal
						output.append("  ");
					}
					i++;
				}
				output.append("\n");
				if(r.number % 3 == 2) { //spaces boxes vertical
					output.append("\n");
				}
			}
			return output.toString();
			
		}
}