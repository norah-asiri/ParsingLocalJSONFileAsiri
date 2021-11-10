package com.example.parsinglocaljsonfileasiri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljsonfileasiri.databinding.ItemRowBinding


class RVAdapter(val activity: MainActivity, private val photos: ArrayList<String>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = photos[position]

        holder.binding.apply {
            Glide.with(activity).load(photo).into(ivThumbnail)
            llItemRow.setOnClickListener { activity.openImg(photo) }
        }
    }

    override fun getItemCount() = photos.size
}

/*

to use this import

add to gradle
id 'kotlin-android-extensions'
______________________________
add to gradle:
 buildFeatures {
        viewBinding true
    }

in side dependencies :
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1'
implementation 'com.github.bumptech.glide:glide:4.12.0'
    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

Don't forget add internet permission to manifest
    <uses-permission android:name="android.permission.INTERNET"/>
 */