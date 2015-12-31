package com.projects.moi.ca.logging;

import com.orhanobut.logger.Logger;

/**
 * The logger.
 * @author Moisés Vázquez Sánchez
 */
public class Log implements LoggingSupport {

    /**
     * Whether the logger should log.
     */
    private boolean trace = false;

    /**
     * Constructor
     * @param trace Whether the logger should log.
     */
    public Log(boolean trace) {
        this.trace = trace;
    }

    /**
     * Sets trace.
     * @param trace The trace.
     */
    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    /**
     * Logs a message line
     * @param tag the tag
     * @param line the message line
     */
    public void logLine(String tag, String line) {
        if (trace) {
            Logger.d(tag, line);
        }
    }

    /**
     * Logs an error
     * @param tag the tag
     * @param throwable the error
     */
    public void logThrowable(String tag, Throwable throwable) {
        if (trace) {
            Logger.e(throwable, tag, throwable.getMessage());
        }
    }

}
