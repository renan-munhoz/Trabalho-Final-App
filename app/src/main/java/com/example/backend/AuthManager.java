package com.example.backend;

import android.util.Log;

import java.io.IOException;

import retrofit2.Response;

public class AuthManager {
    private ApiService apiService;

    public AuthManager() {
        this.apiService = RetrofitClient.getApiService();
    }
}
