package com.projects.moi.ca.data.repository.datasource;

import com.projects.moi.ca.data.entity.NewsEntity;

import java.util.List;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface NewsDataStore {
    /**
     * Get an which will emit a List of {@link NewsEntity}.
     */
    List<NewsEntity> newsEntityList();
}