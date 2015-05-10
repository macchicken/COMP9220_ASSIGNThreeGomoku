package Gomoku;

public class GomokuGameRule extends ChessGameRule{

	@Override
	public String check(String[][] board,String player){
		String winner="";
		for (int x=0;x<board.length;x++)
	    {
	        for (int y=0; y < board[x].length-4; y++ )
	        {
	            if (player == board[x][y]
	            	&&player == board[x][y+1]
	            	&&player == board[x][y+2]
	            	&&player == board[x][y+3]
	            	&&player == board[x][y+4])
	            {
	            	winner=player;
	            }
	        }
	    }
		if (!"".equals(winner)){return winner;}
	    // vertical
	    for (int x=0;x<board.length-4;x++){
	    	for (int y=0; y < board[x].length; y++ )
	        {
	            if (player == board[x][y]
	            	&&player == board[x+1][y]
	            	&&player == board[x+2][y]
	            	&&player == board[x+3][y]
	            	&&player == board[x+4][y])
	            {
	            	winner=player;
	            }
	        }
	    }
		if (!"".equals(winner)){return winner;}
	    // for '\' line
	    for (int x=0;x<board.length-4;x++){
	    	for (int y=0; y < board[x].length-4; y++ )
	        {
	            if (player == board[x][y]
	            	&&player == board[x+1][y+1]
	            	&&player == board[x+2][y+2]
	            	&&player == board[x+3][y+3]
	            	&&player == board[x+4][y+4])
	            {
	            	winner=player;
	            }
	        }
	    }
		if (!"".equals(winner)){return winner;}
	    // '/' line
	    for (int x=0;x<board.length-4;x++){
	    	for (int y=4; y < board[x].length; y++ )
	    	{
	    		if (player == board[x][y]
    				&&player == board[x+1][y-1]
					&&player == board[x+2][y-2]
					&&player == board[x+3][y-3]
	    			&&player == board[x+4][y-4])
	    		{
	    			winner=player;
	    		}
	    	}
	    }
	    return winner;
	}

}
