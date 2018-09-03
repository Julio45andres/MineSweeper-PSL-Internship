/**
 * Blank cell type
 * 
 * These ones when uncovered, a portion of the board with no mines is uncovered
 * Example: 
 * Border cell: 1..9
 * Blank cell: -
 * Uncovered cell: .
. . 2 1 3 3 5 .
. . 2 - - - 4 .
. . 1 - 2 3 3 .
. . 4 2 2 . . .
 * @author julian.munozm
 */
package domain.cells;

public class BlankCell extends Cell {

	private String blankSymbol;
	
	protected BlankCell(String blankContentCellSymbol){
		super.cellType = CellType.BLANK;
		this.blankSymbol = blankContentCellSymbol;
	}
	
	/*
	 * @return the symbol that represents a blank cell in the business logic.
	 */
	@Override
	public String getContent() {
		return blankSymbol;
	}

	@Override
	public BlankCell clone(){
		return new BlankCell(blankSymbol);
	}
}
