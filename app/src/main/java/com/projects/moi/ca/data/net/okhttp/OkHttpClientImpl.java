package com.projects.moi.ca.data.net.okhttp;

import com.projects.moi.ca.data.net.HttpClient;
import com.projects.moi.ca.data.net.HttpRequest;
import com.projects.moi.ca.data.net.response.DefaultHttpResponse;
import com.projects.moi.ca.data.net.response.HttpResponse;
import com.projects.moi.ca.logging.Log;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class provides an HTTP client.
 * @author Moisés Vázquez Sánchez
 */
public class OkHttpClientImpl implements HttpClient {

    /**
     * The http client factory.
     */
    private OkHttpClientFactory clientFactory;

    /**
     * The logger.
     */
    private Log logger;

    /**
     * Constructor
     * @param logger The logger.
     * @param clientFactory The http client factory.
     */
    public OkHttpClientImpl(Log logger, OkHttpClientFactory clientFactory) {
        this.logger = logger;
        this.clientFactory = clientFactory;
    }

    @Override
    public HttpResponse get(HttpRequest enaxRequest) {

        try {
            Builder requestBuilder = createOkRequestBuilder(enaxRequest);
            Request request = requestBuilder.get().build();
            Response response = getHttpClient(enaxRequest.getUrl()).newCall(request).execute();
            return createHttpResponse(enaxRequest, response);
        } catch (IOException e) {
            if (logger != null) {
                logger.logThrowable(TAG, e);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpResponse post(HttpRequest enaxRequest) {

        try {
            Builder requestBuilder = createOkRequestBuilder(enaxRequest);
            Request request = requestBuilder.post(createRequestBody(enaxRequest)).build();
            Response response = getHttpClient(enaxRequest.getUrl()).newCall(request).execute();
            return createHttpResponse(enaxRequest, response);
        } catch (IOException e) {
            if (logger != null) {
                logger.logThrowable(TAG, e);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpResponse put(HttpRequest enaxRequest) {

        try {
            Builder requestBuilder = createOkRequestBuilder(enaxRequest);
            Request request = requestBuilder.put(createRequestBody(enaxRequest)).build();
            Response response = getHttpClient(enaxRequest.getUrl()).newCall(request).execute();
            return createHttpResponse(enaxRequest, response);
        } catch (IOException e) {
            if (logger != null) {
                logger.logThrowable(TAG, e);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpResponse delete(HttpRequest enaxRequest) {

        try {
            Builder requestBuilder = createOkRequestBuilder(enaxRequest);
            Request request = requestBuilder.delete(createRequestBody(enaxRequest)).build();
            Response response = getHttpClient(enaxRequest.getUrl()).newCall(request).execute();
            return createHttpResponse(enaxRequest, response);
        } catch (IOException e) {
            if (logger != null) {
                logger.logThrowable(TAG, e);
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the request body from the Enax request.
     * @param enaxRequest The Enax request.
     * @return The request body.
     */
    private RequestBody createRequestBody(HttpRequest enaxRequest) {
//        EnaxRequestBody enaxRequestBody = enaxRequest.getBody();
//        if (enaxRequestBody != null) {
//            MediaType mediaType = null;
//            if (enaxRequestBody.getContentType() != null) {
//                mediaType = MediaType.parse(enaxRequestBody.getContentType());
//            }
//            byte[] bodyContent;
//            if (enaxRequestBody.getContent() == null) {
//                bodyContent = new byte[0]; // empty body. Null will fail since okHttp 2.4.1-RC1
//            } else {
//                bodyContent = enaxRequestBody.getContent();
//            }
//            return RequestBody.create(mediaType, bodyContent);
//        }
        return null;
    }

    /**
     * Creates an OkHttp request from an OkHttp response.
     * @param enaxRequest TheEnax request.
     * @return The okHttp response.
     */
    private Builder createOkRequestBuilder(HttpRequest enaxRequest) {

        Builder requestBuilder = new Request.Builder().url(enaxRequest.getUrl());
        Headers.Builder headersBuilder = new Headers.Builder();

        Set<Entry<String, String>> headers = enaxRequest.getHeaders().entrySet();
        for (Entry<String, String> entry : headers) {
            headersBuilder.add(entry.getKey(), entry.getValue());
        }

//        Set<Entry<String, String>> securityHeaders = enaxRequest.getSecurityHeaders().entrySet();
//        for (Entry<String, String> entry : securityHeaders) {
//            headersBuilder.add(entry.getKey(), entry.getValue());
//        }

        requestBuilder.headers(headersBuilder.build());
        return requestBuilder;
    }

    /**
     * Creates an Enax response from an OkHttp response.
     * @param request The Enax request.
     * @param response The OkHttp response.
     * @return The Enax response.
     */
    private HttpResponse createHttpResponse(HttpRequest request, Response response) {

        HttpResponse enaxResponse = new DefaultHttpResponse(request);

        // Set the headers.
        Map<String, String> enaxResponseHeaders = enaxResponse.getHeaders();
        Headers headers = response.headers();
        for (String name : headers.names()) {
            enaxResponseHeaders.put(name, headers.get(name));
        }

        // Set the body.
        try {
            ResponseBody body = response.body();
            if (body != null) {
//                HttpResponseBody enaxResponseBody = new EnaxOkHttpResponseBody(body.bytes(), body.contentType());
//                enaxResponseBody.setLogger(logger);
                enaxResponse.setBody(body.string());
            }
        } catch (IOException ioe) {
            if (logger != null) {
                logger.logThrowable(TAG, ioe);
            }
//            throw new EnaxInternalException(ioe);
        }

        // Set the HTTP status code.
        enaxResponse.setStatusCode(response.code());

        // Checks if there has been an error.
        if (!response.isSuccessful()) {
            enaxResponse.setSuccessful(false);
        }

        return enaxResponse;
    }

    @Override
    public void clearCookies() {

        CookieManager targetCookieManager = null;
        CookieHandler defaultCookieHandler = CookieHandler.getDefault();
        if (defaultCookieHandler instanceof CookieManager) {
            targetCookieManager = (CookieManager) defaultCookieHandler;
        }

        if (targetCookieManager != null) {
            CookieStore cookieStore = targetCookieManager.getCookieStore();
            if (cookieStore != null) {
                cookieStore.removeAll();
            }
        }
    }

//    @Override
//    public List<Cookie> getCookies() {
//
//        List<Cookie> cookies = new ArrayList<>();
//        CookieManager targetCookieManager = null;
//        CookieHandler defaultCookieHandler = CookieHandler.getDefault();
//        if (defaultCookieHandler instanceof CookieManager) {
//            targetCookieManager = (CookieManager) defaultCookieHandler;
//        }
//
//        if (targetCookieManager != null) {
//            CookieStore cookieStore = targetCookieManager.getCookieStore();
//            if (cookieStore != null) {
//                List<HttpCookie> list = cookieStore.getCookies();
//                for (HttpCookie cookie : list) {
//                    Cookie newCookie = new Cookie(cookie.getSecure(), cookie.getDomain(), cookie.getPath(), cookie.getName(), cookie.getValue());
//                    cookies.add(newCookie);
//                }
//            }
//        }
//
//        return cookies;
//    }

    /**
     * Gets the http client for this url.
     * @param url the url.
     * @return The http client for this {@code url}.
     * @throws MalformedURLException exception.
     */
    public com.squareup.okhttp.OkHttpClient getHttpClient(String url) throws MalformedURLException {
        return clientFactory.get(url);
    }
}