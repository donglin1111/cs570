package practice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicGame extends Game 
{
	//Start Game
	public void StartGame() 
	{
		String winner = null;
		while (winner == null) {
			int numInput;
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			try {
				numInput = in.nextInt();
				if(numInput==-1)
				{
					System.out.println("END!");
					break;
				}
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			//TakeStep(numInput);
		}
		
	}
/*	public void TakeStep(int numInput) {
		String[] board =  new String[9];;
		String turn = "X";
		if (board[numInput-1].equals(String.valueOf(numInput))) {
			board[numInput-1] = turn;
			if (turn.equals("X")) {
				turn = "O";
			} else {
				turn = "X";
			}
			//printBoard();
			//winner = checkWinner();
		} else {
			System.out.println("Slot already taken; re-enter slot number:");
			continue;
		}
	}*/
}
