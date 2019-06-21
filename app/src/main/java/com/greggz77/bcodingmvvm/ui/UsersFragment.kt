package com.greggz77.bcodingmvvm.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.User
import com.greggz77.bcodingmvvm.data.viewModels.UserViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }

    private fun initUi() {

        val factory = InjectorUtils.provideUserViewModelFactory(application = Application())
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(UserViewModel::class.java)
        viewModel.getUsers().observe(this, Observer { users ->
            initRecyclerView(users.toUserItems())
            Log.i("InitUi callback userList", users.toString())
            group_loading.visibility = View.GONE
        })
    }

    private fun List<User>.toUserItems(): List<UserItem> {
        return this.map {
            UserItem(it)
        }
    }

    private fun initRecyclerView(items: List<UserItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsersFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item is AlbumItem).let {
                showAlbumList(view)
                Log.i("click ------------------------------------------------------", item.toString())
            }
        }
    }

    private fun showAlbumList(view: View) {
        val actionAlbumList = UsersFragmentDirections.actionUsersFragmentToAlbumsFragment()
        Navigation.findNavController(view).navigate(actionAlbumList)
    }
}