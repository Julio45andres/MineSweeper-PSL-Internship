/**
 * Border cell type
 * These cells display a number when uncovered
 * @author julian.munozm
 */
package domain.cells;

public class NumberCell extends Cell {
	
	private Integer adjacentMines;
	
	protected NumberCell(Integer adjacentMines){
		super.cellType = CellType.BORDER;
		this.adjacentMines = adjacentMines;
	}

	/*
	 * @return the symbol that represents a border cell in the business logic.
	 */
	@Override
	public String getContent() {
		return adjacentMines.toString();
	}
}
