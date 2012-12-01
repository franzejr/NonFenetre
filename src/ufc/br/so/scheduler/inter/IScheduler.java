package ufc.br.so.scheduler.inter;

import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;

public interface IScheduler {
	//Define os par�metros para o escalonador
	void setInicialParameters(Configuration c);
	//Carrega um processo no escalonador
	void loadProcess(Process p);
	//Inicia a execu��o do scheduler
	void start();
	//Suspende a execu��o do scheduler
	void suspend();
	//Retoma a execu��o do ponto de onde parou
	void resume();
	//Para a execu��o do scheduler
	void stop();
	//Retorna as estat�sticas da execu��o dos processos
	Statistics getStatistics();
}
