package ufc.br.so.shell.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ufc.br.so.shell.commandline.functions.Function;

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
				e.printStackTrace();
			}
			executeCommand(command);
		}
	}
	private void executeCommand(String command) {
		try{
			command = command.substring(0, 1).toUpperCase()+command.substring(1).toLowerCase();
			Function fun = (Function)Class.forName("ufc.br.so.commandline.functions."+command).newInstance();
			fun.execute();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Command not found");
		}
			
	}
}
