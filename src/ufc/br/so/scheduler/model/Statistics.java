package ufc.br.so.scheduler.model;

import java.util.Map;
/*
 * Classe geral para estatisticas gerais do SO
 * 
 */
public class Statistics {

	private Map<String,String> values;
	
	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		return values.toString();
	}
	
}
