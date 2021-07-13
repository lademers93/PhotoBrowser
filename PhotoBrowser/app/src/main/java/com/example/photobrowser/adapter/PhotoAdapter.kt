package com.example.photobrowser.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.photobrowser.R
import com.example.photobrowser.model.PhotoModel

class PhotoAdapter(
  private var photoList: List<PhotoModel>,
  private var rowLayout: Int,
  private var mContext: Context
): Adapter<PhotoAdapter.ViewHolder>() {

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
    val v=LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val photo: PhotoModel=photoList[position]
    Log.d("network_response", photo.imageURL)
    Glide.with(mContext)
      .load(photo.imageURL)
      .into(holder.photo)
    Glide.with(mContext)
      .load(photo.userImageURL)
      .into(holder.userImage)
    holder.username.text=photo.user
    holder.likesImage.setImageDrawable(AppCompatResources.getDrawable(mContext, R.drawable.ic_outline_like_24))
    holder.likes.text=photo.likes.toString()
  }

  override fun getItemCount(): Int {
    return photoList.size
  }

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val likesImage=itemView.findViewById(R.id.likes_image) as ImageView
    val likes=itemView.findViewById(R.id.likes) as TextView
    val photo=itemView.findViewById(R.id.photo) as ImageView
    val userImage=itemView.findViewById(R.id.user_image) as ImageView
    val username=itemView.findViewById(R.id.username) as TextView
  }
}