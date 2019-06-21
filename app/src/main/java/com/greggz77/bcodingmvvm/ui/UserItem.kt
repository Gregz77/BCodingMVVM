package com.greggz77.bcodingmvvm.ui

import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.User
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.users_list.view.*

class UserItem(private val userEntry: User) : Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            itemView.usernameText.text = userEntry.username
            itemView.nameText.text = userEntry.name
        }
    }

    override fun getLayout(): Int {
        return R.layout.users_list
    }
}