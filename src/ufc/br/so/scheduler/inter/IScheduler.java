package ufc.br.so.scheduler.inter;

import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.StatisticsModule;
import ufc.br.so.scheduler.model.processor.Process;

public interface IScheduler {

	// Define os parametros para o escalonador
	void setInitialParameters(Configuration c);

	// Carrega um processo no escalonador
	void loadProcess(Process p);

	// Suspende a execucao do scheduler
	void suspend();

	// Retoma a execucao do ponto de onde parou
	void resume();

	// Retorna as estatísticas da execução dos processos
	StatisticsModule getStatistics();
	
}
