package com.projects.moi.ca.data.repository.datasource;
/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.projects.moi.ca.data.cache.NewsCache;
import com.projects.moi.ca.data.entity.NewsEntity;

import java.util.List;

/**
 * {@link NewsDataStore} implementation based on file system data store.
 */
public class DiskNewsDataStore implements NewsDataStore {

    private final NewsCache newsCache;

    /**
     * Construct a {@link NewsDataStore} based file system data store.
     *
     * @param newsCache A {@link NewsCache} to cache data retrieved from the api.
     */
    public DiskNewsDataStore(NewsCache newsCache) {
        this.newsCache = newsCache;
    }

    /**
     * User entity list
     * @return the news entity
     */
    @Override
    public List<NewsEntity> userEntityList() {
//        //TODO: implement simple cache for storing/retrieving collections of users.
//        throw new UnsupportedOperationException("Operation is not available!!!");

        return null;
    }
}
