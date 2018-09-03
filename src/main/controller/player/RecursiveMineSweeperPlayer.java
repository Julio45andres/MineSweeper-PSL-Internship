package controller.player;

import arrays.ArrayQuery;
import coordinates.CardinalPointsSetMaker;
import domain.board.MineSweeperBoard;
import domain.cells.CellType;

/**
 * Play the mine sweeper movements recursively when selecting a blank cell as follows:
 * 		
 * 	  NW  N  NE
 * 		↖  ↑  ↗
 * 	  W	← - → E
 * 		↙  ↓  ↘
 *    SW  S  SE
 *    
 *    The base case of recursion is when the cell is numbered cell or the cell has been uncovered
 * @author julian.munozm
 */

public class RecursiveMineSweeperPlayer implements MineSweeperPlayer {
	
	private MineSweeperBoard board;
	private final int[][] deltas;
	private ArrayQuery legalMoveQuery;
	

	public RecursiveMineSweeperPlayer(MineSweeperBoard board) {
		this.board = board;
		deltas = new CardinalPointsSetMaker().getMainCardinalPoints();
		legalMoveQuery = new ArrayQuery();
	}

	/**
	 * This method uncovers cells according to their type, numbered cells display the number of adjacent mines,
	 * blank cells display recursively their adjacent cells and mines end the game, in the first two cases, a
	 * continue state is returned.
	 * @return the state
	 */
	@Override
	public GameState uncoverCell(int row, int col) {
		
		if(!legalMoveQuery.inRange(row, col, board.height(), board.width())) {
			return GameState.ONGOING;
		}
		if(board.isUncovered(row, col)) return GameState.ONGOING;
			
		CellType cellType = board.uncoverCell(row, col);
		
		switch (cellType) {
			case BORDER:
				return GameState.ONGOING;
				
			case BLANK:
				final int rowIndex = 1;
				final int columnIndex = 0;
				int dx, dy;
				int x, y;
				GameState finalState = GameState.ONGOING;
				for(int i = 0; i < deltas.length; i++){
					x = row;
					y = col;
					dx = deltas[i][columnIndex];
					dy = deltas[i][rowIndex];
					
					x = row+dy;
					y = col+dx;
					
					finalState = uncoverCell(x, y);
					
					if(finalState == GameState.GAMEOVER) break;
					
				}
				return finalState;
				
			case MINE:
				return GameState.GAMEOVER;
		}
		
		return null;
	}

	@Override
	public void putFlagOnCell(int row, int col) {
		board.putFlag(row, col);
	}

	@Override
	public GameState check() {
		for(int i = 0; i < board.height(); i++){
			for(int j = 0; j < board.height(); j++){
				if(!board.isMine(i,j) && board.hasFlag(i,j))
					return GameState.ONGOING;
			}
		}
		return GameState.WIN;
	}

	@Override
	public String getBoardString() {
		return board.toString();
	}
}

