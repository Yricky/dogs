package com.yricky.dogs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yricky.dogs.R
import com.yricky.dogs.databinding.ActivityMainBinding
import com.yricky.dogs.ui.fragment.DogListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionBar)


        supportFragmentManager.apply {
            if(findFragmentByTag("list") == null){
                beginTransaction().add(R.id.list_container,DogListFragment(),"list").commit()
            }
        }
    }
}