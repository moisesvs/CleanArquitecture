package com.projects.moi.ca.data.net;

import com.projects.moi.ca.data.net.configuration.HttpClientConfiguration;
import com.projects.moi.ca.data.net.response.DefaultHttpResponse;
import com.projects.moi.ca.data.net.response.HttpResponse;
import com.projects.moi.ca.logging.Log;

/**
 * The enax invoker.
 *
 * @author Moisés Vázquez Sánchez
 */
public class HttpInvoker {

    /**
     * The class tag for the logger.
     */
    private static final String TAG = HttpInvoker.class.getSimpleName();

    /**
     * The logger.
     */
    private Log logger;

    /**
     * The HTTP client.
     */
    private HttpClient httpClient;

    /**
     * Default constructor. Private, since it needs also setup to be called.
     *
     * @param httpConfiguration the initial configuration.
     */
    public HttpInvoker(HttpClientConfiguration httpConfiguration) {
        if (httpConfiguration == null) {
            throw new IllegalArgumentException("Configuration can't be null!");
        }
        if (httpConfiguration.getHttpClient() == null) {
            throw new IllegalArgumentException("HttpClient can't be null!");
        }
        logger = httpConfiguration.getLogger();
        httpClient = httpConfiguration.getHttpClient();
    }

    /**
     * Invokes an operation. It's important that this method is called on a background thread. The listener will be
     * called on a background thread.
     *
     * @param request  The request.
     */
    public HttpResponse invokeOperation(final HttpRequest request) {
        HttpResponse result = null;
        try {
            HttpResponse response = null;
            try {
                if ((httpClient != null) && (request != null)) {
                    long startTime = System.currentTimeMillis();
                    switch (request.getMethod()) {
                        case GET:
                            response = httpClient.get(request);
                            break;
                        case POST:
                            response = httpClient.post(request);
                            break;
                        case PUT:
                            response = httpClient.put(request);
                            break;
                        case DELETE:
                            response = httpClient.delete(request);
                            break;
                    }
                    postProcessResponse(response, startTime);
                }
            } catch (Exception e) {
                response = createDefaultHttpResponse(request, e);
            } finally {
                result = response;
            }
        } catch (Throwable t) {
            if (logger != null) {
                logger.logLine(TAG, "Enax invoker error");
                logger.logThrowable(TAG, t);
            }
        }

        return result;
    }

    /**
     * Applies a post processing to the raw response.
     *
     * @param response  The raw response.
     * @param startTime The time when the operation started.
     */
    private void postProcessResponse(HttpResponse response, long startTime) {

//        response.setError(parseErrorFromResponse(response));

        long endTime = System.currentTimeMillis();
        response.setStartTime(startTime);
        response.setEndTime(endTime);
        response.setDuration(endTime - startTime);
    }

//    /**
//     * Parse the enax error.
//     *
//     * @param response The Enax response.
//     * @return The enax error.
//     */
//    private HttpResponseError parseErrorFromResponse(HttpResponse response) {
//
//        EnaxResponseError result = null;
//        // Check if it has been an error.
//        if ((response != null) && !response.isSuccessful()) {
//
//            EnaxResponseBody body = response.getBody();
//            String content = (body != null) ? body.getContentAsString() : null;
//            if (content != null) {
//
//                try {
//                    JsonWrapper jsonWrapper = JsonWrapper.create(content);
//                    if (!jsonWrapper.isArray()) {
//
//                        JSONObject responseObject = jsonWrapper.getObject();
//                        String version = responseObject.optString("version");
//                        String severity = responseObject.optString("severity");
//                        String httpStatus = responseObject.optString("http-status");
//                        String errorCode = responseObject.optString("error-code");
//                        String errorMessage = responseObject.optString("error-message");
//                        String consumerRequestId = responseObject.optString("consumer-request-id");
//                        String systemErrorCode = responseObject.optString("system-error-code");
//                        String systemErrorDescription = responseObject.optString("system-error-description");
//
//                        if (!TextUtils.isEmpty(errorCode) || !TextUtils.isEmpty(errorMessage)) {
//                            EnaxServerError enaxError = new EnaxServerError(version, severity, httpStatus, errorCode, errorMessage, consumerRequestId, systemErrorCode, systemErrorDescription);
//                            result = new EnaxResponseError(enaxError);
//                        }
//                    }
//                } catch (JSONException e) {
//                    if (logger != null) {
//                        logger.logThrowable(TAG, e);
//                    }
//                }
//            }
//        }
//
//        return result;
//    }

    /**
     * Creates a default enax error response.
     *
     * @param request   The original request.
     * @param exception The caught exception.
     * @return A generic enax response created from the request and the caught exception.
     */
    protected HttpResponse createDefaultHttpResponse(HttpRequest request, Exception exception) {

        HttpResponse response = new DefaultHttpResponse(request);
        response.setSuccessful(false);
//        HttpResponseError responseError;
//        if (exception instanceof HttpConnectionException) {
//            responseError = new HttpResponseError(Code.CONNECTION, exception.getMessage());
//        } else if (exception instanceof HttpInternalException) {
//            responseError = new HttpResponseError(Code.INTERNAL, exception.getMessage());
//        } else {
//            // TODO: Check this code:
//            responseError = new HttpResponseError(Code.INTERNAL, exception.getMessage());
//        }
//        response.setError(responseError);
        return response;
    }
}