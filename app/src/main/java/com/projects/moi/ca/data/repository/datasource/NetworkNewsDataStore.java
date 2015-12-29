package com.projects.moi.ca.data.repository.datasource;
/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.projects.moi.ca.data.cache.NewsCache;
import com.projects.moi.ca.data.entity.NewsEntity;
import com.projects.moi.ca.data.net.RestApi;

import java.util.List;

/**
 * {@link NewsDataStore} implementation based on connections to the api (Cloud).
 */
public class NetworkNewsDataStore implements NewsDataStore {

    /**
     * The rest api network rest api
     */
    private final RestApi restApi;

    /**
     * The news cache
     */
    private final NewsCache newsCache;

    /**
     * Construct a {@link NewsDataStore} based on connections to the api (Cloud).
     *
     * @param newsCache A {@link NewsCache} to cache data retrieved from the api.
     */
    public NetworkNewsDataStore(RestApi restApi, NewsCache newsCache) {
        this.restApi = restApi;
        this.newsCache = newsCache;
    }

    /**
     * News entity list
     * @return the new entity list
     */
    @Override
    public List<NewsEntity> newsEntityList() {
        return this.restApi.newsEntityList();
    }

}
