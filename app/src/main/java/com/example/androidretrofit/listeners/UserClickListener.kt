package com.example.androidretrofit.listeners

import com.example.androidretrofit.models.UserDataResponse

interface UserClickListener {
    fun onClick(user: UserDataResponse)
}