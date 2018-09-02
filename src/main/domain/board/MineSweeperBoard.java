package domain.board;

import domain.cells.CellType;

/**
 * Mine Sweeper Board interface
 * 
 * This interface manages operations on the game board.
 * @author julian.munozm
 */

public interface MineSweeperBoard {
	
	/**
	 * @param row The row number.
	 * @param column The column number.
	 * @return the cell at position [row, column]
	 */
	abstract CellType uncoverCell(int row, int column);
	abstract Boolean isUncovered(int row, int column);
	abstract void putFlag(int row, int column);
	abstract Boolean isMine(int row, int column);
	abstract Boolean hasFlag(int row, int column);
	abstract Integer height();
	abstract Integer width();
}
