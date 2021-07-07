package sudoku;

import java.util.Set;

public class CellAndPossiblesValues {

	private Cell cell;
	private Set<Integer> possibleValues;

	public CellAndPossiblesValues(Cell cell, Set<Integer> possiblesValues) {
		this.cell = cell;
		this.possibleValues = possiblesValues;
	}

	public Cell getCell() {
		return cell;
	}

	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}

	@Override
	public String toString() {
		return cell + " " + possibleValues;
	}
}
