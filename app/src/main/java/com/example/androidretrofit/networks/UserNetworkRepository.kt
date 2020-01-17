package com.example.androidretrofit.networks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidretrofit.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNetworkRepository {

    // get all data
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
                    Log.d("@@@Retrofit", "total: ${listUsers.size} items -- GET ALL")
                }
            }

        })

        return usersData
    }

    // get single data
    fun getUser(id : Int) : MutableLiveData<UserDataResponse> {

        val userData = MutableLiveData<UserDataResponse>()

        NetworkConfig.userApi().getUser(id).enqueue(object : Callback<SingleUserResponse<UserDataResponse>>{
            override fun onFailure(call: Call<SingleUserResponse<UserDataResponse>>, t: Throwable) {
                userData.postValue(null)
            }

            override fun onResponse(
                call: Call<SingleUserResponse<UserDataResponse>>,
                response: Response<SingleUserResponse<UserDataResponse>>
            ) {

                if(response.isSuccessful){
                    val userResponse = response.body()?.data as UserDataResponse
                    userData.postValue(userResponse)
                    Log.d("@@@Retrofit", "get: ${userResponse.name}  -- GET USER")
                } else {
                    userData.postValue(null)
                }

            }

        })


        return userData
    }

    // create data
    fun createUser(userData: UserDataResponse): MutableLiveData<UserDataResponse> {
        val createdUserData = MutableLiveData<UserDataResponse>()

        NetworkConfig.userApi().createUser(userData).enqueue(object: Callback<CreateUserResponse> {
            override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                createdUserData.postValue(null)
            }

            override fun onResponse(
                call: Call<CreateUserResponse>,
                response: Response<CreateUserResponse>) {

                if(response.isSuccessful) {
                    val createdUserResponse = response.body()?.created_users as UserDataResponse
                    createdUserData.postValue(createdUserResponse)
                    Log.d("@@@Retrofit", "id: ${userData.id}, User ${userData.name}  -- CREATED")
                } else {
                    createdUserData.postValue(null)
                }

            }

        })

        return createdUserData
    }

    // update user
    fun updateUser(id: Int, userData: UserDataResponse): MutableLiveData<UserDataResponse> {
        val updatedUserData = MutableLiveData<UserDataResponse>()

        NetworkConfig.userApi().updateUser(id, userData)
            .enqueue(object: Callback<UpdatedUserResponse> {

                override fun onFailure(call: Call<UpdatedUserResponse>, t: Throwable) {
                    updatedUserData.postValue(null)
                }

                override fun onResponse(
                    call: Call<UpdatedUserResponse>,
                    response: Response<UpdatedUserResponse>) {

                    if (response.isSuccessful) {
                        val updatedUserResponse = response.body()?.updated_user as UserDataResponse
                        updatedUserData.postValue(updatedUserResponse)
                        Log.d("@@@Retrofit", "update: ${updatedUserResponse.name}  -- UPDATE USER")
                    } else {
                        updatedUserData.postValue(null)
                    }
                }

            })

        return updatedUserData
    }
}