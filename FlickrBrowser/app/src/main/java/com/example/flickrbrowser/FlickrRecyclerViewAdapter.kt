package com.example.flickrbrowser

import academy.learnprogramming.flickrbrowser.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.photo)
}

class FlickrRecyclerViewAdapter(private var photoList : List<Photo>) : RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val TAG = "FlickrRVA"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        // Called by layout manager when it needs a new view
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        //Called by layout manager when it wants new data in an existing view

        Log.d(TAG, ".onBindViewHolder: ${photoList.size}")

        if (photoList.isEmpty()) {
            holder.thumbail.setImageResource(R.drawable.image_logo)
            holder.title.setText(R.string.empty_photo)
        } else {
            val photoItem = photoList[position]
            Picasso.with(holder.thumbail.context).
            load(photoItem.image).
            error(R.drawable.image_logo).
            into(holder.thumbail)

            holder.title.setText(photoItem.title)
        }
    }

    override fun getItemCount(): Int {
        //Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    fun loadNewPhoto(newPhotos: List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}