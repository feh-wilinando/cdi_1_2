package br.com.alura.alura4ioc.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class ConfigurationFactory implements Serializable {

	
	private static final long serialVersionUID = 8756588660568560991L;

	@Produces @ApplicationScoped
	public Properties getProperties() {
		Properties configuration = new Properties();
		
		
		try(InputStream is = ConfigurationFactory.class.getResourceAsStream("/alura.properties")){
			configuration.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return configuration;
	}
	
}
