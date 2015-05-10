package Gomoku;

public abstract class ChessGameBuilder {

	public abstract void buildGameBoard();
	public abstract void buildGameRule();
	public abstract GomokuPlay getChessGame();
}
