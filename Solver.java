import java.util.PriorityQueue;

public class Solver {
/*
	private class bComparator implements Comparable<bComparator> {
		private bComparator previous;
		private Board current;
		private int numMoves;
		
		public bComparator(Board board) {
			this.current = board;
			this.numMoves = 0;
		}
		
		public bComparator(Board board, bComparator previous) {
			this.current = board;
			this.previous = previous;
			this.moves = previous.moves + 1;
		}
		
		public int compareTo(bComparator compare) {
			return (this.current.manhattan() - compare.current.manhattan()) + (this.moves - compare.moves);
		}
	}
*/
	/* private bComparator previous; */
	private Board _initial;
	private Board _final;
	// find a solution to the initial board
	public Solver(Board initial) {
		_initial = initial;
		/*
		PriorityQueue<bComparator> compare = new PriorityQueue<bComparator>();
		compare.add(new bComparator(initial));
		
		PriroityQueue<bComparator> compareChild = new PriorityQueue<bComparator>();
		compareChild.add(new bComparator());
	*/
	}
	
	// is the initial board solvable?
	public boolean isSolvable() {
		int boardLength = _initial.getBoard("Current").length;
		int[] boardArray = new int[(int)Math.pow(boardLength, 2)];
		boardArray = fillArray(boardArray, _initial.getBoard("Current"));
		int numInversions = 0;
		for (int i = 0; i < boardLength - 1; i++) {
			for (int j = i; j < boardLength; j++) {
				if (boardArray[j] != 0) {
					if (boardArray[j] < boardArray[i])
						numInversions++;
						System.out.println(numInversions);
				}
			}
		}
		return (numInversions % 2 == 0);
	}
	
	private int[] fillArray(int[] toFill, Integer[][] filling) {
		int index = 0;
		for (int i = 0; i < filling.length; i++) {
			for (int j = 0; j < filling[i].length; j++) {
				toFill[index] = filling[i][j];
				index++;
			}
		}
		
		return toFill;
	}
	
	// return min number of moves to solve the initial board
	// -1 if no such solution
	public int moves() {
		return -1;
	}
	
	// return string representation of solution (as described above)
	public String toString() {
		return _final.toString();
	}
	
	//  read puzzle instance from stdin and print solution to stdout (in format above)
	public static void main(String[] args) {
		Integer[][] boardTest = {
								{1, 2, 3},
								{0, 5, 6},
								{4, 8, 7}
							};
		Board testBoard = new Board(boardTest);
		Solver newSolve = new Solver(testBoard);
		System.out.println(newSolve.isSolvable());
	}
}
