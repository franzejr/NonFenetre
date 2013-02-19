package ufc.br.so.shell.commandline.functions;

import ufc.br.so.programs.InstalledPrograms;

public class Installed_programs implements Function {

	@Override
	public void execute() {
		InstalledPrograms.getInstalledProgram("installed_programs").execute();
	}

	
}
