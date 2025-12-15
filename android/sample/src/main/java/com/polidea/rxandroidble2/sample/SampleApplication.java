package com.polidea.rxandroidble2.sample;

import android.app.Application;
import android.content.Context;

import android.util.Log;
import com.polidea.rxandroidble2.LogConstants;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class SampleApplication extends Application {

    private RxBleClient rxBleClient;

    /**
     * In practice you will use some kind of dependency injection pattern.
     */
    public static RxBleClient getRxBleClient(Context context) {
        SampleApplication application = (SampleApplication) context.getApplicationContext();
        return application.rxBleClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
