package ufc.br.so.programs;

import ufc.br.so.kernel.systemcalls.SystemCalls;
import java.util.List;
import ufc.br.so.memory.Page;

public class ExitProgram extends Program {
	
	public ExitProgram() {
		this.setName("exit");
		this.setSize(1);
		this.setDescription("help - a helpful text for commands");
	}
	
	@Override
	public void execute() {
		List<Page> busyPages = this.setBusyPages();
		SystemCalls.exit();
		freePages(busyPages);
	}

}
