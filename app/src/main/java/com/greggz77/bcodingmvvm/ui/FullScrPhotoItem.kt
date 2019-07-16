package com.greggz77.bcodingmvvm.ui

import android.view.View
import com.bumptech.glide.Glide
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Photo
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.full_screen_img.view.*

class FullScrPhotoItem (val fullScrPhotoEntry: Photo, val id: Int) : Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.full_screen_img
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.apply {
                itemView.imageTitleText.text = fullScrPhotoEntry.title
                Glide.with(itemView).load(fullScrPhotoEntry.url).into(itemView.fullScreenView)

                itemView.setOnClickListener {
                    if (itemView.imageTitleText.visibility == View.VISIBLE) {
                        itemView.imageTitleText.visibility = View.INVISIBLE
                    } else {
                        itemView.imageTitleText.visibility = View.VISIBLE
                    }
                }
            }
        }
}