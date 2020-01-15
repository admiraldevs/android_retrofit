package com.example.androidretrofit.networks

import androidx.lifecycle.MutableLiveData
import com.example.androidretrofit.models.BaseUserResponse
import com.example.androidretrofit.models.UserDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNetworkRepository {

    fun getUsers(): MutableLiveData<ArrayList<UserDataResponse>> {
        val usersData = MutableLiveData<ArrayList<UserDataResponse>>()
        val listUsers = ArrayList<UserDataResponse>()

        NetworkConfig.userApi().fetchUsers().enqueue(object: Callback<BaseUserResponse> {
            override fun onFailure(call: Call<BaseUserResponse>, t: Throwable) {
                usersData.postValue(null)
            }

            override fun onResponse(
                call: Call<BaseUserResponse>,
                response: Response<BaseUserResponse>
            ) {

                if (response.isSuccessful) {
                    val listSize = response.body()?.data?.size as Int

                    for (i in 0 until listSize) {
                        listUsers.add(response.body()?.data?.get(i) as UserDataResponse)
                    }
                    usersData.postValue(listUsers)
                }
            }

        })
        
        return usersData
    }
}