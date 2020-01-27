import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Sudoku {
		int sudoku[][] = new int[9][9];
		int squares[][] = new int[9][9];
		public Sudoku() {
			
				
			int example[][] =
				{ { 0, 0, 4,   0, 0, 0,   0, 6, 7 },
	              { 3, 0, 0,   4, 7, 0,   0, 0, 5 },
	              { 1, 5, 0,   8, 2, 0,   0, 0, 3 },
	                    
	              { 0, 0, 6,   0, 0, 0,   0, 3, 1 },
	              { 8, 0, 2,   1, 0, 5,   6, 0, 4 },
	              { 4, 1, 0,   0, 0, 0,   9, 0, 0 },
	                  
	              { 7, 0, 0,   0, 8, 0,   0, 4, 6 },
	              { 6, 0, 0,   0, 1, 2,   0, 0, 0 },
	              { 9, 3, 0,   0, 0, 0,   7, 1, 0 } };
			//this.sudoku = example;
			addNumbers();
		}
		// in all places: //
		// r == row of sudoku
		// i = index of a row
		
		public void addNumbers() {
			for(int i = 0; i < 9; i++) {
				Scanner read = new Scanner(System.in);
				System.out.println("input the row"); 
				String input = read.nextLine(); // 1,2,3,4,5,6,7,8,9
				//System.out.println(input); // 1,2,3,4,5,6,7,8,9
				String stringArr[] = input.split("");
				int row[] = new int[9]; 
				for(int c = 0; c < 9; c++) {
					row[c] = Integer.parseInt(stringArr[c]);
				}
				//System.out.println(row[4]); // 5
				this.sudoku[i] = row;
				
			}
		}
		public void printNumbers() {
			for(int r = 0; r < 9; r++) {
				if(r == 0 || r == 3 || r == 6) {
					System.out.print("\n__________________________________");
				}
				System.out.println();
				for(int i = 0; i < 9; i++) {
					if(i == 0 || i == 3 || i == 6) {
						System.out.print("| ");
					}
					System.out.print(this.sudoku[r][i] + ", ");
					if(i == 8) {
						System.out.print("|");
					}
				}
			}
			System.out.println("\n__________________________________");
		}
		public void setupBoxes() {
			int[][] rows = this.sudoku;
			int[][] boxes = new int[9][9];
			int boxNum = 0;
	        int boxIndex = 0;
	        for(int r = 0; r< rows.length; r++){
	            for(int i = 0; i < rows[r].length; i++){
	                 int[] result = getboxnum(r, i );
	                 int [] coords = {r,i};
	                 setBox(result, coords);
	            }
	        }
		}
		public static int[] getboxnum(int r, int i) {
           int b = 0;
           if(r < 3){
                   b += 0;
               }
               else if(r < 6){
                   b += 3;
               }
               else if(r < 9){
                   b += 6;
               }
               int boxnum = b + i/3;
               int boxindex = ((r%3)*3)+i%3;
               int[] result = {boxnum,boxindex};
               return result  ;
        }
		public void setBox(int[] i, int[] b) {
			this.squares[b[0]] [b[1]] = this.sudoku[i[0]][ i[1]];
		}
		
		public int[] makeCol(int i) {
			int[] col = new int[9];
			for(int r = 0; r < this.sudoku.length; r++) {
				col[r] = this.sudoku[r][i];
			}
			return col;
		}
		public boolean findAvailableNumbers(int r, int i) {
			int[] row = this.sudoku[r];
			int[] col = makeCol(i);
			int[] box = squares[getboxnum(r, i)[0]];
			int[][] all = {row,col,box};
			ArrayList<Integer> result = findNums(all);
			int [] smallNums = new int[9];
			if(result.size() == 1) {
				this.sudoku[r][i] = result.get(0);
				return true;
			}
			else {
				for(i = 0; i < 9; i++) {
					if(result.indexOf(i) == i ) {
						smallNums[i] = i;
					}
					else {
						smallNums[i] = 0;
					}
				}
				return false;
			}
			// TO DO, set up so that this will be displayed in place of the actual number
			
		}
		public static ArrayList<Integer> findNums(int threeArrays[][]){
	        // make a list one to nine, and for each number that exists in the arrays, remove from the list and return the result 
	        ArrayList<Integer> impossibleNumbers = new ArrayList<Integer>();
	        for(int i[] : threeArrays){
	            for(int j : i){
	                if(!impossibleNumbers.contains(j)){
	                    impossibleNumbers.add(j);
	                }
	            }
	        }
	        // orders numbers and removes the zero, leaving only the numbers in the array
	        Collections.sort(impossibleNumbers);
	        impossibleNumbers.remove(0);
	        
	        // create a list of numbers that aren't in the arrays
	        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
	        ArrayList<Integer> possible = new ArrayList<Integer>();
	        possible.addAll(nums);
	        for(int i = 0; i < impossibleNumbers.size(); i++){
	            int number = impossibleNumbers.get(i);
	            if(possible.contains(number)){
	                possible.remove(possible.indexOf(number));
	            }
	        }
	        return possible;
	        
	    }
		
	}

