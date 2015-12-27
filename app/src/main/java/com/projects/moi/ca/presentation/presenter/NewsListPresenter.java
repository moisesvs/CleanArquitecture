package com.projects.moi.ca.presentation.presenter;

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
import com.projects.moi.ca.domain.interactor.GetNewList;
import com.projects.moi.ca.domain.interactor.GetNewsListUseCase;
import com.projects.moi.ca.domain.repository.NewsRepository;
import com.projects.moi.ca.presentation.view.activity.view.NewsView;

import java.util.List;

public class NewsListPresenter implements Presenter, GetNewList.Callback {

    /**
     * The news view
     */
    private NewsView view;

    /**
     * Get news list
     */
    private GetNewsListUseCase newsList;

    /**
     * New list presenter
     */
    public NewsListPresenter(@NonNull NewsRepository repository,
                             ThreadExecutor executor,
                             PostExecutionThread postExecutionThread) {
        newsList = new GetNewsListUseCase(repository, executor, postExecutionThread, this);
    }

    /**
     * Set the view
     * @param view the view to set
     */
    public void setView(@NonNull NewsView view) {
        this.view = view;
    }

    /**
     * On resume presenter
     */
    @Override
    public void resume() {
        // nothing
    }

    /**
     * On pause presenter
     */
    @Override
    public void pause() {
        // nothing
    }

    /**
     * On destroy presenter
     */
    @Override
    public void destroy() {
        // nothing
    }

    /**
     * Loads all users.
     */
    public void loadNewsList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    private void showViewLoading() {
        this.view.showLoading();
    }

    private void hideViewLoading() {
        this.view.hideLoading();
    }

    private void showViewRetry() {
        this.view.showRetry();
    }

    private void hideViewRetry() {
        this.view.hideRetry();
    }

    private void showErrorMessage() {
        this.view.showError("Error ");
    }

//    private void showUsersCollectionInView(Collection<User> usersCollection) {
//        final Collection<UserModel> userModelsCollection =
//                this.userModelDataMapper.transform(usersCollection);
//        this.viewListView.renderUserList(userModelsCollection);
//    }

    /**
     * Get user list
     */
    private void getUserList() {
        newsList.execute();
    }

    @Override
    public void onNewsShowLoaded(List<News> newsList) {
        this.view.notificationNewsList(newsList);
    }

    @Override
    public void onNewsEmpty() {

    }

    @Override
    public void onConnectionError() {

    }

//    private final class UserListSubscriber extends DefaultSubscriber<List<User>> {
//
//        @Override public void onCompleted() {
//            UserListPresenter.this.hideViewLoading();
//        }
//
//        @Override public void onError(Throwable e) {
//            UserListPresenter.this.hideViewLoading();
//            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//            UserListPresenter.this.showViewRetry();
//        }
//
//        @Override public void onNext(List<User> users) {
//            UserListPresenter.this.showUsersCollectionInView(users);
//        }
//    }
}
