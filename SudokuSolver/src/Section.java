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
	
}
