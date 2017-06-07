package com.jdasin.www.simplecrud;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by az on 06-06-17.
 */

public class SimpleCrudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
