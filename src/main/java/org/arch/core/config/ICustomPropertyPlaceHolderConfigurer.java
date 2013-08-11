package org.arch.core.config;

import java.util.Map;

public interface ICustomPropertyPlaceHolderConfigurer {
	
	/**
	 * Return file locations
	 * @return locations 
	 */
	String[] getResourceLocations();

	/**
	 * Returns a map with the loaded property values 
	 * @return Map
	 */
	 Map<String, String> getResolvedProps();

}