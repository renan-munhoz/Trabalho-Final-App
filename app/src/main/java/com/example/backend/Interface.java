package com.example.backend;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface Interface {
    @GET("/getData")
    Call<Filme> getData();

    @GET("/identified/getData/{id}")
    Call<Filme> getDataById(@Path("id") int id);

    @POST("/identified/saveData")
    Call<Filme> postData(@Body Filme data);

    @GET("/getAllFilmes")
    Call<List<Filme>> getAllFilmes();
}
