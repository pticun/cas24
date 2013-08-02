package org.arch.core.util;

import java.util.Locale;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Utilities for core purpose
 * 
 * 
 */
public final class CoreUtils {

	private static final String HOST_NAME_PROPERTY = "environment";

	private static final String INSTANCE_NAME = "weblogic.Name";

	private static final String DEFAULT_HOST_NAME = "";

	private static final Logger log = LoggerFactory.getLogger(CoreUtils.class);

	private static final String LOCALE_SEPARATOR = "_";

	/** Hide **/
	private CoreUtils() {
	}

	/**
	 * Return host name from the current installation
	 * 
	 * If this property is not defined in the System (-Denvironment=pro)
	 * 
	 * @return current host name
	 */
	public static String getCurrentHostName() {
		if (log.isDebugEnabled()) {
			log.debug("CoreUtils:getCurrentHostName()");
		}
		String hostName;
		if (StringUtils.hasText(System.getProperty(HOST_NAME_PROPERTY))) {
			hostName = System.getProperty(HOST_NAME_PROPERTY);
		}
		else {
			log.warn("HostName not found. Setting default host name to " + DEFAULT_HOST_NAME);
			hostName = DEFAULT_HOST_NAME;
		}
		return hostName;
	}

	/**
	 * Return the locale
	 * 
	 * Valid formats: es or es_ES
	 * 
	 * @param localeStr
	 * @return Locale
	 */
	public static Locale getLocale(final String localeStr) {
		Locale locale = null;
		if (localeStr.indexOf(CoreUtils.LOCALE_SEPARATOR) != -1) {
			// Format ca_ES
			final StringTokenizer stringtokenizer = new StringTokenizer(localeStr, CoreUtils.LOCALE_SEPARATOR);

			locale = new Locale(stringtokenizer.nextToken(), stringtokenizer.nextToken());
		}
		else {
			// Format ca
			locale = new Locale(localeStr);
		}
		return locale;
	}

	/**
	 * Get instance name from the system
	 * 
	 * @return instance name
	 */
	public static String getInstanceName() {
		String instanceName = null;
		if (StringUtils.hasText(System.getProperty(INSTANCE_NAME))) {
			instanceName = System.getProperty(INSTANCE_NAME);
		}
		else {
			log.warn("Instance name not found on server");
		}
		return instanceName;
	}
}
