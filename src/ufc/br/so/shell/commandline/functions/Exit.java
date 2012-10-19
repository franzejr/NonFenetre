package ufc.br.so.shell.commandline.functions;

import ufc.br.so.kernel.systemcalls.SystemCalls;

public class Exit implements Function {

	@Override
	public void execute() {
		SystemCalls.exit();
	}

}
