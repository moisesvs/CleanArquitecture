package com.projects.moi.ca.data.net.okhttp;

import com.projects.moi.ca.logging.Log;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * An http client factory. It has a default http client and also an instance of an http client per host where
 * sslCertificatePinning will be used.
 * @author Moisés Vázquez Sánchez
 */
public class OkHttpClientFactory {

    /**
     * The tag.
     */
    String TAG = OkHttpClientFactory.class.getSimpleName();

    /**
     * Configuration.
     */
    private OkHttpClientConfiguration configuration;

    /**
     * The logger.
     */
    private Log logger;

    /**
     * The cached http clients map for hosts which use certificate pinning.
     */
    private Map<String, OkHttpClient> pinnedClients = new HashMap<>();

    /**
     * The http default client.
     */
    private OkHttpClient defaultClient;

    /**
     * Constructor.
     * @param configuration The configuration.
     */
    public OkHttpClientFactory(OkHttpClientConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Configuration can't be null!");
        }
        if (configuration.getContext() == null) {
            throw new IllegalArgumentException("Configuration must have an android context!");
        }
        this.configuration = configuration;
        this.logger = configuration.getLogger();
        this.defaultClient = configuration.getDefaultClient();
    }

    /**
     * Gets an http client for this url. It uses the host to know which OkHttp client it should return. If certificate
     * pinning is to be used with this host, it will use the pinnedClients cache to look for an appropriate client.
     * Otherwise, the default http client will be returned.
     * @param url The url.
     * @return An OkHttpClient.
     * @throws MalformedURLException exception if {@code url} could not be parsed as a URL.
     */
    public synchronized OkHttpClient get(String url) throws MalformedURLException {

        if (configuration.getPinnedCertificates() != null) {

            String host = new URL(url).getHost();
            String[] certs = configuration.getPinnedCertificates().get(host);
            if (certs != null) {

                // certificate pinning is used with this host
                OkHttpClient client = pinnedClients.get(host);
                if (client != null) {
                    // return existing client
                    return client;
                } else {
                    // create new http client for this host
                    client = defaultClient.clone();
//                    client = configureClient(client, host);
                    pinnedClients.put(host, client);
                    return client;
                }

            }

        }

        return defaultClient;

    }

//    /**
//     * Different clients require different configuration on the sslSocketFactories
//     * @param client http client to be configured
//     * @param host host for which the client will be configured
//     * @return The http client
//     */
//    private OkHttpClient configureClient(OkHttpClient client, String host) {
//
//        try {
//
//            CustomSSLSocketFactory sslSocketFactoryHelper;
//            if (configuration.isTrustAllCertificates()) {
//
//                sslSocketFactoryHelper = new PermissiveSocketFactory();
//                HostnameVerifier hostnameVerifier = PermissiveSocketFactory.createNullHostnameVerifier();
//                client.setHostnameVerifier(hostnameVerifier);
//                client.setSslSocketFactory(sslSocketFactoryHelper.getSocketFactory());
//
//            } else if (configuration.isEnableCertificatePinning()) {
//
//                Map<String, String[]> certMap = configuration.getPinnedCertificates();
//                String[] certificates = (certMap != null) ? certMap.get(host) : null;
//                if (certificates != null) {
//                    sslSocketFactoryHelper = new CertificatePinningSocketFactory(host, certificates, logger, configuration.isTraceOperations());
//                    client.setSslSocketFactory(sslSocketFactoryHelper.getSocketFactory());
//                }
//
//            }
//
//        } catch (Throwable t) {
//            if (logger != null) {
//                logger.logThrowable(TAG, t);
//            }
//        }
//
//        return client;
//    }

}