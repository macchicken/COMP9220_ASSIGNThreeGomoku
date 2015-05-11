package Gomoku;

public class ChessGameDirector {

	public void construct(ChessGameBuilder builder,int mode,long timout){
		builder.buildGameBoard(mode,timout);
		builder.buildGameRule();
	}

}
