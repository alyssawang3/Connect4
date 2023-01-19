import java.util.Scanner;

public class HumanPlayer extends Player{

	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	public void makeMove(Board board) {
		Scanner in = new Scanner(System.in);
		System.out.println(this.name + ", please input your move: ");
		int userInput = in.nextInt() -1;
		
		while (!board.isValid(userInput)) {
			System.out.println("Invalid input, please enter another move: ");
			userInput = in.nextInt() -1;
		}
		board.addMove(userInput, symbol);
		
	}

}
