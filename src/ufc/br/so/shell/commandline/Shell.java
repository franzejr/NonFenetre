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
		String command = null;
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print(">");
			//ler entrada do usuário
			command = scanner.nextLine();
			try {
				//procura por comando e tenta executar
				executeCommand(command);
			} catch (CommandNotFoundException e) {
				//se o comando não for reconhecido, avisa o usuário
				System.err.println(e.getMessage());
			}
		}
	}
	
	private void executeCommand(String command) throws CommandNotFoundException {
		//se o comando não for nulo nem vazio
		if(command != null && !"".equals(command)){
			//constroi o nome da classe respectiva
			String commandClass = command.substring(0, 1).toUpperCase()+command.substring(1).toLowerCase();
			Function fun;
			try{
				//cria uma instância da classe
				fun = (Function)Class.forName("ufc.br.so.shell.commandline.functions."+commandClass).newInstance();
			}
			catch(ClassNotFoundException e) {
				//se o comando não existir, gera exceção espefífica
				throw new CommandNotFoundException(command);
			}
			catch(Exception e){
				//qualquer exceção que possa ser gerada
				e.printStackTrace();
				return;
			}
			if(fun != null){
				//executa o comando
				fun.execute();
			}
		}
	}
}
