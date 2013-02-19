package ufc.br.so.programs;

public class InstalledProgramsList extends Program {

	public InstalledProgramsList() {
		this.setName("installed_programs");
		this.setSize(4);
		this.setDescription("installed-programs - get a List of all Installed Programs");
	}
	
	
	@Override
	public void execute() {
		System.out.println("List of Programs...");
		System.out.println( InstalledPrograms.getNameInstalledPrograms() );
	}

}
