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

import com.projects.moi.ca.domain.executor.PostExecutionThread;
import com.projects.moi.ca.domain.executor.ThreadExecutor;
import com.projects.moi.ca.domain.repository.NewsRepository;

/**
 * Get news list
 */
public class GetNewsList extends UseCase {

    /**
     * The new repository
     */
    private final NewsRepository newRepository;

    /**
     * Default constructor get new list
     * @param newRepository the new repository
     * @param threadExecutor the thread executor
     * @param postExecutionThread the post execution thread
     */
    public GetNewsList(NewsRepository newRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newRepository = newRepository;
    }

}
