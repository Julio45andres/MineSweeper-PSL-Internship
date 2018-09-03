package domain.cells;

/**
 * This string based cell factory create cells that are able to become strings.
 * 
 * One of the possibles implementations of cell factory.
 * @author julian.munozm
 */

public class StringBasedCellFactory extends AbstractCellFactory {
	String blankCellContentSymbol;
	String mineCellContentSymbol;
	
	public StringBasedCellFactory(String blankCellContentSymbol, String mineCellContentSymbol) {
		this.blankCellContentSymbol = blankCellContentSymbol;
		this.mineCellContentSymbol = mineCellContentSymbol;
	}

	/**
	 * Number cells are not prototyped as the others, as these cells data varies, therefore one can prototype 8
	 * number cells with values from 1 to 8.
	 */
	@Override
	public Cell makeNumberCell(Integer adjacentMineCount) {
		return new NumberCell(adjacentMineCount);
	}

	@Override
	public Cell makeBlankCell() {
		return new BlankCell(blankCellContentSymbol);
	}

	@Override
	public Cell makeMineCell() {
		return new MineCell(mineCellContentSymbol);
	}
}
