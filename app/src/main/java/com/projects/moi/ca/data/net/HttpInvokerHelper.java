package com.projects.moi.ca.data.net;

import android.content.Context;

import com.projects.moi.ca.BuildConfig;
import com.projects.moi.ca.data.net.configuration.HttpConfigurationBuilder;
import com.projects.moi.ca.data.net.okhttp.LoggingInterceptor;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientConfiguration;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientFactory;
import com.projects.moi.ca.data.net.okhttp.OkHttpClientImpl;
import com.projects.moi.ca.logging.Log;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Interceptor;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

/**
 * This class is a helper for Enax invoker initialization.
 * @author Moisés Vázquez Sánchez
 */
public class HttpInvokerHelper {

    /**
     * The tag
     */
    private String TAG = "HttpInvokerHelper";

    /**
     * The default connection timeout in seconds.
     */
    private static final int DEFAULT_HTTP_CONNECTION_TIMEOUT = 120;

    /**
     * The application context.
     */
    private Context applicationContext;

    /**
     * The logger.
     */
    private final Log logger;

    /**
     * Instantiates a new Scheduler.
     */
    public HttpInvokerHelper(Context applicationContext, Log logger) {
        this.applicationContext = applicationContext;
        this.logger = logger;
    }

    /**
     * Creates the http invoker.
     * @return The http invoker.
     */
    public HttpInvoker createHttpInvoker() {

        com.squareup.okhttp.OkHttpClient okHttpClient = createOkHttpClient();

        OkHttpClientConfiguration httpClientConfiguration = new OkHttpClientConfiguration(okHttpClient);
        httpClientConfiguration.setContext(applicationContext);
        httpClientConfiguration.setLogger(logger);
//        httpClientConfiguration.setTraceOperations(TraceApp.TRACE_OPERATIONS);
//        httpClientConfiguration.setTrustAllCertificates(ConfigApp.TRUST_ALL_CERTIFICATES);
//        httpClientConfiguration.setPinnedCertificates(ConfigApp.PINNED_CERTIFICATES);
//        httpClientConfiguration.setEnableCertificatePinning(!ConfigApp.TRUST_ALL_HOSTS_CERTIFICATES);
        httpClientConfiguration.setTimeout(DEFAULT_HTTP_CONNECTION_TIMEOUT);

        OkHttpClientFactory httpClientFactory = new OkHttpClientFactory(httpClientConfiguration);
        HttpClient httpClient = new OkHttpClientImpl(logger, httpClientFactory);

        HttpConfigurationBuilder invokerConfigurationBuilder = new HttpConfigurationBuilder(applicationContext)
                .setLogger(logger)
                .setHttpClient(httpClient);

        return new HttpInvoker(invokerConfigurationBuilder.build());

    }

    /**
     * Creates the default http client.
     */
    private com.squareup.okhttp.OkHttpClient createOkHttpClient() {

        com.squareup.okhttp.OkHttpClient client = new com.squareup.okhttp.OkHttpClient();

        // cookieHandler
        CookieHandler defaultCookieHandler = CookieHandler.getDefault();
        if (defaultCookieHandler == null) {
            defaultCookieHandler = new CookieManager();
            CookieHandler.setDefault(defaultCookieHandler);
        }
        client.setCookieHandler(defaultCookieHandler);
        client.setConnectionPool(ConnectionPool.getDefault());
//        createClientCache(client, logger);

        if (BuildConfig.DEBUG) {
            Interceptor loggingInterceptor = new LoggingInterceptor("LoggingInterceptor", logger);
            client.interceptors().add(loggingInterceptor);

            /* Flag for logging network requests/responses. It should be false for production. If true, it will add a network interceptor. */
            boolean addNetworkInterceptor = false;
            if (addNetworkInterceptor) {
                Interceptor networkLoggingInterceptor = new LoggingInterceptor("NetworkLoggingInterceptor", logger);
                client.networkInterceptors().add(networkLoggingInterceptor);
            }
        }

        client.setConnectTimeout(DEFAULT_HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        return client;

    }
//
//    /**
//     * Adds cache to the HTTP client.
//     * @param client The http client.
//     */
//    private void createClientCache(OkHttpClient client, log.LoggingSupport logger) {
//
//        if (client != null) {
//
//            try {
//
//                File cacheDir = new File(applicationContext.getCacheDir().getAbsolutePath(), "EnaxHttpCache");
//                int cacheSize = 4 * 1024 * 1024; // 4 MiB
//                Cache cache = new Cache(cacheDir, cacheSize);
//                client.setCache(cache);
//
//            } catch (Exception e) {
//                if (logger != null) {
//                    logger.logThrowable("Could not create http cache", e);
//                }
//            }
//
//        }
//
//    }

//    /**
//     * Provides operation URL
//     * @param operation the operation
//     * @return the URL
//     */
//    public static String getOperationUrl(int operation) {
//
//        String path = getOperationPath(operation);
//        return getOperationUrl(path);
//
//    }

    /**
     * Provides operation URL
     * @param path the path
     * @return the URL
     */
    public static String getOperationUrl(String path) {

        StringBuilder sb = new StringBuilder();

//        boolean hasProtocol = (path != null) && (path.indexOf("://") > 0);
//        if (!hasProtocol) {
//            String server = EnaxConstants.TARGET_SERVERS[EnppConfig.TARGET][EnppConfig.ENVIRONMENT];
//            sb.append(server);
//            String root = EnaxConstants.ROOT_PATHS[EnppConfig.TARGET][EnppConfig.ENVIRONMENT];
//            sb.append(root);
//        }
//
//        if (path != null) {
//            sb.append(path);
//        }

        return sb.toString();

    }

//    /**
//     * Provides operation path
//     * @param operation the operation
//     * @return the path
//     */
//    public static String getOperationPath(int operation) {
//        return HttpConstants.TARGET_OPERATION[HttpConfig.TARGET][EnppConfig.ENVIRONMENT][operation];
//    }

}