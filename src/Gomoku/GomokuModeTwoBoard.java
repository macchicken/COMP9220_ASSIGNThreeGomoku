package Gomoku;

import java.util.List;


public class GomokuModeTwoBoard extends GomokuBoard {

	private GomokuCareTaker careTaker;

	public GomokuModeTwoBoard(int lengthX, int lengthY) {
		super(lengthX, lengthY);
		careTaker=new GomokuCareTaker();
	}

	@Override
	public void setup() {
		super.setup();
	}

	@Override
	public void displayBoard() {
		super.displayBoard();
	}

	//modify board based on the type of command
	@Override
	public void setposition(String p, int c) throws CommandFailException {
		CommandType ct=Constants.validCommand(p);
		String player=Constants.black;
		if (c==2){player=Constants.white;}
		switch(ct){
			case MOVE:
				String[] command=p.split(",");
				int x=Integer.parseInt(command[0]);
				int y=Integer.parseInt(command[1]);
				if (x>lengthX||y>lengthY){throw new CommandFailException("place a chess within the board");}
				x--;y--;
				if (!b[x][y].equals(Constants.empty)){throw new CommandFailException("this place has been occupied");}
				b[x][y]=player;
				careTaker.addHisotry(new Stone(x,y,player),steps);
				steps++;
				break;
			case REDO:
				String[] redoCommand=p.split("redo");
				int redoSteps=Integer.parseInt(redoCommand[1].trim());
				int redoTo=steps+redoSteps;
				List<Stone> redos=careTaker.getHistorialMoves(steps,redoTo);
				if (redos==null){throw new CommandFailException("can not recover moves more than in the history");}
				for (Stone s:redos){
					b[s.getX()][s.getY()] = s.getC();
				}
				steps=redoTo;
				break;
			case UNDO:
				String[] undoCommand=p.split("undo");
				int backSteps=Integer.parseInt(undoCommand[1].trim());
				int size=careTaker.getSize();
				int start=steps-backSteps;
				List<Stone> backs=careTaker.getHistorialMoves(start,size);
				if (backs==null){throw new CommandFailException("can not undo moves more than in the history");}
				else{
					for (Stone s:backs){
						b[s.getX()][s.getY()] = Constants.empty;
					}
					steps=start;
				}
				break;
		}
		current=player;
	}

	@Override
	public int WinJudge() {
		return super.WinJudge();
	}

	class Stone {

		private int x;
		private int y;
		private String c;

		private Stone() {
			x = 0;
			y = 0;
			c = Constants.empty;
		}
		
		private Stone(int x,int y,String c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		private int getX() {
			return x;
		}

		private int getY() {
			return y;
		}
		
		private String getC() {
			return c;
		}
		
	}

}
