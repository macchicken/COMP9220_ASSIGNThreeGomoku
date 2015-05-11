package Gomoku;

public class CommandFailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	

	public CommandFailException(String message){
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

}
