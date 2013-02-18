package ufc.br.so.shell.commandline;

import java.util.Scanner;

import ufc.br.so.exception.CommandNotFoundException;
import ufc.br.so.shell.commandline.functions.Function;

public class Shell {
	
	public Shell(){
		interFace();
	}
	
	@SuppressWarnings("resource")
	private void interFace(){
		System.out.println("Starting the Shell Module");
		System.out.println("");
		
		String command = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome!");
		while(true){
			System.out.print(">");
			//ler entrada do usuario
			command = scanner.nextLine();
			try {
				//procura por comando e tenta executar
				executeCommand(command);
			} catch (CommandNotFoundException e) {
				//se o comando nao for reconhecido, avisa o usuário
				System.err.println(e.getMessage());
			}
		}
	}
	
	private void executeCommand(String command) throws CommandNotFoundException {
		//se o comando nao for nulo nem vazio
		if(command != null && !"".equals(command)){
			//Build the class name
			String commandClass = command.substring(0, 1).toUpperCase()+command.substring(1).toLowerCase();
			Function fun;
			try{
				//Create an instance from the function class
				fun = (Function)Class.forName("ufc.br.so.shell.commandline.functions."+commandClass).newInstance();
			}
			catch(ClassNotFoundException e) {
				//If the command doesn't exist, an exception will be throw
				throw new CommandNotFoundException(command);
			}
			catch(Exception e){
				//Any other exception
				e.printStackTrace();
				return;
			}
			if(fun != null){
				//execute the command
				fun.execute();
			}
		}
	}
}
