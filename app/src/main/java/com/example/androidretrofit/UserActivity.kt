package com.example.androidretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

class UserActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
    }

    private fun initViewModel() {
        //pbUserProgressForm.visibility = View.VISIBLE
        viewModel = ViewModelProviders.of(this)
            .get(UserViewModel::class.java)
    }
}
