package domain.cells;

/**
 * Represents a single cell of the game board.
 * @author julian.munozm
 */

public abstract class Cell {
	
	protected Boolean isUncovered = false;
	protected Boolean hasFlag = false;
	protected CellType cellType;
	
	/*
	 * @return type of cell.
	 */
	public CellType uncover(){
		isUncovered = true;
		hasFlag = false;
		return cellType;
	}
	
	/*
	 * This method tells if the cell had been uncovered
	 */
	public Boolean isUncovered(){
		return isUncovered;
	}
	
	/*
	 * This method tells if the cell is marked with a flag.
	 */
	public Boolean hasFlag(){
		return hasFlag;
	}
	
	public void putFlag(){
		hasFlag = true;
	}
	
	public Boolean isMine(){
		return cellType.equals(CellType.MINE);
	}
	
	/* 
	 * @return the content of the cell in string format.
	 */
	public abstract String getContent();
}
