package domain.cells;

/**
 * This string based cell factory create cells that are able to become strings.
 * 
 * One of the possibles implementations of cell factory.
 * @author julian.munozm
 */

public class StringBasedCellFactory extends AbstractCellFactory {
	
	public StringBasedCellFactory(String blankCellContentSymbol, String mineCellContentSymbol) {
		super.blankCellPrototype = new BlankCell(blankCellContentSymbol);
		super.mineCellPrototype = new MineCell(mineCellContentSymbol);
	}

	/**
	 * Number cells are not prototyped as the others, as these cells data varies, therefore one can prototype 8
	 * number cells with values from 1 to 8.
	 */
	@Override
	public Cell makeNumberCell(Integer adjacentMineCount) {
		return new NumberCell(adjacentMineCount);
	}
}
