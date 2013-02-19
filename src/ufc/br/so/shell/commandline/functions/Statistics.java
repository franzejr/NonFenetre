package ufc.br.so.shell.commandline.functions;

import ufc.br.so.programs.InstalledPrograms;


public class Statistics implements Function {

	@Override
	public void execute() {
		InstalledPrograms.getInstalledProgram("statistics").execute();
	}

}
