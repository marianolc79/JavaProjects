package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class Board {

	private int[][] state;

	public Board(int[][] state) {
		this.state = state;
	}

	public Board(Board board) {
		this.state = Arrays.stream(board.state).map(a -> Arrays.copyOf(a, a.length)).toArray(int[][]::new);
	}

	public int value(Cell cell) {
		return value(cell.getRow(), cell.getCol());
	}

	private int value(int row, int col) {
		return state[row][col];
	}

	public Set<Integer> rowValues(Cell cell) {
		Set<Integer> values = new HashSet<>();
		for (int col = 0; col < 9; col++) {
			if (value(cell.getRow(), col) != 0) {
				values.add(value(cell.getRow(), col));
			}
		}
		return values;
	}

	public Set<Integer> colValues(Cell cell) {
		Set<Integer> values = new HashSet<>();
		for (int row = 0; row < 9; row++) {
			if (value(row, cell.getCol()) != 0) {
				values.add(value(row, cell.getCol()));
			}
		}
		return values;
	}

	private int endCoord(int i) {
		int end = 0;
		if (i >= 0 && i <= 2) {
			end = 2;
		} else if (i > 2 && i <= 5) {
			end = 5;
		} else {
			end = 8;
		}
		return end;
	}

	private int startCoord(int i) {
		int start = 0;
		if (i >= 0 && i <= 2) {
			start = 0;
		} else if (i > 2 && i <= 5) {
			start = 3;
		} else {
			start = 6;
		}
		return start;
	}

	public boolean canPutValue(Cell cell, int value) {
		return possiblesValues(cell).contains(Integer.valueOf(value));
	}

	public void putValue(Cell cell, int value) {
		state[cell.getRow()][cell.getCol()] = value;
	}

	private List<Cell> subgrid(Cell cell) {
		List<Cell> subgridCells = new ArrayList<>();
		for (int i = startCoord(cell.getRow()); i <= endCoord(cell.getRow()); i++) {
			for (int j = startCoord(cell.getCol()); j <= endCoord(cell.getCol()); j++) {
				subgridCells.add(new Cell(i, j));
			}
		}
		return subgridCells;
	}

	public Set<Integer> subgridValues(Cell cell) {
		Set<Integer> values = new HashSet<>();
		for (Cell subgridCell : subgrid(cell)) {
			if (value(subgridCell) != 0) {
				values.add(value(subgridCell));
			}
		}
		return values;
	}

	public boolean isSolved() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (state[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public List<CellValue> possiblesValues() {
		List<CellValue> list = new ArrayList<>();

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (state[row][col] != 0) {
					continue;
				}
				Cell cell = new Cell(row, col);
				Set<Integer> possibleValues = possiblesValues(cell);

				if (possibleValues.isEmpty()) {
					continue;
				}
				possibleValues.stream().forEach(x -> list.add(new CellValue(cell, x)));
			}
		}
		return list;
	}

	public Set<Integer> possiblesValues(Cell cell) {
		return Sets.difference(
				Sets.difference(Sets.difference(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9), rowValues(cell)), colValues(cell)),
				subgridValues(cell));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------------------\n");
		for (int row = 0; row < 9; row++) {
			sb.append("|");
			for (int col = 0; col < 9; col++) {
				sb.append(" " + state[row][col] + " ");

				if ((col + 1) % 3 == 0) {
					sb.append("|");
				}
			}
			if ((row + 1) % 3 == 0) {
				sb.append("\n-------------------------------");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public List<Cell> getEmptyCells() {
		List<Cell> cells = new ArrayList<>();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (state[row][col] == 0) {
					cells.add(new Cell(row, col));
				}
			}
		}
		return cells;
	}

}
