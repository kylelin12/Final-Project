import java.util.PriorityQueue;
import java.util.Scanner;

public class Solver {

	private Board _board;
	private Integer[][] _currentBoard;
	private PriorityQueue _boardNeighbors;
	
	// find a solution to the initial board
	public Solver(Board initial) {
		_board = initial;
	}
	
	// is the initial board solvable?
	public boolean isSolvable() {
		_currentBoard = _board.getBoard("Current");
		int size = 0;
		for (Integer[] r: _currentBoard)
			for (Integer c: r)
				size++;
		// If every index is filled
		if (size == (_currentBoard.length * _currentBoard.length))
			return false;
		return true;
	}
	
	// return min number of moves to solve the initial board
	// -1 if no such solution
	public int moves() {
		return -1;
	}
	
	// return string representation of solution (as described above)
	public String toString() {
		return _board.toString();
	}
	
	//  read puzzle instance from stdin and print solution to stdout (in format above)
	public static void main(String[] args) {
		Scanner stdin = new Scanner(new File("stdin.txt"));
		String input = "";
		while (stdin.hasNextLine()) {
			input += stdin.nextLine() + "\n";
		}
		System.out.println("Input: " + input);
	}
}
