package domain.board;

import java.util.Random;

import arrays.ArrayQuery;
import coordinates.CardinalPointsSetMaker;
import domain.cells.AbstractCellFactory;
import domain.cells.Cell;

/**
 * @author julian.munozm
 *
 */
public class MineSweeperArrayFactory implements MineSweeperBoardFactory {

	private AbstractCellFactory cellFactory;
	private int boardHeight, boardWidth;
	
	/*
	 * TODO: Create an sparse matrix class to make a more clean implementation of the MineSweeperBoardFactory
	 */
	public MineSweeperArrayFactory(AbstractCellFactory cellFactory) {
		this.cellFactory = cellFactory;
	}

	@Override
	public MineSweeperBoard makeBoard(int height, int width, int numberOfMines) {
		boardHeight = height;
		boardWidth = width;
		Cell[][] boardArray = new Cell[height][width];
		Integer[][] mineIndex = new Integer[numberOfMines][2];
		// One mine have at most 8 adjacent numbered cells.
		Integer[][] numberedCellsIndex = new Integer[numberOfMines*8][3];
		
		createMineIndex(mineIndex, height, width, numberOfMines);
		createMines(boardArray, mineIndex);
		
		createNumberedCellsIndex(numberedCellsIndex, mineIndex, numberOfMines);
		createNumberedCells(boardArray, numberedCellsIndex);
		
		fillWithBlanks(boardArray);
		
		return new MineSweeperArray(boardArray);
	}
	
	public void createMineIndex(Integer[][] mineIndex, int height, int width, int numberOfMines){
		Random intGenerator = new Random(System.currentTimeMillis());
		int row;
		int column;
		int rowIndex = 0;
		int columnIndex = 1;

		for(int id = 0; id < numberOfMines; id++){
			row = intGenerator.nextInt(height);
			column = intGenerator.nextInt(width);
			mineIndex[id][rowIndex] = row;
			mineIndex[id][columnIndex] = column;
		}
	}
	
	public void createMines(Cell[][] boardArray, Integer[][] mineIndex){
		Cell nextMine;
		int row;
		int column;
		int rowIndex = 0;
		int columnIndex = 1;
		int length = mineIndex.length;
		
		for(int i = 0; i < length; i++){
			row = mineIndex[i][rowIndex];
			column = mineIndex[i][columnIndex];
			nextMine = cellFactory.makeMineCell();
			boardArray[row][column] = nextMine;
		}
	}

