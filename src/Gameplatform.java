import Gomoku.ChessGameBuilder;
import Gomoku.ChessGameDirector;
import Gomoku.GomokuGameBuilder;
import Gomoku.GomokuPlay;

/**
 * game entry point, options for passing the builder as follows:
 * @param mode: 
 * 1 - player just has move option , could not redo or undo
 * 2 - player can move, redo and undo
 * @param timout:
 * max time of each round
 * less than 10 seconds would be treated as no time limit
 * @author Barry
 * @since 2015-5-9
 */
public class Gameplatform {

	private static GomokuPlay game = null;

	public static void main(String[] args) {
		System.out.println("~~~~~~~ Welcome ~~~~~~~~ ");
		ChessGameDirector director=new ChessGameDirector();
		ChessGameBuilder builder=new GomokuGameBuilder(); 
		director.construct(builder,2,60000);
		game = builder.getChessGame();
		game.start();
		System.out.println("~~~~~~~ game finished ~~~~~~~~ ");
	}

}
