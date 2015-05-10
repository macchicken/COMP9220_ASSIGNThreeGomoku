package Gomoku;

public class GomokuGameBuilder extends ChessGameBuilder {

	private GomokuPlay gomokuPlay=new GomokuPlay(10000);
	private BoardGame boardGame;
	
	@Override
	public void buildGameBoard() {
		this.boardGame=new GomokuBoard();
	}

	@Override
	public void buildGameRule() {
		this.boardGame.setGameRule(new GomokuGameRule());
	}

	@Override
	public GomokuPlay getChessGame() {
		gomokuPlay.setBoard(boardGame);
		return gomokuPlay;
	}

}
