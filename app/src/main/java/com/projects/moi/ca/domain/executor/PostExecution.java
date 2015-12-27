package com.projects.moi.ca.domain.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * MainThread implementation based on a Handler instantiated over the main looper obtained from
 * Looper class.
 *
 * @author Moisés Vázquez Sánchez
 */
public class PostExecution implements PostExecutionThread {

    /**
     * The handler execution
     */
    private Handler handler;

    /**
     * The post execution
     */
    public PostExecution() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * The post runnable
     * @param runnable the post runnable
     */
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
