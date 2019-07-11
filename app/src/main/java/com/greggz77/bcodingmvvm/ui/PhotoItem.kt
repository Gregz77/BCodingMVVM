package com.greggz77.bcodingmvvm.ui

import com.bumptech.glide.Glide
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Photo
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.photos_list.view.*

class PhotoItem(val photoEntry: Photo, val id: Int) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.photos_list
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            itemView.photoText1.text = photoEntry.title
            Glide.with(itemView).load(photoEntry.thumbnailUrl).into(itemView.imageView1)
        }
    }
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2
}