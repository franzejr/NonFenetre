package ufc.br.so.scheduler.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

	private Map<String,String> configuracoes;
	
	public Configuration() {
		Properties properties = new Properties();
		configuracoes = new HashMap<String, String>();
		try {
			properties.load(new FileInputStream("resources/schedulerConfig.properties"));
			for(Object key:properties.keySet()){
				configuracoes.put((String)key, String.valueOf(properties.getProperty((String)key)));
			}
		} catch (IOException e) {
			System.err.println("No schedulerConfig.properties file found!");
		}
	}

	public Map<String, String> getConfiguracoes() {
		return configuracoes;
	}

}
