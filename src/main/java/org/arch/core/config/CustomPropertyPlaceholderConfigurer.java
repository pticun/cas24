package org.arch.core.config;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.arch.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

/**
 * A property resource configurer that resolves placeholders in bean property
 * values of context definitions. It <i>pulls</i> values from a properties file
 * into bean definitions.
 * 
 * 
 */
public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean,
		ICustomPropertyPlaceHolderConfigurer {

	private static final Logger log = LoggerFactory.getLogger(CustomPropertyPlaceholderConfigurer.class);

	public static final String BEAN_NAME = "propertyPlaceholderConfigurer";

	private Map<String, String> resolvedProps;

	private String[] locations = null;

	/**
	 * Set locations
	 * 
	 * @param resources
	 */

	@Override
	public void setLocations(final Resource[] locations) {
		super.setLocations(locations);
		if (locations != null) {
			this.locations = new String[locations.length];
			for (int i = 0; i < locations.length; i++) {
				this.locations[i] = locations[i].getFilename();
			}
		}
	}

	@Override
	public String[] getResourceLocations() {
		return locations;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 * #processProperties
	 * (org.springframework.beans.factory.config.ConfigurableListableBeanFactory
	 * , java.util.Properties)
	 */
	@Override
	protected void processProperties(final ConfigurableListableBeanFactory beanFactoryToProcess, final Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		resolvedProps = new HashMap<String, String>();
		for (final Object key : props.keySet()) {
			final String keyStr = key.toString();
			if (log.isDebugEnabled()) {
				log.debug("keyStr:" + keyStr + "-value:" + props.getProperty(keyStr));
			}
			resolvedProps.put(keyStr, props.getProperty(keyStr));
		}
	}

	@Override
	public Map<String, String> getResolvedProps() {
		return Collections.unmodifiableMap(resolvedProps);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#
	 * convertProperties(java.util.Properties)
	 */
	@Override
	protected void convertProperties(final Properties props) {
		super.convertProperties(props);
		transformProperties(props);
	}

	/**
	 * Transform loaded properties on host name dependent
	 * 
	 * - If it founds a global pattern (*.)
	 * 
	 * @param props
	 */
	private void transformProperties(final Properties props) {
		final String hostname = CoreUtils.getCurrentHostName();
		final String hostNamePattern = hostname + ".";
		final String globalPattern = "*.";
		final String globalPatternRegularExpression = "\\*\\.";

		final Properties properties = new Properties();

		final Enumeration<?> propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			final String propertyName = (String) propertyNames.nextElement();
			final String propertyValue = props.getProperty(propertyName);

			final String hostPropertyName = propertyName.replaceFirst(globalPatternRegularExpression, hostNamePattern);
			final String cleanedHostPropertyName = hostPropertyName.replaceFirst(hostNamePattern, "");

			if (propertyName.startsWith(hostNamePattern)) {
				// Idealist. host name property
				properties.setProperty(cleanedHostPropertyName, propertyValue);
			}
			else if (propertyName.startsWith(globalPattern)) {
				// Replace global patterns with host name properties
				// Ignore if we have specific host property defined
				if (!properties.containsKey(cleanedHostPropertyName)) {
					properties.setProperty(cleanedHostPropertyName, propertyValue);
				}
			}
			else {
				// Ignoring any other host name
				log.info("Ignoring property " + propertyName);
			}
		}

		props.clear();
		props.putAll(properties);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Configuration module loaded...");
	}

}
