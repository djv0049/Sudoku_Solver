import java.util.ArrayList;
// with refactoring, this could easily replace all child classes. 
// but i left that how it is, for readability. 
// and so i don't have to rewrite anything
public class Section {
	ArrayList<Cell> allCells;
	ArrayList<Cell> allMyCells;
	int number;
	public Section(int number) {
		allMyCells = new ArrayList<Cell>();
		allCells = new ArrayList<Cell>();
		this.number = number;
	}
	public void checkDoubles() {
		// code for ensuring no two of the same number
	}
	public boolean containsNumber(int n) {
		for(Cell square : allMyCells) {
			if(square.number == n) {
				return true;
			}
		}
		return false;
	}
}
