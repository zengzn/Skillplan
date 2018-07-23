package com.beone.skillplan.configuration;

import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for system properties defined in {@link Property}
 * @author zhen
 *
 */
public final class Configuration {

	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static final Properties properties = new Properties();

	private static void init() {
		Arrays.stream(Property.values()).forEach(Configuration::load);
	}
	
	/**
	 * Returns the value of the given property
	 * @param property
	 * @return Value of the property
	 */
	public static String get(Property property) {
		if(properties.isEmpty()) {
			init();
		}
		String key = property.getName();
		return properties.getProperty(key);
	}

	private static void load(Property property) {
		String name = property.getName();
		String value = System.getProperty(name);
		
		if(value == null) {
			value = property.getDefaultValue();
			logger.debug("Loaded property {} with default value {}",name, value);
		} else {
			logger.debug("Loaded property {} with overriden value {}",name, value);
		}
		
		properties.put(name, value);
	}
	
	private Configuration() {
		throw new AssertionError(this.getClass().getName()+" cannot be instantiated");
	}

}
