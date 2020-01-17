package com.example.androidretrofit.networks

import com.example.androidretrofit.BuildConfig
import com.example.androidretrofit.models.BaseUserResponse
import com.example.androidretrofit.models.CreateUserResponse
import com.example.androidretrofit.models.SingleUserResponse
import com.example.androidretrofit.models.UserDataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

class NetworkConfig {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null

        private fun getUserRetrofit(): Retrofit {
            return retrofit ?: synchronized(this) {
                retrofit ?: buildUserRetrofit().also {
                    retrofit = it
                }
            }
        }

        private fun buildUserRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.USER_BASE_URL)
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun getInterceptor(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

        fun userApi(): UserApiService {
            return getUserRetrofit().create(UserApiService::class.java)
        }
    }
}

interface UserApiService {
    // get all data
    @GET("api/v1/users")
    fun fetchUsers()
            : Call<BaseUserResponse>
    // get single data
    @GET("api/v1/user/{id}")
    fun getUser(@Path("id") id: Int)
            :Call<SingleUserResponse<UserDataResponse>>
    // create data
    @POST("api/v1/user")
    fun createUser(@Body userData: UserDataResponse)
            : Call<CreateUserResponse>

}