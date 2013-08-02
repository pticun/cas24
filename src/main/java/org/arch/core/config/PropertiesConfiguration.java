package org.arch.core.config;

/**
 * PropertiesConfiguration offers a method to access the configuration
 * properties loaded by CustomPropertyPlaceholderConfigurer
 * 
 *
 */
public class PropertiesConfiguration {
	private ICustomPropertyPlaceHolderConfigurer holder = null;
	public static final String BEAN_NAME = "propertiesConfiguration";
	
	/**
	 * Constructor
	 * @param holder
	 */
	public PropertiesConfiguration(ICustomPropertyPlaceHolderConfigurer holder){
		this.holder = holder; 
	}
	
	
	/**
	 * Return value for the current key
	 * @return
	 */
	public String getProperty(String key){
		return this.holder.getResolvedProps().get(key);
	}
}
