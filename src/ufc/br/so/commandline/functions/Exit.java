package ufc.br.so.commandline.functions;

public class Exit implements Function {

	@Override
	public void execute() {
		System.exit(0);
	}

}
