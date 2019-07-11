package com.greggz77.bcodingmvvm.ui

import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Album
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.albums_list.view.*


class AlbumItem(val albumEntry: Album, val id: Int) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.albums_list
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.albumText.text = albumEntry.title
    }
}
