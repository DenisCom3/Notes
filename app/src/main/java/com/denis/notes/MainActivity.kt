package com.denis.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denis.notes.databinding.ActivityMainBinding
import com.denis.notes.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding:ActivityMainBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        mToolbar = binding.toolbar
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}