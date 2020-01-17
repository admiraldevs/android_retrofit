package com.example.androidretrofit.models

data class DeleteUserResponse(
    val deleted_user: UserDataResponse,
    val message: String
)