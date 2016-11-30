package com.example.videolist.networking;


import com.example.videolist.networking.model.BaseResponse;
import com.example.videolist.networking.model.DataListResponse;
import com.example.videolist.networking.model.Error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RestApi {

    public static final String ENDPOINT = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/";
    private static WebService webService;

    public static WebService getWebService() {
        if (webService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webService = retrofit.create(WebService.class);
        }
        return webService;
    }

    @SuppressWarnings("WeakerAccess")
    public interface WebService {
        @GET("videos-enhanced-c.json")
        Call<DataListResponse> getVideoList();
    }

    /**
     * Class for simplifying work with retrofit callbacks. Retrofit return onResponse callback
     * for success and error calls, so failure and success methods will help with processing
     * errors and success calls in controllers
     */
    public static abstract class RestCallback<T extends BaseResponse> implements Callback<T> {
        public abstract void failure(Error error);

        public abstract void success(Response<T> response);

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response != null) {
                if (response.body() != null) {
                    if (response.body().error != null)
                        failure(response.body().error);
                    else
                        success(response);
                } else {
                    failure(new Error(response.raw().code(), response.raw().message()));
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (t.getMessage() != null) {
                Error error = new Error(0, t.getMessage());
                failure(error);
            }
        }
    }
}
