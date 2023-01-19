import java.util.Random;

public class AIPlayer extends Player{

	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}
	
	private boolean checkStr(String str) {
		String temp="";
		for (int j = 0; j < str.length(); j++){
		    char c = str.charAt(j);     
		    if (c != ' ' && c != symbol && c != '$') {
		    	temp = str.replace(c, '$');
		    	break;
		    }
		}
		return temp.contains("$$$$");
	}
	private void updateSymbol(int rowNum, int colNum) {
		board.removeSymbol(rowNum, colNum);
		board.addMove(colNum, symbol);
	}
	
	@Override
	public void makeMove(Board board) {
		StringBuilder sb = new StringBuilder();
		sb.append(symbol).append(symbol).append(symbol).append(symbol);
		String winStr = sb.toString();
		
		for (int i = 0; i < board.getWidth(); i++) {
			if (board.isValid(i)) {
				int rowNum = board.addMove(i, symbol);
				if (board.getVertical(i).contains(winStr) || 
						board.getHorizontal(rowNum).contains(winStr) ||
						board.getUpDiagonal(rowNum,i).contains(winStr) ||
						board.getDownDiagonal(rowNum, i).contains(winStr)) {
					return;
				}else {
					board.removeSymbol(rowNum, i);
				}
			}
		}
		
		for (int i = 0; i < board.getWidth(); i++) {
			if (board.isValid(i)) {
				int rowNum = board.addMove(i, '$');
				String str = board.getHorizontal(rowNum);
				if (checkStr(str)) {
					updateSymbol(rowNum, i);
					return;
				}
				str = board.getVertical(i);
				if (checkStr(str)) {
					updateSymbol(rowNum, i);
					return;
				}
				str = board.getUpDiagonal(rowNum, i);
				if (checkStr(str)) {
					updateSymbol(rowNum, i);
					return;
				}
				str = board.getDownDiagonal(rowNum, i);
				if (checkStr(str)) {
					updateSymbol(rowNum, i);
					return;
				}
				board.removeSymbol(rowNum, i);
			}
		}
		Random rand = new Random();
		int randNum = rand.nextInt(7);
		while (!board.isValid(randNum)) {
			randNum = rand.nextInt(7);
		}
		
		board.addMove(randNum, symbol);
	}
}
