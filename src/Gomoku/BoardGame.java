package Gomoku;

public abstract class BoardGame {

	protected String[][] b;
	protected int steps;
	protected ChessGameRule gameRule;
	protected int lengthX;
	protected int lengthY;

	public abstract void setup();

	public String getmark(String p) {
		return p;
	}

	public int getsteps() {
		return steps;
	}

	public abstract void displayBoard();
	
	public abstract void setposition(String p, int c) throws CommandFailException;

	public int WinJudge() {// 0 draw, 1 Black win, 2 White win , 3 game continue
		return 3;
	}

	public void setGameRule(ChessGameRule gameRule) {
		this.gameRule = gameRule;
	}

	
}
