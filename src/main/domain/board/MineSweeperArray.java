package domain.board;

import domain.cells.Cell;
import domain.cells.CellType;

/**
 * @author julian.munozm
 *
 */
public class MineSweeperArray implements MineSweeperBoard {

	
	Cell[][] board;
	/**
	 * 
	 */
	public MineSweeperArray(Cell[][] boardArray) {
		this.board = boardArray;
	}

	@Override
	public CellType uncoverCell(int row, int column) {
		return board[row][column].uncover();
	}

	@Override
	public Boolean isUncovered(int row, int column) {
		return board[row][column].isUncovered();
	}

	@Override
	public void putFlag(int row, int column) {
		board[row][column].putFlag();
	}

	@Override
	public Boolean isMine(int row, int column) {
		return board[row][column].isMine();
	}

	@Override
	public Integer height() {
		return board.length;
	}

	@Override
	public Integer width() {
		return board[0].length;
	}

	@Override
	public Boolean hasFlag(int row, int column) {
		return board[row][column].hasFlag();
	}
	
	@Override
	public String toString() {
		String boardString = "";
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(!board[i][j].isUncovered())
					boardString = boardString.concat("." + " ");
				else
					boardString = boardString.concat(board[i][j].getContent() + " ");
			}
			boardString = boardString.concat("\n");
		}
		return boardString;
	}
}
