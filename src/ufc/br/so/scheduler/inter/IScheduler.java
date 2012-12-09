package ufc.br.so.scheduler.inter;

import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Statistics;
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

	// Retorna as estat�sticas da execu��o dos processos
	Statistics getStatistics();
	
}