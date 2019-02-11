package com.sma.delivery.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProyectProperties {
	private static final Logger LOGGER = Logger.getLogger( ProyectProperties.class.getName() );
	private static Properties props = new Properties();

	
	public ProyectProperties () {
		if (props.size() <= 0) {
			setProperties();
		}
	}

	public String getProperty(String propertyName) {
		return props.getProperty(propertyName);
	}
	
	private void setProperties() {
		// se carga el archivo properties WEB-INF/classes/mailer.properties
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/mailer.properties")){
			props.load(inputStream);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e); 	
			throw new NullPointerException();
		}
	}

}
