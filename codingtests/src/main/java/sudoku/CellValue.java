package sudoku;

public class CellValue {

	private Cell cell;
	private Integer value;

	public CellValue(Cell cell, Integer value) {
		this.cell = cell;
		this.value = value;
	}

	public Cell getCell() {
		return cell;
	}

	public int getValue() {
		return value;
	}

}
