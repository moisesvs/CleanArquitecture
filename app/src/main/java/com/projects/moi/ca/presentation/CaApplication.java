package com.projects.moi.ca.presentation;

import android.app.Activity;
import android.app.Application;

import com.projects.moi.ca.BuildConfig;
import com.projects.moi.ca.data.cache.NewsCache;
import com.projects.moi.ca.data.cache.NewsCacheImpl;
import com.projects.moi.ca.data.executor.JobExecutor;
import com.projects.moi.ca.data.net.HttpInvoker;
import com.projects.moi.ca.data.net.HttpInvokerHelper;
import com.projects.moi.ca.data.repository.datasource.NewsDataStoreFactory;
import com.projects.moi.ca.domain.executor.PostExecution;
import com.projects.moi.ca.domain.executor.ThreadExecutor;
import com.projects.moi.ca.domain.interactor.NewsDataRepository;
import com.projects.moi.ca.domain.repository.NewsRepository;
import com.projects.moi.ca.logging.Log;

/**
 * CaApplication is the visual application in charge of configuration screens
 * and user dialogs to invoke phone calls and messaging operations.
 */
public class CaApplication extends Application     {

    /**
     * The application tag
     */
    private static final String TAG = "CaApplication";

    /**
     * Instance Ca application
     */
    private static CaApplication instance;

    /**
     * The logger
     */
    private Log logger;

    /**
     * The http invoker
     */
    private HttpInvoker httpInvoker;

    /**
     * The post execution
     */
    private PostExecution postExecution;

    /**
     * JobExecutor pool executor
     */
    private JobExecutor jobExecutor;

    /**
     * The news repository
     */
    private NewsRepository newsRepository;

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Implementations should be as quick as possible (for example using lazy initialization of state)
     * since the time spent in this function directly impacts the performance of starting the first
     * activity, service, or receiver in a process.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplication();
    }

    /**
     * This method is for use in emulated process environments. It will never be called on
     * a production Android device, where processes are removed by simply killing them; no
     * user code (including this callback) is executed when doing so.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * Received when application is resuming from background execution.
     *
     * @param isResumeApp if the app has been resumed
     * @param pendingLogout true if session expired while background
     */
    public void onResumingFromBackground(boolean isResumeApp,
                                         boolean pendingLogout) {
        // nothing
    }

    /**
     * Initialize application stuff
     */
    public synchronized void initializeApplication() {

        instance = this;

        logger = new Log(BuildConfig.DEBUG);

        jobExecutor = new JobExecutor();

        NewsCache cache = new NewsCacheImpl(this, jobExecutor);
        newsRepository = new NewsDataRepository(new NewsDataStoreFactory(this, cache), null);
        postExecution = new PostExecution();

        HttpInvokerHelper enaxInvokerHelper = new HttpInvokerHelper(this, this.logger);
        httpInvoker = enaxInvokerHelper.createHttpInvoker();

//        if (ConfigApp.DEBUG) {
//            // LeakCanary will automatically show a notification when an activity memory leak is detected in your debug build.
//            LeakCanary.install(this);
//        }
    }

    /**
     * Get instance app
     *
     * @return the instance
     */
    public static CaApplication getInstance() {
        return instance;
    }

    /**
     * Get executors
     * @return the executors thread
     */
    public ThreadExecutor getExecutor() {
        return jobExecutor;
    }

    /**
     * Get news repository
     * @return the news repository
     */
    public NewsRepository getNewsRepository() {
        return newsRepository;
    }

    /**
     * The get execution
     * @return the get execution
     */
    public PostExecution getPostExecution() {
        return postExecution;
    }

    /**
     * Method that execute when app has been resumed
     */
    private void resumedApp () {
    }

    /**
     * Kill the app either safely or quickly. The app is killed safely by
     * killing the virtual machine that the app runs in after finalizing all
     * {@link Object}s created by the app. The app is killed quickly by abruptly
     * killing the process that the virtual machine that runs the app runs in
     * without finalizing all {@link Object}s created by the app. Whether the
     * app is killed safely or quickly the app will be completely created as a
     * new app in a new virtual machine running in a new process if the user
     * starts the application again.
     */
    public void killApp(Activity activity) {
        boolean isNotLastActivity = true;
        if (activity != null) {
            Activity below = activity.getParent();
            activity.finish();
            activity.finishAffinity();
            if (below == null)
                return;

            while (isNotLastActivity) {
                Activity temp = below;
                try {
                    below = temp.getParent();
                    temp.finish();
                } catch (Exception e) {
                    isNotLastActivity = false;
                }
            }
        }
    }

    /**
     * Get logger
     * @return the logger
     */
    public Log getLogger() {
        return logger;
    }

    /**
     * Get http invoker
     * @return the http invoker
     */
    public HttpInvoker getHttpInvoker() {
        return httpInvoker;
    }
}
