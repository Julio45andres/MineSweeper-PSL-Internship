package controller.player;

/**
 * Mine Sweeper Board interface
 * 
 * This interface plays the mine sweeper movements on the board.
 * @author julian.munozm
 */
public interface MineSweeperPlayer {
	abstract GameState uncoverCell(int row, int col);
	abstract void putFlagOnCell(int row, int col);
	abstract GameState check();
	abstract String getBoardString();
}