	/**
	 * This method traverses the mine index to create numbered cells on all adjacent 8 directions of the cell.
	 * 				<br/>
	 * NW  N  NE 	<br/>
 * 		  ↖  ↑  ↗	 	<br/>
       W ← * → E 	<br/>
 * 		  ↙  ↓  ↘	 	<br/>
 *     SW  S  SE	<br/>
	 * @return The following array: [ID][ROW, COLUMN, ADJACENT_MINES]
	 * ID: id of the numbered cells at row position.
	 * ROW: column 0 of the array, is the row position of the cell.
	 * COLUMN: column 1 of the array, is the column position of the cell.
	 * ADJACENT_MINES: column 3 is the number of adjacent mines to the cell.
	 */
	public void createNumberedCellsIndex(Integer[][] numberedCellsIndex, Integer[][] mineIndex, int numberOfMines){
		// Variables
		int row;
		int column;
		int mineRow;
		int mineColumn;
		int dx;
		int dy;
		int cellID = 0;
		
		// Constants
		final int rowIndex = 0;
		final int columnIndex = 1;
		final int adjacentMineCountIndex = 2;
		// 8 tuples (dx, dy).
		final int[][] deltas = new CardinalPointsSetMaker().getCardinalPoints();
		ArrayQuery legalPositionQuery = new ArrayQuery();
		
		/* Steps:
		 * 1. For each mine take its x and y coordinates.
		 * 2. Look in the 8 cardinal points of mine i for empty or numbered cells.
		 * 3. Return the numbered cell index
		 */
		for(int i = 0; i < numberOfMines; i++){
			mineRow = mineIndex[i][rowIndex];
			mineColumn = mineIndex[i][columnIndex];
			/* Step 2 detail:
			 * Look in the 8 cardinal points of mine i for empty or numbered cells.
			 * Is there a mine on position (mineRow + dx, mineColumn + dy)?
			 * No
			 * 		Is there a numbered cell on position (mineRow + dx, mineColumn + dy)?
			 * 		No
			 * 			Then put (mineRow + dx, mineColumn + dy) position on the cell on the numbered cell index with an adjacent mine count of 1.
			 * 		Yes 
			 * 			Then sum 1 to the adjacent mine count of (mineRow + dx, mineColumn + dy) position.
			 * Yes
			 * 		Then skip that position.
			 */
			for(int j = 0; j < 8; j++){
				dx = deltas[j][rowIndex];
				dy = deltas[j][columnIndex];
				row = mineRow + dx;
				column = mineColumn + dy;
				if(!legalPositionQuery.inRange(mineRow + dx, mineColumn + dy, boardHeight, boardWidth)) continue;
				// Is there a mine cell on position (mineRow + dx, mineColumn + dy)?
				if(!isItemAtCell(row, column, mineIndex)){
					// No
					// Is there a numbered cell on position (mineRow + dx, mineColumn + dy)?
					if(!legalPositionQuery.inRange(mineRow + dx, mineColumn + dy, boardHeight, boardWidth)) continue;
					if(!isItemAtCell(mineRow + dx, mineColumn + dy, numberedCellsIndex)){
						// No
						// Then put (mineRow + dx, mineColumn + dy) position on the cell on the numbered cell index with an adjacent mine count of 1.
						numberedCellsIndex[cellID][rowIndex] = mineRow + dx;
						numberedCellsIndex[cellID][columnIndex] = mineColumn + dy;
						numberedCellsIndex[cellID][adjacentMineCountIndex] = 1;
						cellID++;

					} else {
						// Yes
						// Then sum 1 to the adjacent mine count of (mineRow + dx, mineColumn + dy) position.
//						Integer[] position = {mineRow + dx, mineColumn + dy};
//						Integer mineCount = numberedCells.get(position);
//						numberedCells.set(position, mineCount++);
						int idCell;
						try {
							idCell = getIndexOfCell(mineRow + dx, mineColumn + dy, numberedCellsIndex);
							numberedCellsIndex[idCell][adjacentMineCountIndex]++;
						} catch (NullPointerException  e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
					}
				} // If there's no mine on (mineRow + dx, mineColumn + dy) then skip that position.
				
			}
		}
	}
	
	public void createNumberedCells(Cell[][] boardArray, Integer[][] numberedCellIndex){
		Cell nextNumberedCell;
		Integer row;
		int column;
		int mineCount;
		int rowIndex = 0;
		int columnIndex = 1;
		int mineCountIndex = 2;
		int length = numberedCellIndex.length;
		
		for(int i = 0; i < length; i++){
			row = numberedCellIndex[i][rowIndex];
			if(row == null) break;
			column = numberedCellIndex[i][columnIndex];
			mineCount = numberedCellIndex[i][mineCountIndex];
			nextNumberedCell = cellFactory.makeNumberCell(mineCount);
			boardArray[row][column] = nextNumberedCell;
		}
	}
	
	public void fillWithBlanks(Cell[][] boardArray){
		int length = boardArray.length;
		int width = boardArray[0].length;
		
		for(int i = 0; i < length; i++){
			for(int j = 0; j < width; j++){
				if(boardArray[i][j] == null)
					boardArray[i][j] = cellFactory.makeBlankCell();
			}
		}
	}
	
	/**
	 * Checks if there's an item at position (row, column) 
	 */
	public Boolean isItemAtCell(int row, int column, Integer[][] index){
		final int x = 0;
		final int y = 1;
		int length = index.length;
		for(int i = 0; i < length; i++){
			if(!(index[i][x] == null) && !(index[i][y] == null))
				if(index[i][x] == row && index[i][y] == column) return true;
		}
		return false;
	}
	
	public Integer getIndexOfCell(int row, int column, Integer[][] index) throws NullPointerException{
		final int x = 0;
		final int y = 1;
		int length = index.length;
		for(int i = 0; i < length; i++){
			if(!(index[i][x] == null) && !(index[i][y] == null))
				if(index[i][x] == row && index[i][y] == column) return i;
		}
		throw new NullPointerException("cell not found");
	}
}
