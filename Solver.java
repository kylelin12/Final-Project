import java.util.PriorityQueue;

public class Solver {

	private class Move implements Comparable<Move> {
		private Move previous;
		private Board current;
		private int numMoves;
		
		public Move(Board board) {
			this.current = board;
			this.numMoves = 0;
		}
		
		public Move(Board board, Move previous) {
			this.current = board;
			this.previous = previous;
			this.numMoves = previous.numMoves + 1;
		}
		
		public int compareTo(Move compare) {
			return (this.current.manhattan() - compare.current.manhattan()) + (this.numMoves - compare.numMoves);
		}
	}

	private Move lastMove;
	private Board _initial;
	private Board _final;
	
	// find a solution to the initial board
	public Solver(Board initial) {
		_initial = initial;
		PriorityQueue<Move> compare = new PriorityQueue<Move>();
		compare.add(new Move(initial));
		
		PriorityQueue<Move> compareChild = new PriorityQueue<Move>();
		compareChild.add(new Move(initial.dupeChild()));
		
		while (true) {
			lastMove = allChild(compare);
			if (lastMove != null || allChild(compareChild) != null) 
				return; 
		}
	}
	
	// Returns the board after the best possible move has been run
	private Move allChild(PriorityQueue<Move> compare) {
		if (compare.isEmpty())
			return null;
		Move bestChoice = compare.poll();
		if (bestChoice.current.isSolution())
			return bestChoice;
		for (Board neighbor: bestChoice.current.neighbors()) {
			if (bestChoice.previous == null || !neighbor.equals(bestChoice.previous.current)) {
				compare.add(new Move(neighbor, bestChoice));
			}
		}
		return null;
	}
	
	// is the initial board solvable?
	public boolean isSolvable() {
		int boardLength = _initial.getBoard("Current").length;
		int[] boardArray = new int[(int)Math.pow(boardLength, 2)];
		boardArray = fillArray(boardArray, _initial.getBoard("Current"));
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
