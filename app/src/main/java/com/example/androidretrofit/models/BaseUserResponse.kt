package com.example.androidretrofit.models

data class BaseUserResponse(
    val count: Int,
    val data: List<UserDataResponse>,
    val total: Int
)