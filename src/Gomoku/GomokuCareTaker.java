package Gomoku;

import java.util.ArrayList;
import java.util.List;

public class GomokuCareTaker {

	private List<GomokuModeTwoBoard.Stone> history=new ArrayList<GomokuModeTwoBoard.Stone>();

	public void addHisotry(GomokuModeTwoBoard.Stone stone,int start){
		if (start<history.size()){
			this.history=getHistorialMoves(0,start);
		}
		this.history.add(stone);
	}

	public List<GomokuModeTwoBoard.Stone> getHistorialMoves(int start,int end){
		if (start<0){return null;}
		if (end>history.size()){return null;}
		ArrayList<GomokuModeTwoBoard.Stone> result=new ArrayList<GomokuModeTwoBoard.Stone>();
		for (int i=start;i<end;i++){
			result.add(history.get(i));
		}
		return result;
	}

	public int getSize(){
		return history.size();
	}

}
