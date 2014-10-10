package uk.ac.manchester.skeleton;

import org.springframework.beans.factory.annotation.Required;

/**
 * A simple Spring bean. It has two basic properties, with both getters and
 * setters in the standard Java Beans style.
 */
public class Configuration {
	private String version;
	private String fruit;
	
	public String getVersion() {
		return version;
	}

	// This is an optional property
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getFruit() {
		return fruit;
	}

	// This is a mandatory property; Spring will fail to start your app (with a
	// fairly clear error message) if you don't supply it.
	@Required
	public void setFruit(String fruit) {
		this.fruit = fruit;
	}
}
