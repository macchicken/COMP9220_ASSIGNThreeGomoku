package Gomoku;


public class GomokuPlay {

	private BoardGame board;
	private boolean end;
	private int winner;// 0 draw, 1 Black win, 2 White win
	private ChessGameReader reader;
	private long timeout;
	
	public GomokuPlay(long timeout) {
		end = false;
		winner = 3;
		if (timeout<10000){
			this.reader=new ChessGameReader(false);
		}else{
			this.reader=new ChessGameReader(true);
			this.timeout=timeout;
		}
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
				command=reader.nextLine(timeout-stop+begin);
				if (command==null){break;}
				try {
					ct=processCommand(command, player);
				} catch (CommandFailException e) {
					System.out.println(e.getMessage());
					ct=null;
				}
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
		displayResult();
	}

	// process and check if the command input is correct
	private CommandType processCommand(String command,String player) throws CommandFailException{
		CommandType ct=Constants.validCommand(command);
		if (ct==null){throw new CommandFailException("Invalid command,Please re-type");}
		int c=1;
		if (Constants.white.equals(player)){c=2;}
		board.setposition(command.trim(),c);
		return ct;
	}

	private String switchPlayer(String player){
		if (Constants.black.equals(player)){player=Constants.white;}
		else if (Constants.white.equals(player)){player=Constants.black;}
		return player;
	}

	public void displayResult() {
		if (winner == 0) {
			System.out.println("Draw!");
		} else if (winner == 1) {
			System.out.println("Black win!");
		} else {
			System.out.println("White win!");
		}
		board.displayBoard();
		reader.close();
	}

	public void setBoard(BoardGame board) {
		this.board = board;
	}

}
