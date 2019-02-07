package com.sma.delivery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProyectProperties {
	private static Properties props = null;
	static {
		setProperties();
	}

	public static String getProperty(String propertyName) {
		return props.getProperty(propertyName);
	}

	private static void setProperties() {
		props = new Properties();
		InputStream input = null;

		try {
			System.out.println("Exists :" + new File("WebContent/WEB-INF/config.properties").exists());
			input = new FileInputStream("WebContent/WEB-INF/config.properties");

			// load a properties file
			props.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
