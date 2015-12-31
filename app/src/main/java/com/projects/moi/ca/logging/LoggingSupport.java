package com.projects.moi.ca.logging;

/**
 * The logger interface.
 * @author Moisés Vázquez Sánchz
 */
public interface LoggingSupport {

    /**
     * Logs a message line.
     * @param tag The tag.
     * @param line The message line.
     */
    void logLine(String tag, String line);

    /**
     * Logs an error.
     * @param tag The tag.
     * @param throwable The error.
     */
    void logThrowable(String tag, Throwable throwable);

}