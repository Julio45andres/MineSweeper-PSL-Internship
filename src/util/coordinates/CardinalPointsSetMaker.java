package coordinates;

/**
 * @author julian.munozm
 *
 */
public class CardinalPointsSetMaker {

	/**
	 * 
	 */
	public CardinalPointsSetMaker() {}
	
	/**
	 * Creates and array with 8 cardinal points. 
	 * 
	 * NW = (ndx, dy) 	N = (d0, dy)	NE = (dx, dy)
	 * W  = (ndx, d0)					E  = (dx, d0)
	 * SW = (ndx, ndy)	S = (d0, ndy)	SE = (dx, ndy)
	 * 
	 * @return The following array: [NW, N, NE, W, E, SW, S, SE].
	 */
	public int[][] getCardinalPoints(){
		final int dx = 1, dy = 1;
		final int ndx = -1, ndy = -1;
		final int d0 = 0;

		final int[][] cardinalPoints = {
				{ndx, dy}, {d0, dy}, {dx, dy},
				{ndx, d0}, {dx, d0},
				{ndx, ndy}, {d0, ndy}, {dy, ndy}
		};
		
		return cardinalPoints;
	}
	
	/**
	 * Creates and array with 4 cardinal points. 
	 * <body>
	 * 					N = (d0, dy)	
	 * W  = (ndx, d0)					E  = (dx, d0)
	 * 					S = (d0, ndy)
	 * <body/>
	 * @return The following array: [N, W, E, S].
	 */
	public int[][] getMainCardinalPoints(){
		final int dx = 1, dy = -1;
		final int ndx = -1, ndy = 1;
		final int d0 = 0;

		final int[][] cardinalPoints = {
					{d0, dy},
				{ndx, d0}, {dx, d0},
					{d0, ndy}
		};
		
		return cardinalPoints;
	}
}
