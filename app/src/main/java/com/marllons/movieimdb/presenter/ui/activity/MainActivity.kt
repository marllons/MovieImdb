package com.marllons.movieimdb.presenter.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marllons.movieimdb.R
import com.marllons.movieimdb.databinding.ActivityMainBinding
import com.marllons.movieimdb.presenter.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}