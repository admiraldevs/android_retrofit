package com.example.androidretrofit.listeners

import com.example.androidretrofit.models.UserDataResponse

interface MyUserListener {
    fun onClick(user: UserDataResponse)
}