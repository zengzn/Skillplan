package com.beone.skillplan.configuration;

/**
 * skillplan properties which are expected to be passed to the application server as system properties.
 * @author zhen
 *
 */
public enum Property {


	ALLOWED_ORIGIN_URL("skillplan.allowed.origin.url","https://service.beone-group.com/skillplan/");
	
	private String name;
	private String defaultValue;

	private Property(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	
	public String getName() {
		return name;
	}
}
