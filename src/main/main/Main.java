package main;

import java.util.Scanner;

import controller.main.ShellMineSweeperController;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Mine Sweeper, please input the board height, width and the number of mines: ");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();

        String board[] = input.split("\\s+");
        System.out.println(board.length);
    
        if(board.length < 3){
			System.out.println("Incomplete board. \n\nPlease input the board height and width and the number of mines.");
		}
        if(board.length > 3){
			System.out.println("To much arguments. \n\nPlease input the board height and width and the number of mines.");
		}
        
        int height = Integer.parseInt(board[0]);
        int width = Integer.parseInt(board[1]);
        int numberOfMines = Integer.parseInt(board[2]);
        
		ShellMineSweeperController game = new ShellMineSweeperController(height, width, numberOfMines);
		game.gameOn();
	}

}
