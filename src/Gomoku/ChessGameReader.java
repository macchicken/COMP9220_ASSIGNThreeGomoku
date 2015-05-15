package Gomoku;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ChessGameReader {

	private Scanner scan;
	private boolean timeouton;
	
	// switch to turn on the timeout on the inputstream reader
	public ChessGameReader(boolean timeouton) {
		if (!timeouton){
			this.scan=new Scanner(System.in);
		}
		this.timeouton=timeouton;
	}

	private static String readLine(long timeout) throws InterruptedException {
	    ExecutorService ex = Executors.newSingleThreadExecutor();
	    String input = null;
	    try {
	    	// start working
	        Future<String> result = ex.submit(new ConsoleInputReadTask());
	        try {
	          input = result.get(timeout, TimeUnit.MILLISECONDS);
	        } catch (ExecutionException e) {
	          e.getCause().printStackTrace();
	        } catch (TimeoutException e) {
	          result.cancel(true);
	        }
	    } finally {
	      ex.shutdownNow();
	    }
	    return input;
	  }

	public String nextLine(long timeout){
		String input=null;
		if (!timeouton) {
			input = this.scan.nextLine();
		}else{
			try {
				input=readLine(timeout);
			} catch (InterruptedException e) {
				input=null;
			}
		}
		return input;
	}

	public void close(){
		if (this.scan!=null) {
			this.scan.close();
		}
	}
}
