package com.greggz77.bcodingmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.greggz77.bcodingmvvm.R
import kotlinx.android.synthetic.main.fragment_recview_toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    /*private fun initUi() {

        val factory = InjectorUtils.provideUserViewModelFactory(application)
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(UserViewModel::class.java)
        viewModel.getUsers().observe(this, Observer { users ->
            usernameText.text = users.toString()
            Log.i("BCodingMVVM:MainActivity", users.toString())
        })
    }*/

    /*private fun initUi() {

        val factory = InjectorUtils.provideAlbumViewModelFactory(application)
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(AlbumViewModel::class.java)
        viewModel.getAlbums().observe(this, Observer { albums ->
            albumText.text = albums.toString()
            Log.i("BCodingMVVM:MainActivity", albums.toString())
        })
    }*/

    /*private fun initUi() {

        val factory = InjectorUtils.providePhotoViewModelFactory(application = Application())
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(PhotoViewModel::class.java)
        viewModel.getPhotos().observe(this, Observer { photos ->
            usersTextView.text = photos.toString()
            Log.i("BCodingMVVM:MainActivity", photos.toString())
        })
    }*/
}
