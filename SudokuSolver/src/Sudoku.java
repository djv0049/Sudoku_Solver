import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Sudoku {
		int sudoku[][] = new int[9][9];
		int squares[][] = new int[9][9];
		public Sudoku() {
			/*
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
			this.sudoku = example;
			*/
			int example[][] =

				{   {0, 0, 2,  5, 0, 0,  0, 0, 4},
					{8, 0, 0,  6, 0, 9,  0, 5, 0},
					{5, 0, 0,  0, 2, 0,  0, 1, 0},

					{0, 0, 5,  9, 0, 0,  3, 0, 0},
					{0, 0, 8,  3, 7, 0,  5, 0, 0},
					{0, 6, 0,  0, 8, 0,  4, 0, 0},

					{1, 5, 0,  0, 0, 0,  0, 3, 2},
					{0, 0, 0,  0, 0, 8,  0, 4, 5},
					{0, 0, 4,  0, 0, 3,  0, 0, 0} };
			this.sudoku = example;
			
			//addNumbers();
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
					System.out.println("");
					//System.out.print("\n__________________________________");
				}
				System.out.println();
				for(int i = 0; i < 9; i++) {
					if(i == 0) {
						System.out.print("{");
					}
					if( i == 3 || i == 6) {
						System.out.print("  ");
					}
					System.out.print(this.sudoku[r][i] + "");
					if(i != 8) {
						System.out.print(", ");
					}
					if(i == 8) {
						System.out.print("},");
					}
				}
			}
			//System.out.println("\n__________________________________");
		}
		public void setupBoxes() {
			int[][] rows = this.sudoku;
	        for(int r = 0; r< rows.length; r++){
	            for(int i = 0; i < rows[r].length; i++){
	                 int[] result = getboxnum(r, i );
	                 int [] coords = {r,i};
	                 setBox(result, coords);
	            }
	        }
		}
		public int[] getboxnum(int r, int i) {
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
		public int[] getRIfromBox(int bn, int bi) {
			int r = 0;
			int i = 0;
			if (bn < 3) {
				r += 0;
			}
			else if (bn < 6) {
				r += 3;
			}
			else if(bn < 9) {
				r += 6;
			}
			if(bi < 3) {
				i += 0;
			}
			else if(bi < 6) {
				r += 1;
			}
			else if(bi < 9) {
				r +=2;
			}
			i += bi%3;
			i += bn%3*3;
			int[] result = {r,i};
			return  result;
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
		public int hasAvailableNums(int r, int i) {
			int[][] all = makeThreeArrays(r,i);
			ArrayList<Integer> result = findNums(all);
			int [] smallNums = new int[9];
			if(result.size() == 1) {
				this.sudoku[r][i] = result.get(0);
				return 1;
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
				return 0;
			}
			// TO DO, set up so that this will be displayed in place of the actual number	
		}
		public int isOnlyNumInLine(int r, int i, int n) {
			//for each array from the number
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
			//////// *****************************  repeat of above to figure out repetition and how and where to utilize repeated code. *************************** //////////////////////////
			listCount = 0;
			for(int t = 0; t < 9; t++) {
				if(this.sudoku[t][i] == 0) {
					int[][] all = makeThreeArrays(r, t);
					lst = findNums(all);
					colslist.add(lst);
				}
				else {// adds list to keep numbers in line 
					lst.add(0); 
					colslist.add(lst);
				}
			}
			for(ArrayList<Integer> x : colslist) {
				if(x.contains(n)) {
					listCount += 1;
				}
			}
			if(listCount == 1 && colslist.get(r).contains(n)) {// ensures the only list that contains the target number, is from the square being worked from
				this.sudoku[r][i] = n;
				result += 1;
			}
	//////// *****************************  repeat of above to figure out repetition and how and where to utilize repeated code. *************************** //////////////////////////
			listCount = 0;
			for(int t = 0; t < 9; t++) {
					int b = getboxnum(r, i)[0];
					setupBoxes();
					if(this.squares[b][t] == 0) {
						int [] ri= getRIfromBox(b,t);
						int[][] all = makeThreeArrays(ri[0],ri[1]);
						lst = findNums(all);
						boxlist.add(lst);
					}
					else {// adds list to keep numbers in line 
						lst.clear();
						lst.add(0); 
						boxlist.add(lst);
					}
			}
			for(ArrayList<Integer> x : boxlist) {
				if(x.contains(n)) {
					listCount += 1;
				}
			}
			int bn = getboxnum(r, i)[1];
			
			if(listCount == 1 && boxlist.get(bn).contains(n)) {// ensures the only list that contains the target number, is from the square being worked from
				this.sudoku[r][i] = n;
				result += 1;
			}
			
		
				//make a list of lists of all the numbers that can be in each square
				// if more than one list contains the "n", continue. 
				// else set the 
			
			
			return result;
		}
		
		public void isOnlyNum() {
			
		}
		
		public int[][] makeThreeArrays(int r, int i) {
			int[] row = this.sudoku[r];
			int[] col = makeCol(i);
			int[] box = squares[getboxnum(r, i)[0]];
			int[][] all = {row,col,box};
			return all;
			
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

