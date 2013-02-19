package ufc.br.so.shell.commandline.functions;

import ufc.br.so.programs.InstalledPrograms;

public class Help implements Function {

	@Override
	public void execute() {
		System.out.println("---------------");
		System.out.println("HELP");
		
		System.out.println("Commands:");
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("exit") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("lshw") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("statistics") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("getmeminfo") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("installed_programs") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("getdatetime") );
		System.out.println();
		
		System.out.println();
		System.out.println( InstalledPrograms.getDescriptionInstalledProgram("services_running") );
		System.out.println();
		
		System.out.println();
		System.out.println( "help - these help commands" );
		System.out.println();
		
		System.out.println("---------------");
	}

}
