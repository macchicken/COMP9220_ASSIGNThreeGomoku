package Gomoku;


public class GomokuPlay {

	private BoardGame board;
	private boolean end;
	private int winner;// 0 draw, 1 Black win, 2 White win
	private long timeout;

	public GomokuPlay(int timeout) {
		end = false;
		winner = 3;
		this.timeout=timeout;
	}


	public void start() {
		String command = null;
		String player=Constants.black;
		while (!end) {
			board.displayBoard();
			System.out.println(Constants.getPlayerName(player)+"'s turn: What do you wish to do(redo numofoves or undo numofoves or x,y to place a new disc)?");
			boolean needJudge=false;
			CommandType ct=null;
			long begin=System.currentTimeMillis();long stop=begin;
			do {
				command=Tools.readInput(timeout-begin+stop);
				if (command==null){break;}
				ct=processCommand(command, player);
				stop=System.currentTimeMillis();
			} while (ct==null);
			if (CommandType.MOVE.equals(ct)){needJudge=true;}
			if (needJudge){
				winner = board.WinJudge();
				if (winner != 3) {
					end = true;
				}
				else{player=switchPlayer(player);}
			}else{
				player=switchPlayer(player);
			}
		}
		displayResult(winner);
	}

	private CommandType processCommand(String command,String player){
		CommandType ct=validCommand(command);
		if (ct==null){System.out.println("Invalid command,Please re-type");return null;}
		boolean modified=board.modifyBoard(command.trim(),player,ct);
		if (!modified){System.out.println("Invalid command,Please re-type");return null;}
		return ct;
	}

	private String switchPlayer(String player){
		if (Constants.black.equals(player)){player=Constants.white;}
		else if (Constants.white.equals(player)){player=Constants.black;}
		return player;
	}

	// Check if the command input is correct
	private CommandType validCommand(String command) {
		command=command.trim();
		if (Constants.movePattern.matcher(command).matches()){return CommandType.MOVE;}
		if (Constants.redoPattern.matcher(command).matches()){return CommandType.REDO;}
		if (Constants.undoPattern.matcher(command).matches()){return CommandType.UNDO;}
		return null;
	}
	
	public void displayResult(int i) {
		if (i == 0) {
			System.out.println("Draw!");
		} else if (i == 1) {
			System.out.println("Black win!");
		} else {
			System.out.println("White win!");
		}
		board.displayBoard();
	}

	public void setBoard(BoardGame board) {
		this.board = board;
	}

}
