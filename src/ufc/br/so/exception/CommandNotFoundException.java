package ufc.br.so.exception;

public class CommandNotFoundException extends Exception {

	private static final long serialVersionUID = 484906722460490628L;

	public CommandNotFoundException(String command) {
		super("The command " + command + " is not a known command!");
	}
}
