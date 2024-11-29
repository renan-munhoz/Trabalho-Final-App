package com.example.backend;
import android.util.Log;

import com.google.gson.Gson;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class RetrofitClient {
    private static final String BASE_URL = "https://trabalho-final-api-production.up.railway.app/";
    private static ApiService apiService;
    private static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImNpbmVtYWRpcmVnaTIiLCJpYXQiOjE3MzI5MDczODYsImV4cCI6MTczMjkxMDk4Nn0.2x3v6fsSMfwO93V7SwD96t35X7RmRqDQnn9sBFEY_q8";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static ApiService getApiService() {
        if (apiService == null) {
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });

            Retrofit retrofit = builder.client(httpClient.build()).build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


}
