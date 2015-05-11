package Gomoku;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ChessGameReader {

	private Scanner scan;
	private boolean timeouton;
	
	// switch to turn on the timeout on the inputstream reader
	public ChessGameReader(boolean timeouton) {
		if (!timeouton){
			this.scan=new Scanner(System.in);
		}
	}

	private int readInputStreamWithTimeout(InputStream is, byte[] buf, long timeoutMillis)
			throws IOException {
		int bufferOffset = 0;    //the internal storage buffer offset
		long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;//actual waiting time
		while (System.currentTimeMillis() < maxTimeMillis && bufferOffset < buf.length) { //til read sth from input
			int readLength = Math.min(is.available(), buf.length - bufferOffset); //calculate the acutal read length according to what actual read
			int readResult = is.read(buf, bufferOffset, readLength);
			if (readResult == -1) {// there is no more data because the end of the stream has been reached
				break;
			}
			bufferOffset += readResult;
			if (readResult > 0) { //has read sth
				break;
			}
			try {
				Thread.sleep(100);//wait 0.1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return bufferOffset;
	}
	
	private String readInput(long timeoutMillis) {
		if (timeoutMillis<=0){return null;}
		byte[] inputData = new byte[10240];//set 10KB, should be enough for a simple console application
		int readLength = 0;
		try {
			readLength = readInputStreamWithTimeout(System.in, inputData, timeoutMillis);// wait input
		} catch (IOException e) {
			e.printStackTrace();
		}
		String input=null;
		if (readLength > 0) {input=new String(inputData);input=input.trim();}
		return input;
	}
	
	public String nextLine(long timeout){
		String input=null;
		if (!timeouton) {
			input = this.scan.nextLine();
		}else{
			input=readInput(timeout);
		}
		return input;
	}

	public void close(){
		if (this.scan!=null) {
			this.scan.close();
		}
	}
}
