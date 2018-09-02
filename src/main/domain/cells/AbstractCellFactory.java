package domain.cells;

/**
 * Abstract cell factory
 * 
 * Base class for creating sets of cell types.
 * @author julian.munozm
 */

public abstract class AbstractCellFactory {
	
	protected BlankCell blankCellPrototype;
	protected MineCell mineCellPrototype;
	
	public Cell makeBlankCell(){
		return blankCellPrototype;
	}
	
	public Cell makeMineCell(){
		return mineCellPrototype;
	}
	
	public abstract Cell makeNumberCell(Integer adjacentMineCount);
}
