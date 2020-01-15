package com.example.androidretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidretrofit.models.UserDataResponse
import com.example.androidretrofit.networks.UserNetworkRepository

class UserViewModel :ViewModel() {

    fun getListUsers(): MutableLiveData<ArrayList<UserDataResponse>> {
        return UserNetworkRepository().getUsers()
    }
}