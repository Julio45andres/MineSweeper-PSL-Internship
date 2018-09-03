package arrays;

public class ArrayQuery {

	public ArrayQuery() {}
	
	public boolean inRange(int row, int column, Integer[][] index){
		if(row < 0 || column < 0) return false;
		return row < index.length && column < index[0].length;		
	}
	
	public boolean inRange(int row, int column, int height, int width){
		if(row < 0 || column < 0) return false;
		return row < height && column < width;		
	}

}
