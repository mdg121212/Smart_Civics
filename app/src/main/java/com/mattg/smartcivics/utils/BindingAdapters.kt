package com.mattg.smartcivics.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mattg.smartcivics.R

@BindingAdapter("android:setGlideImage")
fun loadImage(imageView: ImageView, string: String){
    Glide.with(imageView)
        .load(string)
        .placeholder(R.drawable.man)
        .into(imageView)
}