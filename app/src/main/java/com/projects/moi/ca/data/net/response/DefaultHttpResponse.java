package com.projects.moi.ca.data.net.response;

import com.projects.moi.ca.data.net.HttpRequest;

import java.util.Map;
import java.util.TreeMap;

/**
 * The base class for an http response. <b>To use a default http response use {@link DefaultEnaxResponse}.</b>
 * @author Moisés Vázquez Sánchez
 */
public class DefaultHttpResponse implements HttpResponse {

    /**
     * The original request.
     */
    protected HttpRequest request;

    /**
     * The statusCode.
     */
    protected int statusCode;

    /**
     * The headers.
     */
    protected Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * The body.
     */
    protected String body;

    /**
     * The startTime.
     */
    protected long startTime;

    /**
     * The endTime.
     */
    protected long endTime;

    /**
     * The duration.
     */
    protected long duration;

    /**
     * Whether the request has been successful.
     */
    protected boolean successful = true;

    /**
     * Instantiates a new Enax response.
     * @param request The original request.
     */
    public DefaultHttpResponse(HttpRequest request) {
        this.request = request;
    }

    /**
     * Gets request.
     * @return The request.
     */
    @Override
    public HttpRequest getRequest() {
        return request;
    }

    /**
     * Gets status code.
     * @return The status code.
     */
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     * @param statusCode The status code.
     */
    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets headers.
     * @return The headers.
     */
    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets headers.
     * @param headers The headers.
     */
    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Gets body.
     * @return The body.
     */
    @Override
    public String getBody() {
        return body;
    }

    /**
     * Sets body.
     * @param body The body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets start time.
     * @return The start time.
     */
    @Override
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     * @param startTime The start time.
     */
    @Override
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     * @return The end time.
     */
    @Override
    public long getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     * @param endTime The end time.
     */
    @Override
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets duration.
     * @return The duration.
     */
    @Override
    public long getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     * @param duration The duration.
     */
    @Override
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Is successful.
     * @return The boolean.
     */
    @Override
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets successful.
     * @param successful The successful flag.
     */
    @Override
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}