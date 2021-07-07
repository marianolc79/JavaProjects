package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SudokuSolver {

	private static final int[][] STATE = //
			{ //
					{ 5, 0, 0, 0, 0, 0, 0, 0, 0 }, //
					{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, //
					{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, //

					{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, //
					{ 0, 0, 0, 8, 0, 3, 0, 0, 1 }, //
					{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, //

					{ 0, 6, 0, 0, 0, 0, 0, 8, 0 }, //
					{ 0, 0, 0, 0, 0, 9, 0, 0, 5 }, //
					{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } //
			};

//	private static final int[][] STATE = //
//		{ //
//				{ 5, 3, 0, 0, 7, 0, 0, 0, 0 }, //
//				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, //
//				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, //
//
//				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, //
//				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 }, //
//				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, //
//
//				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, //
//				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 }, //
//				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } //
//		};
	public static void main(String[] args) {
		Board board = new Board(STATE);
		System.out.println(board.toString());
		Board boardSolved = solve(board);
		System.out.println(boardSolved.toString());

	}

	private static Board solve(Board board) {

		propagateConstraints(board);

		if (board.isSolved()) {
			return board;
		}

		return solveBySearch(board, board.possiblesValues());
	}

	private static boolean propagateConstraints(Board board) {
		boolean hasChanged = true;
		while (hasChanged) {
			hasChanged = false;
			List<Cell> cells = board.getEmptyCells();

			for (Cell cell : cells) {

				Set<Integer> possibleValues = board.possiblesValues(cell);

				if (possibleValues.isEmpty()) {
					return false;
				}

				if (possibleValues.size() == 1) {
					board.putValue(cell, possibleValues.iterator().next());
					hasChanged = true;
				}
			}
		}
		return true;
	}

	private static Board solveBySearch(Board board, List<CellValue> possibleValues) {

		for (CellValue cellValue : possibleValues) {
			if (!board.canPutValue(cellValue.getCell(), cellValue.getValue())) {
				continue;
			}
			Board board2 = new Board(board);
			board2.putValue(cellValue.getCell(), cellValue.getValue());
			if (!propagateConstraints(board2)) {
				continue;
			}
			System.out.println(cellValue.getCell() + " => " + cellValue.getValue());

//			try {
//				Thread.sleep(1000);
//			} catch (Exception e) {
//			}
			System.out.println(board2);
			if (board2.isSolved()) {
				return board2;
			} else {
				List<CellValue> possibleValues2 = new ArrayList<>(possibleValues);
				possibleValues2.remove(cellValue);
				Board board3 = solveBySearch(board2, possibleValues2);
				if (board3.isSolved()) {
					return board3;
				}
			}

		}
		return board;
	}
}
