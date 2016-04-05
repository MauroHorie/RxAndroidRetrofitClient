package com.mkhorie.rxretrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.mkhorie.rxretrofit.utils.GsonUtils;

/**
 * Created by Mauro on 16-04-04.
 */
public class App extends Application {

    private Gson gson;
    private FluidClient restClient;

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        gson = GsonUtils.getConfiguredGson();
        restClient = new FluidClient(gson);
    }

    /**
     * @return the rest client.
     */
    public FluidClient getRestClient() {
        return restClient;
    }
}
