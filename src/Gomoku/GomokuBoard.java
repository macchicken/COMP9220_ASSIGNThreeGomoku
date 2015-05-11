package Gomoku;


public class GomokuBoard extends BoardGame {

	protected String current;
	
	public GomokuBoard(int lengthX,int lengthY) {
		this.lengthX=lengthX;
		this.lengthY=lengthY;
		setup();
		steps = 0;
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
	public void setposition(String p, int c) throws CommandFailException{
		String player=Constants.black;
		if (c==2){player=Constants.white;}
		String[] command=p.split(",");
		int x=Integer.parseInt(command[0]);
		int y=Integer.parseInt(command[1]);
		if (x>lengthX||y>lengthY){throw new CommandFailException("place a chess within the board");}
		x--;y--;
		if (!b[x][y].equals(Constants.empty)){throw new CommandFailException("this place has been occupied");}
		b[x][y]=player;
		current=player;
		steps++;
	}

	@Override
	// 0 draw, 1 Black win, 2 White win , 3 game continue
	public int WinJudge() {
		String winner=gameRule.check(b, current);
		if (Constants.black.equals(winner)){
			return 1;
		}else if (Constants.white.equals(winner)){
			return 2;
		}else if (steps==64){
			return 0;
		}
		return 3;
	}


}
