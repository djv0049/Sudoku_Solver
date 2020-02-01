import java.util.ArrayList;

public class Section {
	ArrayList<Square> allSquares;
	ArrayList<Square> allMySquares;
	int number;
	public Section(int number) {
		allMySquares = new ArrayList<Square>();
		allSquares = new ArrayList<Square>();
		
		this.number = number;
	}
	public void checkDoubles() {
		
	}
	public boolean containsSquare(int n) {
		for(Square square : allMySquares) {
			if(square.number == n) {
				return true;
			}
		}
		return false;
	}
}
