package domain.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.cells.AbstractCellFactory;
import domain.cells.Cell;
import domain.cells.StringBasedCellFactory;

public class StringBasedCellFactoryTest {

	private static final Integer OBJECT_INTEGER = 4;
	private static final int PRIMITIVE_INTEGER = 4;
	private static final String MINE_SYMBOL = "*";
	private static final String BLANK_CELL_SYMBOL = "-";

	/**
	 * Test method for {@link domain.cells.StringBasedCellFactory#makeNumberCell(int)}.
	 */
	@Test
	public void makeNumberCellTest() {
		// arrange
		AbstractCellFactory cellFactory =  new StringBasedCellFactory(MINE_SYMBOL, BLANK_CELL_SYMBOL);
		
		// act	
		Cell numberCell = cellFactory.makeNumberCell(PRIMITIVE_INTEGER);

		// assert
		assertEquals(OBJECT_INTEGER.toString(), numberCell.getContent());
	}

}

//arrange
//act	
//assert