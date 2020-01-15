package com.example.androidretrofit.models

data class CreateUserResponse(
    val created_users: UserDataResponse,
    val message: String
)