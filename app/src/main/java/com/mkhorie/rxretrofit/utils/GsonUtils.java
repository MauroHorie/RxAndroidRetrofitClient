package com.mkhorie.rxretrofit.utils;

import com.google.gson.Gson;

/**
 * Utility class related to Gson.
 * The Gson guide can be found here: https://github.com/google/gson/blob/master/UserGuide.md
 *
 * Created by Mauro on 16-02-29.
 */
public class GsonUtils {

    /**
     * Instantiates and configures the Gson object to serialize / deserialize objects correctly
     * depending on application rules. For now, there are no serialization or deserialization rules,
     * so we simply return a new Gson instance.
     *
     * @return the Gson instance configured for application use.
     */
    public static Gson getConfiguredGson() {
        return new Gson();
    }
}
