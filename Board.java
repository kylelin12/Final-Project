import java.util.LinkedList;

public class Board {

	private Integer[][] _board;

	// construct a board from an N-by-N array of tiles
	public Board(Integer[][] tiles) {
		_board = tiles;
	}

	// Returns the _board
	public Integer[][] getBoard() {
		return _board;
	}
	// return number of blocks out of place
	public int hamming() {
		int solution = 0;
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				if (notRight(r, c)) solution++;
			}
		}
		return solution;
	}

	// Is the value at the specified index in the right place?
	private boolean notRight(int r, int c) {
		return (_board[r][c] != null) && (_board[r][c] != rightPlace(r, c));
	}

	// Returns the integer that should be in that row & column
	private int rightPlace(int r, int c) {
		return (r * _board.length) + c + 1;
	}
	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int solution = 0;
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				solution += distanceAway(r, c);
			}
		}
		return solution;
	}

	// Returns the absolute distance away from where it should be
	private int distanceAway(int r, int c) {
		if (_board[r][c] == null) return 0;
		else {
			int index = _board[r][c];
			return (Math.abs(r - ((index - 1) / _board.length)) + Math.abs(c - ((index - 1) % _board.length)));
		}
	}

	// Checks every position to see if it's in the right place.
	public boolean isSolution() {
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				if (_board[r][c] != null && _board[r][c] != rightPlace(r, c)) return false;
			}
		}
		return true;
	}

	// Generates a possible child
	public Board child() {
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				if (_board[r][c] != null && _board[r][c + 1] != null) return new Board(swap(r, c, r, c + 1)); // Returns a new board with one swap
			}
		}
		return null;
	}

	// Returns a new IntegerArray after 1 swap
	private Integer[][] swap(int r, int c, int newR, int newC) {
		Integer[][] result = _board;
		int temp = result[r][c];
		result[r][c] = result[r][c + 1];
		result[r][c + 1] = temp;
		return result;
	}

	// Are these two identical
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y != null || !(y instanceof Board) || ((Board) y)._board.length != _board.length) return false;
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				if (((Board) y)._board[r][c] != _board[r][c]) return false;
			}
		}
		return true;
	}

	// return an Iterable of all neighboring board positions	                          
	public Iterable < Board > neighbors() {
		LinkedList < Board > neighbors = new LinkedList < Board > ();
		Integer[] spaces = locationOfSpace();
		if (spaces[0] > 0) neighbors.add(new Board(swap(spaces[0], spaces[1], spaces[0] - 1, spaces[1])));
		if (spaces[0] < _board.length - 1) neighbors.add(new Board(swap(spaces[0], spaces[1], spaces[0] + 1, spaces[1])));
		if (spaces[1] > 0) neighbors.add(new Board(swap(spaces[0], spaces[1], spaces[0], spaces[1] - 1)));
		if (spaces[1] < _board.length - 1) neighbors.add(new Board(swap(spaces[0], spaces[1], spaces[0], spaces[1] + 1)));
		return neighbors;
	}

	// Returns the first location of an empty space in the form of an Integer array
	private Integer[] locationOfSpace() {
		for (int r = 0; r < _board.length; r++) {
			for (int c = 0; c < _board.length; c++) {
				if (_board[r][c] == null) {
					Integer[] location = new Integer[2];
					location[0] = r;
					location[1] = c;
					return location;
				}
			}
		}
		return null;
	}

	// return a string representation of the board
	public String toString() {
		String string = "";
		int index = 1;
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board[i].length; j++) {
				if (_board[i][j] == null) {
					if (index % 3 == 0) // New line if the position is in the 3rd column
					string += " \n";
					else string += " ";
				} else {
					if (index % 3 == 0) // New line if the position is in the 3rd column
					string += _board[i][j] + "\n";
					else string += _board[i][j] + " ";
				}
				index++;
			}
		}
		return string;
	}
}
