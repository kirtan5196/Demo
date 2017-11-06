package com.example.apple.demo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.support.multidex.MultiDexApplication;


/**
 * Created by apple on 07/09/17.
 */

public class client extends MultiDexApplication{

    protected static client sInstance;
    private RequestQueue mRequestQueue;


    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static client getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}