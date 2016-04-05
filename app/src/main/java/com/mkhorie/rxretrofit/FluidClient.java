package com.mkhorie.rxretrofit;

import com.google.gson.Gson;
import com.mkhorie.rxretrofit.models.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mauro on 16-04-04.
 */
public class FluidClient {

    private static final String TAG = FluidClient.class.getSimpleName();
    private static final String ENVIRONMENT = "http://jsonplaceholder.typicode.com";

    /**
     * Define the REST endpoints here
     */
    public interface ApiInterface {
        @GET("users")
        Observable<List<User>> getUsers();

        //@GET("users/{username}")
        //Call<User> getUser(@Path("username") String username);

        //@GET("group/{id}/users")
        //Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

        //@POST("users/new")
        //Call<User> createUser(@Body User user);
    }

    private ApiInterface restClient;

    public FluidClient(Gson gson) {
        restClient = new Retrofit.Builder()
                .baseUrl(ENVIRONMENT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    public Observable<List<User>> syncUsers() {
        return restClient
                .getUsers()
                .flatMap(new Func1<List<User>, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(List<User> users) {
                        // Save to database or whatever here prior to continuing.
                        // This code is here just to demonstrate stuff like this is possible.
                        return Observable.just(users);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
