package com.projects.moi.ca.data.net.okhttp;

import com.projects.moi.ca.data.net.configuration.HttpClientConfiguration;
import com.squareup.okhttp.OkHttpClient;

/**
 * OkHttpClientConfiguration configuration for OkHttp client.
 * @author Moisés Vázquez Sánchez
 */
public class OkHttpClientConfiguration extends HttpClientConfiguration {

    /**
     * Default http client.
     */
    private OkHttpClient defaultClient;

    /**
     * Instantiates a new Http client configuration.
     * @param defaultClient The default client.
     */
    public OkHttpClientConfiguration(OkHttpClient defaultClient) {
        if (defaultClient == null) {
            throw new IllegalArgumentException("Default client can't be null!");
        }
        this.defaultClient = defaultClient;
    }

    /**
     * Gets default client.
     * @return The default client.
     */
    public OkHttpClient getDefaultClient() {
        return defaultClient;
    }

}