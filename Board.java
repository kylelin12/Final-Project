import java.util.PriorityQueue;
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
		if (y == this)
			return true;
		if (y == null || !(y instanceof Board) || ((Board)y)._current.length != this._current.length)
			return false;
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
		for (Integer[] r: _current) {
			for (Object c: r) {
				
				if (index % 3 == 0) {
					string += c + "\n";
				} else {
					string += c + " ";
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
								{7, 8, },
								{0, 5, 2}
							};
		Board test = new Board(three);
		System.out.println(test.toString());
	}
}
