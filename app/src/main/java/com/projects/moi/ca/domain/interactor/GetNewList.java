package com.projects.moi.ca.domain.interactor;

import com.projects.moi.ca.domain.News;
import com.projects.moi.ca.domain.executor.ThreadExecutor;

import java.util.List;

/**
 * GetNewList interface
 *
 * @author Moisés Vázquez Sánchez
 */
public interface GetNewList extends ThreadExecutor {

    interface Callback {
        void onNewsShowLoaded(final List<News> newsList);

        void onNewsEmpty();

        void onConnectionError();
    }
}
