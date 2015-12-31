package com.projects.moi.ca.data.net.okhttp;

import com.projects.moi.ca.logging.Log;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * A logging interceptor for debug purposes.
 * @author Moisés Vázquez Sánchez
 */
public class LoggingInterceptor implements Interceptor {

    /**
     * The tag.
     */
    String TAG = LoggingInterceptor.class.getSimpleName();

    /**
     * This interceptor's name.
     */
    private String interceptorName;

    /**
     * The logger.
     */
    private Log logger;

    public LoggingInterceptor(String interceptorName, Log logger) {
        this.interceptorName = interceptorName;
        this.logger = logger;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        if (logger != null) {
            String connection = chain.connection() == null ? "" : "on " + chain.connection();
            logger.logLine(TAG, String.format(interceptorName + "%nSending request %s %s%n%s",
                    request.url(), connection, request.headers()));
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        if (logger != null) {
            String cachedResponse = response.cacheResponse() == null ? "" : " CACHED";
            logger.logLine(TAG, String.format(interceptorName + "%nReceived%s response for %s in %.1fms%n%s", cachedResponse,
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        }

        return response;
    }

}
