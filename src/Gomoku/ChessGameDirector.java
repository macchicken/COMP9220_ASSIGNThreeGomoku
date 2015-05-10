package Gomoku;

public class ChessGameDirector {

	public void construct(ChessGameBuilder builder){
		builder.buildGameBoard();
		builder.buildGameRule();
	}

}
