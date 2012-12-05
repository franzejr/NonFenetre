package ufc.br.so.scheduler.inter;

import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;

public interface IScheduler {

	// Define os parametros para o escalonador
	void setInicialParameters(Configuration c);

	// Carrega um processo no escalonador
	void loadProcess(Process p);

	// Inicia a execucao do scheduler
	void start();

	// Suspende a execucao do scheduler
	void suspend();

	// Retoma a execucao do ponto de onde parou
	void resume();

	// Para a execucao do scheduler
	void stop();

	// Retorna as estatísticas da execução dos processos
	Statistics getStatistics();
	
}
