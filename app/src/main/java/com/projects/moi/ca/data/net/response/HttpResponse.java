package com.projects.moi.ca.data.net.response;

import com.projects.moi.ca.data.net.HttpRequest;

import java.util.Map;

/**
 * HttpResponse The http response interface.
 * @author Moisés Vázquez Sánchez
 */
public interface HttpResponse {

    /**
     * Gets request.
     * @return The request.
     */
    HttpRequest getRequest();

    /**
     * Gets status code.
     * @return The status code.
     */
    int getStatusCode();

    /**
     * Sets status code.
     * @param statusCode The status code.
     */
    void setStatusCode(int statusCode);

    /**
     * Gets headers.
     * @return The headers.
     */
    Map<String, String> getHeaders();

    /**
     * Sets headers.
     * @param headers The headers.
     */
    void setHeaders(Map<String, String> headers);

    /**
     * Gets body.
     * @return The body.
     */
    String getBody();

    /**
     * Sets body.
     * @param body The body.
     */
    void setBody(String body);

    /**
     * Gets start time.
     * @return The start time.
     */
    long getStartTime();

    /**
     * Sets start time.
     * @param startTime The start time.
     */
    void setStartTime(long startTime);

    /**
     * Gets end time.
     * @return The end time.
     */
    long getEndTime();

    /**
     * Sets end time.
     * @param endTime The end time.
     */
    void setEndTime(long endTime);

    /**
     * Gets duration.
     * @return The duration.
     */
    long getDuration();

    /**
     * Sets duration.
     * @param duration The duration.
     */
    void setDuration(long duration);

    /**
     * Is successful.
     * @return The boolean.
     */
    boolean isSuccessful();

    /**
     * Sets successful.
     * @param successful The successful.
     */
    void setSuccessful(boolean successful);
}