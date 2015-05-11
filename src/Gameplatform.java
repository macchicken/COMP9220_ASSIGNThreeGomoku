import Gomoku.ChessGameBuilder;
import Gomoku.ChessGameDirector;
import Gomoku.GomokuGameBuilder;
import Gomoku.GomokuPlay;

public class Gameplatform {

	private static GomokuPlay game = null;

	public static void main(String[] args) {
		System.out.println("~~~~~~~ Welcome ~~~~~~~~ ");
		ChessGameDirector director=new ChessGameDirector();
		ChessGameBuilder builder=new GomokuGameBuilder(); 
		director.construct(builder,2,9000);
		game = builder.getChessGame();
		game.start();
		System.out.println("~~~~~~~ game finished ~~~~~~~~ ");
	}

}
