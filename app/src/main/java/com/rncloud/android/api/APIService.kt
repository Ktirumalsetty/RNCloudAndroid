package com.rncloud.android.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rncloud.android.BuildConfig
import com.rncloud.android.data.model.LoginDataModel
import com.rncloud.android.data.model.LoginResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {

    companion object Factory {

        val BASE_URL = "https://kryptostext-dev.azurewebsites.net/api/"

        fun getInstance(): APIService {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                okHttpClientBuilder.addInterceptor(logging)
            }
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

            return retrofit.create(APIService::class.java)

        }
    }


    @POST("LoginForgotPass/UserValidationCallForLogin")
//    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginDataModel: LoginDataModel): Deferred<Response<LoginResponse>>
    fun userLogin(@Body loginDataModel: LoginDataModel): Call<LoginResponse>
}