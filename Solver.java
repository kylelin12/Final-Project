import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.io. * ;

public class Solver {

	// Private Move object that allows you to compare Boards easily
	private class Move implements Comparable < Move > {
		private Move _previous;
		private Board _current;
		private int _numMoves;

		public Move(Board board) {
			_current = board;
		}

		public Move(Board board, Move previous) {
			_current = board;
			_previous = previous;
			_numMoves = _previous._numMoves + 1;
		}

		public int compareTo(Move compare) {
			return (_current.manhattan() - compare._current.manhattan()) + (_numMoves - compare._numMoves);
		}
	}

	private Move _previousMove;

	// find a solution to the initial board
	public Solver(Board initial) {
		PriorityQueue < Move > compare = new PriorityQueue < Move > ();
		compare.add(new Move(initial));

		PriorityQueue < Move > compareChild = new PriorityQueue < Move > ();
		compareChild.add(new Move(initial.child()));

		while (true) {
			_previousMove = allChild(compare);
			if (_previousMove != null || allChild(compareChild) != null) return;
		}
	}

	// Returns the board after the best possible move has been run
	private Move allChild(PriorityQueue < Move > compare) {
		if (compare.isEmpty()) return null;
		Move bestChoice = compare.poll();
		if (bestChoice._current.isSolution()) return bestChoice;
		for (Board neighbor: bestChoice._current.neighbors()) {
			if (bestChoice._previous == null || !neighbor.equals(bestChoice._previous._current)) {
				compare.add(new Move(neighbor, bestChoice));
			}
		}
		return null;
	}

	// is the initial board solvable? Uses inversion algorithm
	public boolean isSolvable(Board initial) {
		int boardLength = initial.getBoard().length;
		Integer[] boardArray = new Integer[(int) Math.pow(boardLength, 2)];

		// Converts a 2d to 1d array
		ArrayList < Integer > list = new ArrayList < Integer > ();
		for (int r = 0; r < initial.getBoard().length; r++) {
			for (int c = 0; c < initial.getBoard().length; c++) {
				list.add(initial.getBoard()[r][c]);
			}
		}
		for (int i = 0; i < boardArray.length; i++) {
			boardArray[i] = list.get(i);
		}

		int numInversions = 0;
		for (int i = 0; i < boardLength - 1; i++) {
			if (boardArray[i] != 0) {
				for (int j = i + 1; j < boardArray.length; j++) {
					if (boardArray[j] != 0) {
						if (boardArray[j] < boardArray[i]) {
							numInversions++;
						}
					}
				}
			}
		}
		return (numInversions % 2 == 0);
	}

	// return min number of moves to solve the initial board
	// -1 if no such solution
	public int moves(Board initial) {
		if (isSolvable(initial)) return _previousMove._numMoves;
		else return - 1;
	}

	// return string representation of solution (as described above)
	public String toString() {
		return _previousMove.toString();
	}

	//  read puzzle instance from stdin and print solution to stdout (in format above)
	public static void main(String[] args) throws FileNotFoundException {
		// # of rows and columns
		Scanner input = new Scanner(new File(args[0]));
		int rows = 0;
		int columns = 0;
		while (input.hasNextLine()) {
			rows++;
			Scanner colReader = new Scanner(input.nextLine());
			while (colReader.hasNextInt()) {
				columns++;
			}
		}
		Integer[][] board = new Integer[rows][columns];

		input.close();
		// actual insert of data
		input = new Scanner(new File(args[0]));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (input.hasNextInt()) {
					board[i][j] = input.nextInt();
				}
			}
		}

		// Solves the board
		Board boardSolution = new Board(board);
		System.out.println(boardSolution.toString());
	}
}
