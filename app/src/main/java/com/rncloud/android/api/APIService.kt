package com.rncloud.android.api

import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import com.rncloud.android.BuildConfig
import com.rncloud.android.model.LoginDataModel
import com.rncloud.android.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {

    companion object Factory {

        val BASE_URL = "https://kryptostext-dev.azurewebsites.net/api/"

        fun getInstance(): APIService {
            val okHttpClientBuilder = Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                okHttpClientBuilder.addInterceptor(logging)
            }
//            val gson = GsonBuilder().setLenient().create()
//            val retrofit = Retrofit.Builder()
//                .baseUrl("YOUR URL")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
//                .build()

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(APIService::class.java)

        }
    }


    @POST("LoginForgotPass/UserValidationCallForLogin")
//    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginDataModel: LoginDataModel): Deferred<Response<LoginResponse>>
    fun userLogin(@Body loginDataModel: LoginDataModel): LiveData<ApiResponse<LoginResponse>>
}