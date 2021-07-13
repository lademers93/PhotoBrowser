package com.example.photobrowser.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://pixabay.com/"
const val API_KEY="22209845-0e41a1e637ade5054d52c799e"

object ApiClient {
  @Volatile
  private var retrofit: Retrofit?=null
  fun getClient(): Retrofit? {
    return retrofit ?: synchronized(this) {
      val interceptor=HttpLoggingInterceptor()
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      val client: OkHttpClient=OkHttpClient.Builder().addInterceptor(interceptor).build()
      retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
      retrofit
    }
  }
}