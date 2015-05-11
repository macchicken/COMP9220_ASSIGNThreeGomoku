package Gomoku;

public class GomokuGameBuilder extends ChessGameBuilder {

	private GomokuPlay gomokuPlay;
	private BoardGame boardGame;
	
	@Override
	public void buildGameBoard(int mode,long timeout) {
		gomokuPlay=new GomokuPlay(timeout);
		switch(mode){
			case 1: this.boardGame = new GomokuBoard(15, 15);break;
			case 2: this.boardGame = new GomokuModeTwoBoard(15, 15);break;
			default: this.boardGame = new GomokuBoard(15, 15);break;
		}
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
