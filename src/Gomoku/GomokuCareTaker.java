package Gomoku;

import java.util.ArrayList;
import java.util.List;

public class GomokuCareTaker {

	private List<GomokuBoard.Stone> history=new ArrayList<GomokuBoard.Stone>();

	public void addHisotry(GomokuBoard.Stone stone,int start){
		if (start<history.size()){
			this.history=getHistorialMoves(0,start);
		}
		this.history.add(stone);
	}

	public List<GomokuBoard.Stone> getHistorialMoves(int start,int end){
		if (start<0){return null;}
		if (end>history.size()){return null;}
		ArrayList<GomokuBoard.Stone> result=new ArrayList<GomokuBoard.Stone>();
		for (int i=start;i<end;i++){
			result.add(history.get(i));
		}
		return result;
	}

	public int getSize(){
		return history.size();
	}

}
