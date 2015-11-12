package com.projects.moi.ca.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;

import com.projects.moi.ca.R;
import com.projects.moi.ca.presentation.presenter.NewsListPresenter;
import com.projects.moi.ca.presentation.view.activity.view.NewsView;

public class NewsActivity extends BaseActivity implements NewsView {

    /**
     * New list presenter
     */
    private NewsListPresenter presenter;

    /**
     * On create activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        presenter = new NewsListPresenter();
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.resume();
    }

    /**
     * On pause
     */
    @Override
    protected void onPause() {
        super.onPause();

        presenter.pause();
    }

    /**
     * On destroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    /**
     * Retrieve news
     */
    @Override
    public void retrieveNews() {
        presenter.loadNewsList();
    }

    /**
     * Show loading
     */
    @Override
    public void showLoading() {

    }

    /**
     * Hide loading
     */
    @Override
    public void hideLoading() {

    }

    /**
     * Show retry again
     */
    @Override
    public void showRetry() {

    }

    /**
     * Hide retry
     */
    @Override
    public void hideRetry() {

    }

    /**
     * Show error
     * @param message A string representing an error.
     */
    @Override
    public void showError(String message) {

    }

    /**
     * Get context
     * @return
     */
    @Override
    public Context getContext() {
        return null;
    }
}
