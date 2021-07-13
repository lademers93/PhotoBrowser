package com.example.photobrowser.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photobrowser.R
import com.example.photobrowser.adapter.PhotoAdapter
import com.example.photobrowser.viewmodel.PhotoViewModel

class MainActivity: AppCompatActivity() {
  private lateinit var mAdapter: PhotoAdapter
  private lateinit var mViewModel: PhotoViewModel
  private lateinit var pDialog: ProgressDialog
  private lateinit var mRecyclerView: RecyclerView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initializeRecyclerView()
    initializeProgress()
    displayPhotosList()
  }

  override fun onDestroy() {
    super.onDestroy()
    hidePDialog()
  }

  private fun displayPhotosList() {
    mViewModel=ViewModelProvider(this).get(PhotoViewModel::class.java)
    mViewModel.getPhotosList().observe(this,
      {
        mAdapter=PhotoAdapter(it, R.layout.card_row_photo, applicationContext)
        mRecyclerView.adapter=mAdapter
        hidePDialog()
      }
    )
  }

  private fun initializeProgress() {
    pDialog=ProgressDialog(this)
    pDialog.setMessage("Loading...")
    pDialog.show()
  }

  private fun initializeRecyclerView() {
    mRecyclerView=findViewById(R.id.photo_list)
    mRecyclerView.layoutManager=LinearLayoutManager(this)
    mRecyclerView.itemAnimator=DefaultItemAnimator()
  }

  private fun hidePDialog() {
    pDialog.dismiss()
  }
}