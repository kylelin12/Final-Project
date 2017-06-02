import java.util.PriorityQueue;

public class Solver {

	private Board _board;
	private PriorityQueue _boardNeighbors;
	
	// find a solution to the initial board
	public Solver(Board initial) {
		_board = initial;
	}
	
	// is the initial board solvable?
	public boolean isSolvable() {
		int size = 0;
		for (int[] r: _board)
			for (int c: r)
				size++;
		// If every index is filled
		if (size == (_board.length * _board.length))
			return false;
		return true;
	}
	
	// return min number of moves to solve the initial board
	// -1 if no such solution
	public int moves() {
		
	}
	
	// return string representation of solution (as described above)
	public String toString() {
		return _board.toString();
	}
	
	//  read puzzle instance from stdin and print solution to stdout (in format above)
	public static void main(String[] args) {
		
	}
}
