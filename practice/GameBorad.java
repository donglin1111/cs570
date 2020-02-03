package practice;
import java.io.IOException;

public class GameBorad {
	public static void main(String [] args) throws IOException {
		TicGame Tictac = new TicGame();
		System.out.println("Welcome to 2 Player Tic Tac Toe.");
		System.out.println("--------------------------------");
		Tictac.InitGame();
		System.out.println("X's will play first. Enter a slot number to place X in:");
		Tictac.StartGame();
	}
}
