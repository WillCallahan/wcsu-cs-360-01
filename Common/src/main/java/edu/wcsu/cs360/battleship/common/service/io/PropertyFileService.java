package edu.wcsu.cs360.battleship.common.service.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Loads properties from the property files specified in the {@link PropertyFileService#PropertyFileService(String...)}
 * constructor. If properties with clashing names exist, then the property that is loaded last in the list will be kept.
 */
public class PropertyFileService {

	private Log log = LogFactory.getLog(this.getClass());
	private Map<Object, Object> propertyMap;
	
	private PropertyFileService() {
		
	}

	public PropertyFileService(String... propertyFileNameArray) {
		this();
		propertyMap = new HashMap<>();
		for (String propertyFileName : propertyFileNameArray)
			loadProperties(propertyFileName);
	}

	/**
	 * Loads properties from the specified file
	 *
	 * @param propertyFileName File to gather properties from
	 */
	private void loadProperties(String propertyFileName) {
		try {
			log.debug("Loading properties from " + propertyFileName);
			InputStream inputStream = loadFile(propertyFileName);
			if (inputStream == null)
				throw new IllegalArgumentException("Unable to find property file " + propertyFileName);
			Properties properties = new Properties();
			properties.load(inputStream);
			for (Map.Entry<Object, Object> property : properties.entrySet()) {
				log.debug("Loaded property from " + propertyFileName + "\r\nKey: " + property.getKey() + "\r\nValue: " + property.getValue());
				propertyMap.put(property.getKey(), property.getValue());
			}
			log.debug("Loaded properties from " + propertyFileName);
		} catch (IOException e) {
			log.error("Unable to read property file " + propertyFileName, e);
		}
	}

	private InputStream loadFile(String fileName) {
		try {
			return new FileInputStream(fileName);
		} catch (IOException e) {
			log.warn("Unable to read property file " + fileName + " - attempting to get file from classpath.", e);
		}
		return this.getClass().getClassLoader().getResourceAsStream(fileName);
	}

	public Object getProperty(Object property) {
		Object foundProperty = propertyMap.get(property);
		log.debug("Getting Property:" + String.format("%n") + "\tProperty Key: " + property  + String.format("%n") + "\tProperty Value: " + foundProperty);
		return foundProperty;
	}
}
