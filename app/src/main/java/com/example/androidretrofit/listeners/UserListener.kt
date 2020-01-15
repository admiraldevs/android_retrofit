package com.example.androidretrofit.listeners

import com.example.androidretrofit.models.UserDataResponse

interface UserListener {
    fun onClick(user: UserDataResponse)
}