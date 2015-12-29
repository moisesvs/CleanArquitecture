package com.projects.moi.ca.domain.interactor;

/**
 * Copyright (C) 2015 Moisés Vázquez Open Source Project
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
import com.projects.moi.ca.data.entity.NewsEntity;
import com.projects.moi.ca.data.entity.mapper.NewsEntityDataMapper;
import com.projects.moi.ca.data.repository.datasource.NewsDataStore;
import com.projects.moi.ca.data.repository.datasource.NewsDataStoreFactory;
import com.projects.moi.ca.domain.News;
import com.projects.moi.ca.domain.repository.NewsRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link NewsRepository} for retrieving news data.
 */
public class NewsDataRepository implements NewsRepository {

    /**
     * The news data store factory
     */
    private final NewsDataStoreFactory newsDataStoreFactory;

    /**
     * News entity data mapper
     */
    private final NewsEntityDataMapper newsEntityDataMapper;

    /**
     * Constructs a {@link NewsRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param newsEntityDataMapper {@link NewsEntityDataMapper}.
     */
    public NewsDataRepository(NewsDataStoreFactory dataStoreFactory,
                              NewsEntityDataMapper newsEntityDataMapper) {
        this.newsDataStoreFactory = dataStoreFactory;
        this.newsEntityDataMapper = newsEntityDataMapper;
    }

    @Override
    public List<News> news() {
        NewsDataStore storeNews = null;
        ArrayList<News> newsList = null;

        //we always get all users from the cloud
        if (this.newsDataStoreFactory != null) {
            storeNews = this.newsDataStoreFactory.create();
            List<NewsEntity> newsEntityList = storeNews.newsEntityList();
            if (newsEntityList != null) {
                newsList = new ArrayList<>();
                for (NewsEntity newEntity : newsEntityList) {
                    newsList.add(new News(newEntity.getTitle(), newEntity.getDescription()));
                }
            }
        }

        return newsList;

//        return userDataStore.userEntityList()
//                .map(userEntities -> this.userEntityDataMapper.transform(userEntities));
    }

}
