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

import android.support.annotation.NonNull;

import com.projects.moi.ca.domain.News;
import com.projects.moi.ca.domain.executor.PostExecutionThread;
import com.projects.moi.ca.domain.executor.ThreadExecutor;
import com.projects.moi.ca.domain.repository.NewsRepository;

import java.util.List;

/**
 * Get news list
 */
public class GetNewsListUseCase extends UseCase {

    /**
     * The new repository
     */
    private final NewsRepository newRepository;

    /**
     * The callback
     */
    private final GetNewList.Callback callback;

    /**
     * Default constructor get new list
     * @param newRepository the new repository
     * @param threadExecutor the thread executor
     * @param postExecutionThread the post execution thread
     */
    public GetNewsListUseCase(@NonNull NewsRepository newRepository,
                              ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread,
                              GetNewList.Callback callback) {
        super(threadExecutor, postExecutionThread);
        this.newRepository = newRepository;
        this.callback = callback;
    }

    /**
     * The run execute
     */
    @Override
    public void run() {
        super.run();
        final List<News> list = this.newRepository.news();

        getPostExecutionThread().post(new Runnable() {
            @Override
            public void run() {
                callback.onNewsShowLoaded(list);
            }
        });
    }
}
