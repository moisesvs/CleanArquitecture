package com.projects.moi.ca.data.repository.datasource;

import android.content.Context;

import com.projects.moi.ca.data.cache.NewsCache;
import com.projects.moi.ca.data.entity.mapper.NewsEntityJsonMapper;

/**
 * NewsDataStoreFactory
 *
 * @author (c) 2014, New Means of Payment, BBVA
 */
public class NewsDataStoreFactory {

    /**
     * The context app
     */
    private final Context context;

    /**
     * The news cache
     */
    private final NewsCache newsCache;

    /**
     * News data store factory
     * @param context the context app
     * @param newsCache the news cache
     */
    public NewsDataStoreFactory(Context context, NewsCache newsCache) {
        if (context == null || newsCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.newsCache = newsCache;
    }

    /**
     * Create {@link NewsDataStore} from a user id.
     */
    public NewsDataStore create(int userId) {
        NewsDataStore userDataStore;

        if (!this.newsCache.isExpired() && this.newsCache.isCached(userId)) {
            userDataStore = new DiskNewsDataStore(this.newsCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Create {@link NewsDataStore} to retrieve data from the Cloud.
     */
    public NewsDataStore createCloudDataStore() {
        NewsEntityJsonMapper userEntityJsonMapper = new NewsEntityJsonMapper();
//        RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);

        return new NetworkNewsDataStore(this.newsCache);
    }
}
