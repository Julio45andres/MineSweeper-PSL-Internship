/**
 * Mine cell type
 * These ones end the game when uncovered
 * @author julian.munozm
 */
package domain.cells;

public class MineCell extends Cell {
	
	String mineSymbol;
	
	protected MineCell(String mineCellContentSymbol){
		super.cellType = CellType.MINE;
		this.mineSymbol = mineCellContentSymbol;
	}

	/*
	 * @return the symbol that represents a mine in the business logic.
	 */
	@Override
	public String getContent() {
		return mineSymbol;
	}
}
