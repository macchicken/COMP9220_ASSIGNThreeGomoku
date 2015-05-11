package Gomoku;

public abstract class ChessGameBuilder {

	/*
	 * @param mode - game mode for with or without redo and undo function
	 * @param timeout - the timeout for each aournd
	 */
	public abstract void buildGameBoard(int mode,long timeout);
	public abstract void buildGameRule();
	public abstract GomokuPlay getChessGame();
}
