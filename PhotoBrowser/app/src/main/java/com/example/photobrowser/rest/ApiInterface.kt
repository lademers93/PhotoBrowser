package com.example.photobrowser.rest

import com.example.photobrowser.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
  @GET("api/")
  suspend fun getPhotoList(
    @Query("key") key: String,
    @Query("image_type") image_type: String,
    @Query("per_page") results: Int,
    @Query("safesearch") safesearch: Boolean
  ): Response<ResponseModel>
}