import java.util.LinkedList;

// http://introcs.cs.princeton.edu/java/assignments/8puzzle.html

public class Board {

	private Integer[][] _previous;
	private Integer[][] _current;
	private int _movesDone;

	// construct a board from an N-by-N array of tiles
	public Board(Integer[][] tiles) {
		_previous = null;
		_current = tiles;
		_movesDone = 0;
	}
	
	// set _previous to inputted int[][]
	public void setPrevious(Integer[][] tiles) {
		_previous = tiles;
	}
	
	public Integer[][] getBoard(String option) {
		if (option.equals("Previous") || option.equals("previous"))
			return _previous;
		else 
			return _current;
	}
	
	public int getMoves() {
		return _movesDone;
	}
	
	public void addMoves() {
		_movesDone++;
	}
	
	// return number of blocks out of place
	public int hamming() {
		int out = _movesDone;
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				if ((_current[i-1][j-1] % ((i * 3) + j)) != 0)
					out++;
			}
		}
		return out;
	}
	
	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int distSum = _movesDone;
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				int colOff = (_current[i-1][j-1] % i) - j;
				int rowOff = (_current[i-1][j-1] % j) - i;
				distSum += colOff + rowOff;
			}
		}
		return distSum;
	}
	
	// does this board equal y
	public boolean equals(Object y) {
		// Basic check
		if (y == this)
			return true;
		// If the object is null, not a Board or doesn't have the same dimensions as the board then false
		if (y == null || !(y instanceof Board) || ((Board)y)._current.length != this._current.length)
			return false;
		// If the elements are mismatched
		for (int i = 0; i < _current.length; i++)
			for (int j = 0; j < _current.length; j++)
				if (((Board)y)._current[i][j] != this._current[i][j])
					return false;
		return true;
	}
	
	// return an Iterable of all neighboring board positions
	public Iterable < Board > neighbors() {
		LinkedList < Board > neighbors = new LinkedList < Board > ();
		return neighbors;
	}
	
	// return a string representation of the board
	public String toString() {
		String string = "";
		int index = 1;
		for (int i = 0; i < _current.length; i++) {
			for (int j = 0; j < _current[i].length; j++) {
				if (_current[i][j] == null) {
					if (index % 3 == 0) // New line if the position is in the 3rd column
						string += " \n";
					else
						string += " ";
				} else {
					if (index % 3 == 0) // New line if the position is in the 3rd column
						string += _current[i][j] + "\n";
					else 
						string += _current[i][j] + " ";
				}
				index++;
			}
		}
		return string;
	}
	
	//  test client
	public static void main(String[] args) {
		Integer[][] three = {
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, null}
							};
		Board test = new Board(three);
		System.out.println(test.toString());
	}
}
