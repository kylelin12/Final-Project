import java.util.PriorityQueue;

public class Board {

	private int[][] _previous;
	private int[][] _current;
	private int _movesDone;

	// construct a board from an N-by-N array of tiles
	public Board(int[][] tiles) {
		_previous = null;
		_current = tiles;
		_movesDone = 0;
	}
	
	// set _previous to inputted int[][]
	public void setPrevious(int[][] tiles) {
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
		
	}
	
	// return an Iterable of all neighboring board positions
	public Iterable < Board > neighbors() {
		
	}
	
	// return a string representation of the board
	public String toString() {
		String string = "";
		int index = 1;
		for (int[] r: _current) {
			for (int c: r) {
				if (index % 3 == 0) {
					string += c + "\n";
				} else {
					string += c + " ";
				}
			}
		}
		return string;
	}
	
	//  test client
	public static void main(String[] args) {
		
	}
}
