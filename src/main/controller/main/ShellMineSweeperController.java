package controller.main;

import java.util.Scanner;

import controller.player.GameState;
import controller.player.MineSweeperPlayer;
import controller.player.RecursiveMineSweeperPlayer;
import domain.board.MineSweeperArrayFactory;
import domain.board.MineSweeperBoard;
import domain.board.MineSweeperBoardFactory;
import domain.cells.StringBasedCellFactory;

public class ShellMineSweeperController {

	private static final String BLANK_CELL_CONTENT_SYMBOL = "-"; 
	private static final String MINE_CELL_CONTENT_SYMBOL = "*";
	private static final String UNCOVERED_CELL_SYMBOL = ".";
	private final int height, width;
	MineSweeperPlayer player;
	
	public ShellMineSweeperController(int height, int width, int numberOfMines) {
		this.height = height;
		this.width = width;
		
		MineSweeperBoard board;
	
		MineSweeperBoardFactory boardMaker = new MineSweeperArrayFactory(
			new StringBasedCellFactory(BLANK_CELL_CONTENT_SYMBOL, MINE_CELL_CONTENT_SYMBOL)
		); 
	
		board = boardMaker.makeBoard(height, width, numberOfMines);
		player = new RecursiveMineSweeperPlayer(board);
	}
	
	
	private GameState makeMove(int row, int column, String move){
		switch(move){
			case "U":
				return player.uncoverCell(row, column);
			
			case "u":
				return player.uncoverCell(row, column);
			
			case "P":
				player.putFlagOnCell(row, column);
				break;
				
			case "p":
				player.putFlagOnCell(row, column);
				break;
		}
		
		return player.check();
	}
	
	
	public void gameOn(){
		
		GameState gameState = GameState.ONGOING;
		
		Scanner scanner = new Scanner(System.in);
        
        while(gameState.equals(GameState.ONGOING)){
        	
        	System.out.println("Please Enter a move: ");
        	String input = scanner.nextLine();
        	
        	while(input.equals("")) {
    			System.out.println("\nNo move submitted.\n\nPlease enter a move.\n");
    			System.out.print("Move: ");
    			input = scanner.nextLine();
    		}

            String move[] = input.split("\\s+");
        
	        if(move.length < 3){
				System.out.println("Incomplete move. \n\nPlease input a row number, a column number and the move.");
				continue;
			}
	        if(move.length > 3){
				System.out.println("To much arguments. \n\nPlease input a row number, a column number and the move.");
				continue;
			}
	        
	        int row = Integer.parseInt(move[0])-1;
	        int column = Integer.parseInt(move[1])-1;
	        String action = move[2];
	        
	        if(row <= 0 || column <= 0){
	        	System.out.println("Negative or zero position, please enter valid dimensions.");
	        	continue;
	        }
	        if(row  >= height || column >= width){
	        	System.out.format("The move (%d %d) is out of the board, please enter valid dimensions.", height, width);
	        	continue;
	        }
	        
	        gameState = makeMove(row, column, action);
	        if(gameState.equals(GameState.GAMEOVER)){
	        	System.out.println("You lose :(");
	        	scanner.close();
	        }
	        
	        System.out.println();
	        String board = player.getBoardString();
	        System.out.println(board); 
        }
        
        
	}
	
}
