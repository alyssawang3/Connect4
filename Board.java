
public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	
	// create 2d array to store the board
	private char[][] boardArray = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	
	private int lastCol = -1;
	private int lastRow = -1;
	
	public Board() {
		reset();
	}
	
	public void printBoard() {
		for (int i = 0; i <NUM_OF_ROW; i++) {
			String printRow = new String("");
			char[] row = this.boardArray[i];
			
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				printRow += "|" + row[j];
				
				if (j == NUM_OF_COLUMNS-1) {
					printRow += "|";
				}
			}
			System.out.println(printRow);
		}
	}
	
	public int getHeight() {
		return NUM_OF_ROW;
	}
	
	public int getWidth() {
		return NUM_OF_COLUMNS;
	}
	public boolean isValid(int column) {
		// check if move is valid
		if (this.boardArray[0][column] == ' ') {
			return true;
		}
		return false;
	}
	
	public int addMove(int userInput, char symbol) {
		int rowNum = -1;
		for (int i = NUM_OF_ROW-1; i >= 0; i--) {
			char[] row = this.boardArray[i];
			if (row[userInput] == ' ') {
				row[userInput] = symbol;
				rowNum = i;
				break;
			}
		}
		lastCol = userInput;
		lastRow = rowNum;
		return rowNum;
	}
	
	public void removeSymbol(int rowNum, int colNum) {
		boardArray[rowNum][colNum] = ' ';
	}
	
	
	public String getHorizontal(int rowNum) {
		return new String(boardArray[rowNum]);
	}
	
	public String getVertical(int colNum) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < NUM_OF_ROW; i++) {
			sb.append(boardArray[i][colNum]);
		}
		
		return sb.toString();
	}
	
	public String getUpDiagonal(int rowNum, int colNum) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < NUM_OF_ROW; i++) {
			int x = colNum + rowNum - i;
			
			if (0 <= x && x < NUM_OF_COLUMNS) {
				sb.append(boardArray[i][x]);
			}
		}
		return sb.toString();
	}
	
	public String getDownDiagonal(int rowNum, int colNum) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < NUM_OF_ROW; i++) {
			int x = colNum - rowNum + i;
			
			if (0 <= x && x < NUM_OF_COLUMNS) {
				sb.append(boardArray[i][x]);
			}
		}
		return sb.toString();
		
	}
	
	public boolean containsWin() {
		
		if (lastRow == -1 || lastCol == -1) {
			return false;
		}
		
		char symbol = boardArray[lastRow][lastCol];
		
		StringBuilder sb = new StringBuilder();
		sb.append(symbol).append(symbol).append(symbol).append(symbol);
		String winStr = sb.toString();
		
		if (getHorizontal(lastRow).contains(winStr)) {
			return true;
		}
		if (getVertical(lastCol).contains(winStr)) {
			return true;
		}
		if (getUpDiagonal(lastRow, lastCol).contains(winStr)) {
			return true;
		}
		if (getDownDiagonal(lastRow, lastCol).contains(winStr)) {
			return true;
		}
		return false;
	}
	
	public boolean isTie() {
		for (int i = 0; i <NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (boardArray[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public void reset() {
		// populate boardArray
		for (int i = 0; i <NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				boardArray[i][j] = ' ';
			}
		}
	}
	
}
