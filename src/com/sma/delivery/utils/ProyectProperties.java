package com.sma.delivery.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProyectProperties {
	private static final Logger LOGGER = Logger.getLogger( ProyectProperties.class.getName() );
	private static Properties props = null;

	
	public ProyectProperties () {
		if (props==null) {
			setProperties();
		}
	}

	public String getProperty(String propertyName) {
		return props.getProperty(propertyName);
	}
	
	private void setProperties() {
		props = new Properties();
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/mailer.properties")){
			// load a properties file
			props.load(inputStream);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e); 	
			throw new NullPointerException();
		}
	}

}
