package com.projects.moi.ca.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.projects.moi.ca.R;
import com.projects.moi.ca.domain.News;
import com.projects.moi.ca.presentation.CaApplication;
import com.projects.moi.ca.presentation.presenter.NewsListPresenter;
import com.projects.moi.ca.presentation.view.activity.view.NewsView;

import java.util.List;

public class NewsActivity extends BaseActivity implements NewsView {

    /**
     * The tag activity
     */
    private static final String TAG = "NewsActivity";

    /**
     * New list presenter
     */
    private NewsListPresenter presenter;

    /**
     * On create activity
     * @param savedInstanceState on saved instace
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init(TAG);

        CaApplication app = CaApplication.getInstance();

        setContentView(R.layout.activity_test);

        presenter = new NewsListPresenter(app.getNewsRepository(), app.getExecutor(), app.getPostExecution());
        presenter.setView(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveNews();
            }
        });
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
     * Notification news list
     * @param list the new list
     */
    @Override
    public void notificationNewsList(List<News> list) {
        if ((list != null) && (list.size() > 0)) {
            Logger.v("List de news recibida: " + list.get(0).getDescription());

            // hide the fab button
//            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//            fab.setVisibility(View.GONE);
        }
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
