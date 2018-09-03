package domain.unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import domain.board.MineSweeperArrayFactory;
import domain.board.MineSweeperBoardFactory;
import domain.cells.AbstractCellFactory;

/**
 * @author julian.munozm
 *
 */
public class MineSweeperArrayFactoryTest {

	/**
	 * Test method for {@link domain.board.MineSweeperArrayFactory#isItemAtCell(int, int, java.lang.Integer[][])}.
	 */
	private static final int ROW = 4;
	private static final int COLUMN = 4;
	@Test
	public void testIsItemAtCell() {
		// arrange
		AbstractCellFactory cellFactory = mock(AbstractCellFactory.class);
		MineSweeperArrayFactory boardFactory = new MineSweeperArrayFactory(cellFactory);
		Integer[][] indexes = {
				{2, 4},
				{6, 7},
				{9,2},
				{ROW,COLUMN},
				{9,9},
				{7,8}
		};
		
		// act
		boolean found = boardFactory.isItemAtCell(ROW, COLUMN, indexes);
		
		// assert
		assertTrue(found);
	}

}
