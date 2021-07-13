package com.example.photobrowser.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photobrowser.model.PhotoModel
import com.example.photobrowser.model.Repository
import com.example.photobrowser.rest.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel: ViewModel() {
  private lateinit var photoRepository: Repository
  private lateinit var photoList: MutableLiveData<List<PhotoModel>>

  init {
    refreshList()
  }

  fun getPhotosList(): MutableLiveData<List<PhotoModel>> {
    return photoList
  }

  fun setPhotosList(listOfPhotos: List<PhotoModel>) {
    photoList.postValue(listOfPhotos)
  }

  private fun refreshList(image_type: String="photo", per_page: Int=50, safesearch: Boolean=true) {
    photoList=MutableLiveData<List<PhotoModel>>()
    photoRepository=Repository()

    CoroutineScope(Dispatchers.IO).launch {
      val response=photoRepository.getPhotoList(API_KEY, image_type, per_page, safesearch)
      if (response?.isSuccessful!!) {
        response.body()?.let {
          photoList.postValue(it.hits)
        }
      } else {
        Log.d("network_error", response.errorBody().toString())
      }
    }
  }
}