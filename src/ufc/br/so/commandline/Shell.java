package ufc.br.so.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
	public Shell(){
		interFace();
	}
	private void interFace(){
		String command = null;
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.print(">");
			try {
				command = buf.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			executeCommand(command);
		}
	}
	private void executeCommand(String command) {
		// TODO Auto-generated method stub
		if(command.compareTo("exit")==0)
			System.exit(0);
		else
			System.out.println("Command not found");
	}
}
