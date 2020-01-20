package com.example.retromovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    //String BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=f6394d02587ba2032873e777128c1548&language=en-US&page=1";

    @Headers("Content-Type: text/html")
    @GET("3/movie/popular?api_key=f6394d02587ba2032873e777128c1548&language=en-US&page=1")
    Call<DataPojo> getMovies();

}
