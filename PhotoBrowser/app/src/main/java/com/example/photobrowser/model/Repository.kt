package com.example.photobrowser.model

import com.example.photobrowser.rest.ApiClient
import com.example.photobrowser.rest.ApiInterface
import retrofit2.Response

class Repository {
  suspend fun getPhotoList(
    key: String,
    image_type: String,
    per_page: Int,
    safesearch: Boolean
  ): Response<ResponseModel>? {
    val myService=ApiClient.getClient()?.create(ApiInterface::class.java)
    return myService?.getPhotoList(key, image_type, per_page, safesearch)
  }
}