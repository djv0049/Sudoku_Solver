import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
		
		public void presetInput() { // for testing purposes mainly.
			int[][] input = 
				 		{ { 4, 0, 0,   8, 0, 0,   0, 5, 0 },
	    	              { 2, 1, 0,   0, 0, 4,   3, 0, 6 },
	    	              { 7, 0, 3,   0, 6, 0,   0, 0, 4 },
	    	                
	    	              { 0, 0, 0,   0, 3, 1,   0, 0, 0 },
	    	              { 5, 0, 0,   9, 0, 0,   0, 0, 0 },
	    	              { 0, 0, 9,   0, 7, 5,   0, 0, 1 },
	    	                
	    	              { 0, 0, 0,   3, 8, 7,   0, 0, 0 },
	    	              { 0, 0, 5,   4, 0, 2,   7, 0, 3 },
	    	              { 0, 0, 4,   0, 1, 0,   0, 0, 0 } };
		this.sudoku = input;
		convertFrom2dArray();
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
				for(int x = 0; x < 9; x++) {
					if(s.x == x) {
						this.myColumns.get(x).allMyCells.add(s);
						s.setCol(this.myColumns.get(x));
					}				
				}//// NOTE TO SELF. ^ v these two "for"s may be joinable into one loop 
				for( int y = 0; y < 9; y++) {
					if(s.y == y) {
						this.myRows.get(y).allMyCells.add(s);
						s.setRow(this.myRows.get(y));
					}
				}
				for( int y = 0; y < 9; y++) {  // math to figure out which box the numbers go into. took me way too long to get this so simple
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
							this.myBoxes.get(box).allMyCells.add(s);
							s.myBox = this.myBoxes.get(box);
						}
					}
				}
			}
		}
		
		public void takeInput() {
			int [][] input = new int[9][9];
			Scanner read = new Scanner(System.in); // defined before loop so can be closed outside of loop
			for(int i = 0; i < 9; i++) {
				System.out.println("input the row"); 
				String str = read.nextLine();
				if(str.length() != 9) {
					i--; // go back one
					System.out.print("please enter 9 numbers");
					continue; // skips rest of loop
				}
				String stringArr[] = str.split("");
				int row[] = new int[9]; 
				for(int c = 0; c < 9; c++) {
					row[c] = Integer.parseInt(stringArr[c]); // convert string[] to int[]
				}
				input[i] = row;
			}
			read.close(); // close reader -- good practice

			this.sudoku = input;
			convertFrom2dArray(); 
		}
		
		public void convertFrom2dArray() {  
			for(int x = 0; x < 9; x++) {
				for(Row row : this.myRows) {
					row.allMyCells.get(x).number = this.sudoku[row.number][x];
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
				}
				//System.out.println("Single num Changes Made: " + singles);
				//System.out.println("Hidden Single Changes Made: " + hiddenSingles);
				//this.print();
			}while(singles + hiddenSingles != 0);
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
			System.out.print(output.toString());
			return output.toString();
			
		}
}