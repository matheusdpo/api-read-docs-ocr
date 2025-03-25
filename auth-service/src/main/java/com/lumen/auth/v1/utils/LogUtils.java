package com.lumen.auth.v1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Class utils for log messages in the application with the logger
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Component
public class LogUtils {

    /**
     * Logger instance
     */
    private Logger logger = LoggerFactory.getLogger("API-OCR");


    /**
     * Method to log info - message to logmessages
     * @param message - message to log
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Method to log error messages
     * @param message - message to log
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * Method to log error messages with exception
     * @param message - message to log
     * @param e       - exception to log
     */
    public void error(String message, Throwable e) {
        logger.error(message, e);
    }

    /**
     * Method to log debug messages
     * @param message - message to log
     */
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Method to log warn messages
     * @param message - message to log
     */
    public void warn(String message) {
        logger.warn(message);
    }

}
