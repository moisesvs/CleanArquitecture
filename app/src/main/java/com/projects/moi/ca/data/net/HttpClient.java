package com.projects.moi.ca.data.net;

import com.projects.moi.ca.data.net.response.HttpResponse;

/**
 * HttpClient interface to provide access to HTTP operations
 * @author Moisés Vázquez Sánchez
 */
public interface HttpClient {

    /**
     * The tag.
     */
    String TAG = HttpClient.class.getSimpleName();

    /**
     * Invokes a GET operation.
     * @param request The The request.
     * @return The The response.
     */
   HttpResponse get(HttpRequest request);

    /**
     * Invokes a POST operation.
     * @param request The enax request.
     * @return The enax response.
     */
    HttpResponse post(HttpRequest request);

    /**
     * Invokes a PUT operation.
     * @param request The enax request.
     * @return The enax response.
     */
    HttpResponse put(HttpRequest request);

    /**
     * Invokes a DELETE operation.
     * @param request The enax request.
     * @return The enax response.
     */
    HttpResponse delete(HttpRequest request);

    /**
     * Clears the cookies of the client.
     */
    void clearCookies();

//    /**
//     * Gets the cookies.
//     * @return The cookies.
//     */
//    List<Cookie> getCookies();
}