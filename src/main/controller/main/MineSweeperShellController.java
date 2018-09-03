package controller.main;

import java.util.Scanner;

import controller.player.GameState;
import controller.player.MineSweeperPlayer;
import controller.player.RecursiveMineSweeperPlayer;
import domain.board.MineSweeperArrayFactory;
import domain.board.MineSweeperBoard;
import domain.board.MineSweeperBoardFactory;
import domain.cells.StringBasedCellFactory;

public class MineSweeperShellController {

	private static final String BLANK_CELL_CONTENT_SYMBOL = "-"; 
	private static final String MINE_CELL_CONTENT_SYMBOL = "*";
	//private static final String UNCOVERED_CELL_SYMBOL = ".";
	private MineSweeperPlayer player;
	private MineSweeperBoard board;
	
	public MineSweeperShellController() {}
	
	
	private GameState makeMove(int row, int column, String move){
		switch(move){
			case "U":
				return player.uncoverCell(row, column);
			
			case "P":
				player.putFlagOnCell(row, column);
				break;
		}
		
		return player.check();
	}
	
	
	public void gameOn(){
		
		GameState gameState = GameState.ONGOING;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Mine Sweeper :)\nTo start enter the board height, width and the number of mines.");
        while(gameState.equals(GameState.ONGOING)){
        	System.out.print("Board: ");
    		String gameSubmission = scanner.nextLine();
    		System.out.println();
    		while(gameSubmission.equals("")) {
    			System.out.println("\nNo board submitted.\n\nPlease enter the board height and width and the number of mines.\n");
    			gameSubmission = scanner.nextLine();
    		}

            String game[] = gameSubmission.split("\\s+");
        
            if(game.length < 3){
    			System.out.println("Incomplete board. \n\nPlease enter the board height and width and the number of mines: ");
    		}
            if(game.length > 3){
    			System.out.println("To much arguments. \n\nPlease enter the board height and width and the number of mines.");
    		}
            
            int height = 0;
            int width = 0;
            try{
            	height = Integer.parseInt(game[0]);
            	width = Integer.parseInt(game[1]);
            	int numberOfMines = Integer.parseInt(game[2]);
            	makeBoard(height, width, numberOfMines);
            } catch (NumberFormatException e) {
            	System.out.println("\nWrong input.\n\nPlease enter only numbers.\n");
    			continue;
    		}
	        while(gameState.equals(GameState.ONGOING)){
	        	
	        	System.out.println("Enter a move: ");
	        	System.out.print("Move: ");
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
		        
		        int row = 0;
		        int column = 0;
		        try{
		        	row = Integer.parseInt(move[0])-1;
			        column = Integer.parseInt(move[1])-1;
	            } catch (NumberFormatException e) {
	            	System.out.println("\nWrong input.\n\nPlease enter only numbers for rows and columns.\n");
	    			continue;
	    		}
		        
		        String action = move[2];
		        action = action.toUpperCase();
		        if(!(action.equals("U") || action.equals("P"))){
		        	System.out.println("\nWrong input.\n\nPlease enter only U to uncover and P for flags.\n");
	    			continue;
		        }
		        
		        if(row < 0 || column < 0){
		        	System.out.println("Negative or zero position, please enter valid dimensions.");
		        	continue;
		        }
		        if(row  >= height+1 || column >= width+1){
		        	System.out.format("\n:o the move (%d %d) is out of the board, try again.\n\n", height, width);
		        	continue;
		        }
		        
		        gameState = makeMove(row, column, action);
		        if(gameState.equals(GameState.GAMEOVER)){
		        	System.out.println("You lose :(");
		        	System.out.println("Wanna game again :)? y/n");
		        	String decision = scanner.nextLine();
		        	if(decision.toLowerCase().equals("y")) {
		        		gameState = GameState.ONGOING;
		        		System.out.println();
		        		break;
		        	}
		        	scanner.close();
		        } else if(gameState.equals(GameState.WIN)){
		        	System.out.println("You WIN !!! 8)");
		        	System.out.println("Wanna game again ;)? y/n");
		        	String decision = scanner.nextLine();
		        	if(decision.toLowerCase().equals("y")) {
		        		gameState = GameState.ONGOING;
		        		System.out.println();
		        		break;
		        	}
		        	scanner.close();
		        }
		        
		        System.out.println();
		        String board = player.getBoardString();
		        System.out.println(board); 
	        }
        } 
	}
	
	private void makeBoard(int height, int width, int numberOfMines) {
	
		MineSweeperBoardFactory boardMaker = new MineSweeperArrayFactory(
			new StringBasedCellFactory(BLANK_CELL_CONTENT_SYMBOL, MINE_CELL_CONTENT_SYMBOL)
		); 
	
		board = boardMaker.makeBoard(height, width, numberOfMines);
		player = new RecursiveMineSweeperPlayer(board);
	}
}
