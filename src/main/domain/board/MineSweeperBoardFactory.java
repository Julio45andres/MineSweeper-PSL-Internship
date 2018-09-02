package domain.board;

/**
 * Creates mine sweeper boards based user input.
 * @author julian.munozm
 */
public interface MineSweeperBoardFactory {
	
	/**
	 * 
	 */
	abstract MineSweeperBoard makeBoard(int height, int width, int numberOfMines);
}
