package com.example.plantest;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static OkHttpClient client;
    private static Retrofit retrofit;
    private static Gson gson;
    private static ApiSpace api;

//    public static OkHttpClient getClient() {
//        client = new OkHttpClient();
//        return client;
//    }

    public static Retrofit getRetrofit() {
        if (gson == null) {
            gson = new Gson();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
//                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }return retrofit;

    }
    public static ApiSpace getApiService(){
        if (api == null) {
            api = getRetrofit().create(ApiSpace.class);
        }
        return api;

    }
}
