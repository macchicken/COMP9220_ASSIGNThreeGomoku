package Gomoku;

import java.util.regex.Pattern;

public class Constants {

	public static final String black="X";
	public static final String white="O";
	public static final String empty="*";
	public static final Pattern movePattern = Pattern.compile("[1-9][0-9]*[\\s\t\n]*,[\\s\t\n]*[1-9][0-9]*");
	public static final Pattern redoPattern = Pattern.compile("redo[\\s\t\n][1-9][0-9]*");
	public static final Pattern undoPattern = Pattern.compile("undo[\\s\t\n][1-9][0-9]*");
	
	public static String getPlayerName(String color){
		return color.equals(black)?"Black":"White";
	}

}
