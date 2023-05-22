package com.alianza.clients.util;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private final org.apache.logging.log4j.Logger logging;

    private <T> Logger(Class<T> aClass) {
    	logging = org.apache.logging.log4j.LogManager.getLogger(aClass);
    }

    private Logger(String nameLog) {
    	logging = org.apache.logging.log4j.LogManager.getLogger(nameLog);
    }

    public static <T> Logger getLogger(Class<T> aClass) {
        return new Logger(aClass);
    }

    public static Logger getLogger(String nombreLog) {
        return new Logger(nombreLog);
    }

    public void debug(String log) {
    	logging.debug(log);
    }

    public void debug(String log, Throwable t) {
    	logging.debug(log, t);
    }

    public void error(String log) {
    	logging.error(log);
    }

    public void error(String log, Throwable t) {
        logging.error(log, t);
    }

    public void error(String log, Object... objects) {   
    	List<String> listLog = new ArrayList<>();
    	for(Object object: objects) {
    		String sanitizedO = object.toString().replace('\n', '_').replace('\r', '_');
    		listLog.add(sanitizedO);
    	}
    	
        logging.error(log, listLog);
    }

    public void info(String log) {
        logging.info(log);
    }

    public void info(String log, Object... objects) {
    	List<String> listLog = new ArrayList<>();       	
    	for(Object object: objects) {
    		String sanitizedO = object.toString().replace('\n', '_').replace('\r', '_');
        	listLog.add(sanitizedO);
    	}
        logging.info(log, listLog);
    }

    public void warn(String log) {
        logging.warn(log);
    }

    public void warn(String log, Throwable t) {
        logging.warn(log, t);
    }

}
