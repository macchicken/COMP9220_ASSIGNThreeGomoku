package Gomoku;

import java.util.List;

public class GomokuBoard extends BoardGame {

	private String current;
	private GomokuCareTaker careTaker;
	
	public GomokuBoard() {
		setup();
		steps = 0;
		careTaker=new GomokuCareTaker();
		lengthX=15;
		lengthY=15;
	}

	@Override
	// initialize
	public void setup() {
		b = new String[lengthX][lengthY];
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				b[i][j] = Constants.empty;
			}
		}
	}

	@Override
	// show the Gomoku Board
	public void displayBoard() {
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	// modify board based on the type of command
	public boolean modifyBoard(String p, String c,CommandType ct){
		switch(ct){
			case MOVE:
				String[] command=p.split(",");
				int x=Integer.parseInt(command[0]);
				int y=Integer.parseInt(command[1]);
				if (x>lengthX||y>lengthY){System.out.println("place a chess within the board");return false;}
				x--;y--;
				if (!b[x][y].equals(Constants.empty)){System.out.println("this place has been occupied");return false;}
				careTaker.addHisotry(new Stone(x,y,c),steps);
				b[x][y] = c;
				current=c;
				steps++;return true;
			case REDO:
				String[] redoCommand=p.split("redo");
				int redoSteps=Integer.parseInt(redoCommand[1].trim());
				int redoTo=steps+redoSteps;
				List<Stone> redos=careTaker.getHistorialMoves(steps,redoTo);
				if (redos==null){System.out.println("can not recover moves more than in the history");return false;}
				for (Stone s:redos){
					b[s.getX()][s.getY()] = s.getC();
				}
				steps=redoTo;
				return true;
			case UNDO:
				String[] undoCommand=p.split("undo");
				int backSteps=Integer.parseInt(undoCommand[1].trim());
				int size=careTaker.getSize();
				int start=steps-backSteps;
				List<Stone> backs=careTaker.getHistorialMoves(start,size);
				if (backs==null){System.out.println("can not undo moves more than in the history");return false;}
				else{
					for (Stone s:backs){
						b[s.getX()][s.getY()] = Constants.empty;
					}
					steps=start;
					return true;
				}
		}
		return false;
	}

	@Override
	// 0 draw, 1 Black win, 2 White win , 3 game continue
	public int WinJudge() {
		String winner=gameRule.check(b,current);
		if (Constants.black.equals(winner)){return 1;}
		else if (Constants.white.equals(winner)){return 2;}
		else if (steps>64){return 0;}
		return 3;
	}


	class Stone {

		private int x;
		private int y;
		private String c;


		public Stone() {
			x = 0;
			y = 0;
			c = Constants.empty;
		}
		
		public Stone(int x,int y,String c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
		public String getC() {
			return c;
		}
		
	}

}
