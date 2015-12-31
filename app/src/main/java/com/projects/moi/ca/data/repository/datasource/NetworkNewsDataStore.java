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
import com.projects.moi.ca.data.net.HttpInvoker;
import com.projects.moi.ca.data.net.HttpRequest;
import com.projects.moi.ca.data.net.response.HttpResponse;
import com.projects.moi.ca.presentation.CaApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link NewsDataStore} implementation based on connections to the api (Cloud).
 */
public class NetworkNewsDataStore implements NewsDataStore {

    /**
     * The http client
     */
    private HttpInvoker invoker;

    /**
     * The news cache
     */
    private final NewsCache newsCache;

    /**
     * Construct a {@link NewsDataStore} based on connections to the api (Cloud).
     *
     * @param newsCache A {@link NewsCache} to cache data retrieved from the api.
     */
    public NetworkNewsDataStore(HttpInvoker invoker, NewsCache newsCache) {
        this.invoker = invoker;
        this.newsCache = newsCache;
    }

    /**
     * News entity list
     * @return the new entity list
     */
    @Override
    public List<NewsEntity> newsEntityList() {
        String API_BASE_URL = "https://raw.githubusercontent.com/BBC-News/app-search-data/master/";
        String API_URL_GET_NEWS_LIST = API_BASE_URL + "search_tags_v3.1.json";

        HttpRequest request = new HttpRequest(API_URL_GET_NEWS_LIST);
        HttpResponse response = invoker.invokeOperation(request);
        String body = response.getBody();
        CaApplication.getInstance().getLogger().logLine("NetworkNewsDataStore", body);

        List<NewsEntity> listEntity = new ArrayList<>();
        listEntity.add(new NewsEntity("Test Network 1", "Description 1"));
        listEntity.add(new NewsEntity("Test Network 2", "Description 2"));
        listEntity.add(new NewsEntity("Test Network 3", "Description 3"));

        return listEntity;
    }

}
