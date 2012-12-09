package ufc.br.so.scheduler.model;

/*
 * A model to store the values from a Statistic
 * 
 */
public class StatisticTuples {
	
	private String key;
	private String value;
	
	public StatisticTuples(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return key + ": " + value;
	}
	
	
}
