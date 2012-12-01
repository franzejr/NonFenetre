package ufc.br.so.scheduler.inter;

import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;

public interface IScheduler {
	//Define os parâmetros para o escalonador
	void setInicialParameters(Configuration c);
	//Carrega um processo no escalonador
	void loadProcess(Process p);
	//Inicia a execução do scheduler
	void start();
	//Suspende a execução do scheduler
	void suspend();
	//Retoma a execução do ponto de onde parou
	void resume();
	//Para a execução do scheduler
	void stop();
	//Retorna as estatísticas da execução dos processos
	Statistics getStatistics();
}
