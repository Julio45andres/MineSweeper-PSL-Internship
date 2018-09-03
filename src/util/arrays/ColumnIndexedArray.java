package arrays;

/**
 * @author julian.munozm
 *
 */
public class ColumnIndexedArray<T> {

	T[][] myArray;
	/**
	 * This gives to 2d arrays some Collection powers.
	 */
	public ColumnIndexedArray(T[][] array) {
		this.myArray = array;
	}
	
	public boolean inRange(int row, int column){
		if(row < 0 || column < 0) return false;
		return row < myArray.length && column < myArray[0].length;		
	}
	
	public T get(int row, int column){
		for(int i = 0; i < myArray.length; i++)
			for(int j = 0; j < myArray.length; j++)
				if(i == row && j == column) return myArray[i][j];
		
		return null;
	}
	
	/**
	 * Gets the value at the last column index
	 * TODO: refactor to support more than 2 indexes
	 * @return Value V at last column [i][c1, c2, ..., V].
	 */
	public T get(T[] indexValues){
		if(!(myArray[0].length-1 == indexValues.length)) throw new IndexOutOfBoundsException();
		Boolean found;
		for(int i = 0; i < myArray.length; i++){
			found = true;
			for(int j = 0; j < myArray[0].length-1; j++)
				if(!(myArray[i][j].equals(indexValues[j]))) found = false;
			
			if(found) return myArray[i][myArray[0].length-1];
		}
		
		return null;
	}
	
	public void set(T[] indexValues, T value){
		if(!(myArray[0].length-1 == indexValues.length)) throw new IndexOutOfBoundsException();
		Boolean found;
		for(int i = 0; i < myArray.length; i++){
			found = true;
			for(int j = 0; j < myArray[0].length-1; j++)
				if(!(myArray[i][j].equals(indexValues[j]))) found = false;
			
			if(found) myArray[i][myArray[0].length-1] = value;
		}
	}
}
