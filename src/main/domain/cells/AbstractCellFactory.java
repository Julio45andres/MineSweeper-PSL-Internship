package domain.cells;

/**
 * Abstract cell factory
 * 
 * Base class for creating sets of cell types.
 * @author julian.munozm
 */

public abstract class AbstractCellFactory {
	
	abstract public Cell makeBlankCell();
	
	abstract public Cell makeMineCell();
	
	public abstract Cell makeNumberCell(Integer adjacentMineCount);
}
