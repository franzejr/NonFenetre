package ufc.br.so.kernel.spi;

import java.util.Stack;

/*
 * Um report eh um log do que esta acontecendo em uma determinada classe.
 * 
 * Exemplo:
 * O mesmo eh utilizado nas classes dos algoritmos, de forma que como um algoritmo
 * pode demorar muito podemos ver em que passo o algoritmo se encontra.
 */
public class Report {
	
	private Stack<String> reports = new Stack<String>();
	/*
	 * @return report
	 */
	public String getReport(){
		return this.reports.lastElement();
	}
	/*
	 * @param report
	 */
	public void setReport(String report){
		this.reports.add(report);
	}
	/*
	 * @return reports
	 */
	public String getAllReports(){
		return reports.toString();
	}
	
	@Override
	public String toString() {
		return reports.toString();
	}
}
