package com.thangnc.wallpaper.api;

import com.thangnc.wallpaper.models.latest.Latest;
import com.thangnc.wallpaper.models.photo.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("posts?_embed")
    Call<List<Latest>> getLastest();

    @GET("media")
    Call<List<Photo>> getPhoto(@Query("page") int page, @Query("per_page") int per_page, @Query("parent") String parent);

}
