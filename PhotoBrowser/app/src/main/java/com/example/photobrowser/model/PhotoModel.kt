package com.example.photobrowser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoModel(
  @Expose
  val id: Int,
  @Expose
  @SerializedName("largeImageURL")
  val imageURL: String,
  @Expose
  val views: Int,
  @Expose
  val downloads: Int,
  @Expose
  val comments: Int,
  @Expose
  val likes: Int,
  @Expose
  val user: String,
  @Expose
  val userImageURL: String
)