package com.sidchiku9.retrofit2;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<Posts>> getPosts(@Query("usedId") Integer[] userId,
                               @Query("_sort") String sort,
                               @Query("_order") String order);

    @GET("posts")
    Call<List<Posts>> getPosts(@QueryMap Map<String,String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comments>> getComments(@Url String url);
}
