package ufc.br.so.programs;

import ufc.br.so.kernel.systemcalls.SystemCalls;

public class ExitProgram extends Program {
	
	public ExitProgram() {
		this.setName("exit");
		this.setSize(4);
		this.setDescription("help - a helpful text for commands");
	}
	
	@Override
	public void execute() {
		SystemCalls.exit();
	}

}
