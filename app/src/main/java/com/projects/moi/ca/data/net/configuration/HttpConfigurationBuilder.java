package com.projects.moi.ca.data.net.configuration;

import android.content.Context;

import com.projects.moi.ca.data.net.HttpClient;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientConfiguration;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientFactory;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientImpl;
import com.projects.moi.ca.logging.Log;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

/**
 * The type Enax configuration builder.
 * @author Moisés Vázquez Sánchez
 */
@SuppressWarnings("unused")
public class HttpConfigurationBuilder {

    /**
     * The context.
     */
    private Context context = null;

    /**
     * The http client. If null, one will be provided by default.
     */
    private HttpClient httpClient;

    /**
     * The trace.
     */
    private boolean trace = false;

    /**
     * The trace operations flag.
     */
    private boolean traceOperations = false;

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

//    /**
//     * The cookies.
//     */
//    private List<Cookie> cookies = null;

    /**
     * The connection timeout in seconds.
     */
    private int timeout;

    /**
     * The logger.
     */
    Log logger;

    /**
     * Constructor.
     * @param context An Android context.
     */
    public HttpConfigurationBuilder(Context context) {
        this.context = context;
    }

    /**
     * Sets the http client.
     * @param httpClient The http client.
     * @return This builder.
     */
    public HttpConfigurationBuilder setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /**
     * Sets logger.
     * @param logger The logger.
     * @return This builder.
     */
    public HttpConfigurationBuilder setLogger(Log logger) {
        this.logger = logger;
        return this;
    }

    /**
     * Sets trace operations. Not used if HttpClient is set.
     * @param trace The trace operations.
     * @return This builder.
     */
    public HttpConfigurationBuilder setTrace(boolean trace) {
        this.trace = trace;
        return this;
    }

    /**
     * Sets trace operations. Not used if HttpClient is set.
     * @param traceOperations The trace operations.
     * @return This builder.
     */
    public HttpConfigurationBuilder setTraceOperations(boolean traceOperations) {
        this.traceOperations = traceOperations;
        return this;
    }

    /**
     * Sets trust all certificates. Not used if HttpClient is set.
     * @param trustAllCertificates The trust all certificates.
     * @return This builder.
     */
    public HttpConfigurationBuilder setTrustAllCertificates(boolean trustAllCertificates) {
        this.trustAllCertificates = trustAllCertificates;
        return this;
    }

    /**
     * Sets enable certificate pinning. Not used if HttpClient is set.
     * @param enableCertificatePinning The enable certificate pinning.
     * @return This builder.
     */
    public HttpConfigurationBuilder setEnableCertificatePinning(boolean enableCertificatePinning) {
        this.enableCertificatePinning = enableCertificatePinning;
        return this;
    }

    /**
     * Sets pinned certificates. Not used if HttpClient is set.
     * @param pinnedCertificates The pinned certificates.
     * @return This builder.
     */
    public HttpConfigurationBuilder setPinnedCertificates(Map<String, String[]> pinnedCertificates) {
        this.pinnedCertificates = pinnedCertificates;
        return this;
    }

//    /**
//     * Sets cookies. Not used if HttpClient is set.
//     * @param cookies The cookies.
//     * @return This builder.
//     */
//    public HttpConfigurationBuilder setCookies(List<Cookie> cookies) {
//        this.cookies = cookies;
//        return this;
//    }

    /**
     * Sets timeout. Not used if HttpClient is set.
     * @param timeout The timeout.
     * @return This builder.
     */
    public HttpConfigurationBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Create enax configuration.
     * @return The enax configuration.
     */
    public HttpClientConfiguration build() {
        if (context == null) {
//            throw new EnaxInternalException("Context can't be null");
        }
        context = context.getApplicationContext();

        // create a default logger if none is supplied
        if (logger == null) {
            logger = new Log(trace);
        }

        // create a default http client with default configuration
        if (httpClient == null) {
            OkHttpClientConfiguration httpClientConfiguration = new OkHttpClientConfiguration(new OkHttpClient());
            httpClientConfiguration.setContext(context);
            httpClientConfiguration.setLogger(logger);
            httpClientConfiguration.setTraceOperations(traceOperations);
            httpClientConfiguration.setTrustAllCertificates(trustAllCertificates);
            httpClientConfiguration.setPinnedCertificates(pinnedCertificates);
            httpClientConfiguration.setEnableCertificatePinning(enableCertificatePinning);
            httpClientConfiguration.setTimeout(timeout);
            OkHttpClientFactory httpClientFactory = new OkHttpClientFactory(httpClientConfiguration);
            this.httpClient = new OkHttpClientImpl(logger, httpClientFactory);
        }

        return new HttpClientConfiguration(logger, httpClient);
    }

}