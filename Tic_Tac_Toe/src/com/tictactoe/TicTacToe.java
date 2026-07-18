package com.tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TicTacToe {

	static String[] board;
	static String turn;
	
	//Check Winner Method
	static String checkWinner() {
		for(int a=0; a<8; a++) {
			String line=null;
			
			switch(a){
			case 0:
				line = board[0] + board[1] + board[2];
				break;
				
			case 1:
				line = board[3] + board[4] + board[5];
				break;
				
			case 2:
				line = board[6] + board[7] + board[8];
				break;
				
			case 3:
				line = board[0] + board[3] + board[6];
				break;
				
			case 4:
				line = board[1] + board[4] + board[7];
				break;
				
			case 5:
				line = board[2] + board[5] + board[8];
				break;
				
			case 6:
				line = board[0] + board[4] + board[8];
				break;
				
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			
			/* for x Winner */
			if(line.equals("XXX")) {
				return "X";
			}
			
			/* for O Winner */
			else if(line.equals("OOO")){
				return "O";
			}
		}
		for(int i=0; i<9; i++) {
			if(Arrays.asList(board).contains(String.valueOf(i+1))) {
				break;
			}
			else if(i==9){
				return "draw";
			}
		}
		System.out.println(turn+" ' s turn ; Enter a Slot Number to place "+turn+" in: ");
		return null ;
	}

       /* To print Board */
      static void printBoard() {
    	  System.out.println("|---|---|---|");
    	  System.out.println("| "+board[0] +" | "+board[1]+" | "+board[2]+" | ");
    	  System.out.println("|-----------|");
    	  System.out.println("| "+board[3] +" | "+board[4]+" | "+board[5]+" | ");
    	  System.out.println("|-----------|");
    	  System.out.println("| "+board[6] +" | "+board[7]+" | "+board[8]+" | ");
    	  System.out.println("|---|---|---|");
      }
      public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new String[9];
		turn ="X";
		String winner=null;
		
		for(int i=0; i<9; i++) {
			board[i]=String.valueOf(i+1);
		}
		System.out.println("Welcome to 3x3 Tic Tac Toe");
		printBoard();
		System.out.println("X will play First");
		
		while(winner == null) {
			int numInput;
			
			try {
				numInput=Integer.parseInt(br.readLine());
				/*check range*/
				if(!(numInput > 0 &&  numInput <=9)) {
					System.out.println("Invalid Input; re-Enter slot number:");
					continue;
				}
				/*check if slot available*/
				if(board[numInput - 1 ].equals(String.valueOf(numInput))) {
					board[numInput - 1]=turn;
					
					//Toggle turn
					turn = turn.equals("X")? "O" : "X";
					printBoard();
					winner = checkWinner();
				}
				else {
					System.out.println("Slot already taken ; re-enter slot  number :");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid Input;re-enter 	slot number: ");
				br.readLine();// Consume invalid input to prevent infinite loop
			}
		}
		if(winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing");
		}
		else {
			System.out.println("Congratulations ! "+winner+"' s has won ! Thanks for Playing.");
		}
		br.close();
	}
}
