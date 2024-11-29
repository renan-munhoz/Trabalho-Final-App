package com.example.backend;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface ApiService {

    @GET("/identified/getData")
    Call<List<Filme>> getData();

    @GET("/identified/getData/{id}")
    Call<Filme> getDataById(@Path("id") int id);

    @POST("/identified/saveData")
    Call<ResponseFilme> postData(@Body AuxFilme data);

}

