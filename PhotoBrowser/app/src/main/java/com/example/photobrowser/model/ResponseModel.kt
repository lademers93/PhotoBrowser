package com.example.photobrowser.model

import com.google.gson.annotations.Expose

data class ResponseModel(
  @Expose
  val total: Int,
  @Expose
  val totalHits: Int,
  @Expose
  val hits: List<PhotoModel>?
)