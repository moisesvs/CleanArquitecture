package com.projects.moi.ca.data.net.configuration;

import android.content.Context;

import com.projects.moi.ca.data.net.HttpClient;
import com.projects.moi.ca.logging.Log;

import java.util.Map;

/**
 * HttpClientConfiguration http client configuration.
 * @author Moisés Vázquez Sánchez
 */
public class HttpClientConfiguration {

    /**
     * The Android context.
     */
    private Context context;

    /**
     * The http client
     */
    private HttpClient httpClient;

    /**
     * The logger.
     */
    private Log logger;

    /**
     * Whether traceOperations should be traced.
     */
    private boolean traceOperations = false;

    /**
     * The connection timeout in seconds.
     */
    private int timeout;

    /**
     * The trustAllCertificates.
     */
    private boolean trustAllCertificates = true;

    /**
     * The enableCertificatePinning.
     */
    private boolean enableCertificatePinning = false;

    /**
     * The pinnedCertificates.
     */
    private Map<String, String[]> pinnedCertificates;

    /**
     * Instantiates a new Http client configuration.
     */
    public HttpClientConfiguration() {
    }

    /**
     * Instantiates a new Http client configuration.
     * @param logger the logger
     * @param httpClient http client
     */
    public HttpClientConfiguration(Log logger,
                                   HttpClient httpClient) {
        if (httpClient == null) {
            throw new IllegalArgumentException("Default client can't be null!");
        }
        this.logger = logger;
        this.httpClient = httpClient;
    }

    /**
     * Gets context.
     * @return The context.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets context.
     * @param context The context.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets logger.
     * @return The logger.
     */
    public Log getLogger() {
        return logger;
    }

    /**
     * Sets logger.
     * @param logger The logger.
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     * Is trace operations.
     * @return The boolean.
     */
    public boolean isTraceOperations() {
        return traceOperations;
    }

    /**
     * Sets trace operations.
     * @param traceOperations The trace operations.
     */
    public void setTraceOperations(boolean traceOperations) {
        this.traceOperations = traceOperations;
    }

    /**
     * Gets timeout.
     * @return The timeout.
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets timeout.
     * @param timeout The timeout.
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Is trust all certificates.
     * @return The boolean.
     */
    public boolean isTrustAllCertificates() {
        return trustAllCertificates;
    }

    /**
     * Sets trust all certificates.
     * @param trustAllCertificates The trust all certificates.
     */
    public void setTrustAllCertificates(boolean trustAllCertificates) {
        this.trustAllCertificates = trustAllCertificates;
    }

    /**
     * Is enable certificate pinning.
     * @return The boolean.
     */
    public boolean isEnableCertificatePinning() {
        return enableCertificatePinning;
    }

    /**
     * Sets enable certificate pinning.
     * @param enableCertificatePinning The enable certificate pinning.
     */
    public void setEnableCertificatePinning(boolean enableCertificatePinning) {
        this.enableCertificatePinning = enableCertificatePinning;
    }

    /**
     * Gets pinned certificates.
     * @return The pinned certificates.
     */
    public Map<String, String[]> getPinnedCertificates() {
        return pinnedCertificates;
    }

    /**
     * Sets pinned certificates.
     * @param pinnedCertificates The pinned certificates.
     */
    public void setPinnedCertificates(Map<String, String[]> pinnedCertificates) {
        this.pinnedCertificates = pinnedCertificates;
    }

    /**
     * Get http client
     * @return the http client
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

}
