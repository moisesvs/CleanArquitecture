package com.projects.moi.ca.data.net;

import java.util.HashMap;
import java.util.Map;

/**
 * The Http request class.
 * @author Moisés Vázquez Sánchez
 */
public class HttpRequest {

    /**
     * The Http Method.
     */
    public enum Method {

        /**
         * The GET.
         */
        GET,

        /**
         * The POST.
         */
        POST,

        /**
         * The PUT.
         */
        PUT,

        /**
         * The DELETE.
         */
        DELETE
    }

    /**
     * The URL.
     */
    private final String url;

    /**
     * The http method. GET is the default.
     */
    private Method method = Method.GET;

    /**
     * The optional text. It includes the contentType
     */
    private String body;

    /**
     * The headers.
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * Instantiates a new Enax request.
     * @param url The URL.
     */
    public HttpRequest(String url) {
        this.url = url;
    }

    /**
     * Gets URL.
     * @return The URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets method.
     * @return The method.
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Sets method.
     * @param method The method.
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Gets body.
     * @return The body.
     */
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
     * Gets headers.
     * @return The headers.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets headers.
     * @param headers The headers.
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

}