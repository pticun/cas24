package org.arch.core.logging;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.arch.core.util.CoreUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Log4jConfigurer;
import org.springframework.util.StringUtils;

/**
 * CustomDOMConfigurator provides the initialization of log4j configuration
 * 
 * Support multiple configurations based on environment property
 * 
 * 
 */
public class CustomDOMConfigurator extends DOMConfigurator implements InitializingBean {

    private String fileName = null;
    private static final String CLASSPATH_PREFIX = "classpath:";
    private static Logger log = null;

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    /**
     * Return the configuration file name from the current host
     * 
     * @param file
     * @return host file name
     */
    private String getHostConfigurationFile(String file) {
	String hostName = CoreUtils.getCurrentHostName();
	return StringUtils.stripFilenameExtension(file) + "." + hostName + "." + StringUtils.getFilenameExtension(file);
    }

    /**
     * Initialize log4j based on classpath
     */
    private void initializeByClassPath() {
	String cleanClassPathFile = this.fileName.substring(this.fileName.indexOf(CLASSPATH_PREFIX) + CLASSPATH_PREFIX.length());
	String hostNameConfigFile = CLASSPATH_PREFIX + getHostConfigurationFile(cleanClassPathFile);

	try {
	    log.info("Log4j resource is " + hostNameConfigFile);
	    Log4jConfigurer.initLogging(hostNameConfigFile);
	    log.info("Log4j resource " + hostNameConfigFile + " resource");
	} catch (FileNotFoundException e) {
	    System.out.println("Log4j resource not found. " + hostNameConfigFile + ". Error: " + e);

	    try {
		Log4jConfigurer.initLogging(this.fileName);
	    } catch (FileNotFoundException e1) {
		log.error("Host-dependent configuration file (" + hostNameConfigFile + " not found. Checking default configuration: " + this.fileName);
	    }

	}

    }

    /**
     * Initialize log4j based on path
     */
    private void initializeByPath() {

	String hostNameConfigFile = getHostConfigurationFile(this.fileName);
	boolean existHostConfigFile = (new File(hostNameConfigFile)).exists();

	if (existHostConfigFile) {
	    log.info("Log4j resource is " + fileName);
	    try {
		Log4jConfigurer.initLogging(hostNameConfigFile);
	    } catch (FileNotFoundException e) {
		log.error("Log4j resource not found. " + fileName + ". Error: " + e);
	    }
	} else {
	    log.info("Host-dependent configuration file (" + hostNameConfigFile + " not found. Checking default configuration: " + fileName);

	    boolean existDefaultConfig = (new File(fileName)).exists();
	    if (existDefaultConfig) {
		log.info("Log4j resource is " + fileName);
		try {
		    Log4jConfigurer.initLogging(hostNameConfigFile);
		} catch (FileNotFoundException e) {
		    log.error("Log4j resource not found. " + fileName + ". Error: " + e);
		}
	    } else {
		log.warn("WARN: " + fileName + " not found");
	    }
	}
    }

    /**
     * Initialize log4j
     */
    public void initialize() {
	log = Logger.getLogger(CustomDOMConfigurator.class);
	if (StringUtils.hasText(fileName)) {
	    if (fileName.startsWith(CLASSPATH_PREFIX)) {
		initializeByClassPath();
	    } else {
		initializeByPath();
	    }
	} else {
	    log.warn("WARN: log4.xml configuration file not informed");
	}
    }

    public void afterPropertiesSet() throws Exception {
    }
}
