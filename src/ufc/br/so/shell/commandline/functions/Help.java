package ufc.br.so.shell.commandline.functions;

public class Help implements Function {

	@Override
	public void execute() {
		System.out.println("---------------");
		System.out.println("HELP");
		
		System.out.println("Commands:");
		System.out.println();
		System.out.println("help - a helpful text for commands");
		System.out.println();
		System.out.println("lshw - lshw is a small tool to extract detailed information on the hardware configuration of the machine. It can report exact memory configuration, firmware version, mainboard configuration,");
		System.out.println();
		System.out.println("statistics - Statistics from processess");
		System.out.println();
		System.out.println("exit - NonFenetre exit");
		
	}

}
