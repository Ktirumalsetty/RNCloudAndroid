package com.rncloud.android.api

import Constants.BASE_URL
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import com.rncloud.android.BuildConfig
import com.rncloud.android.model.*
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {

    companion object Factory {

        fun getInstance(): APIService {
            val okHttpClientBuilder = Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
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
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(APIService::class.java)

        }
    }


    @POST("Login/xLoginRequest")
//    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginDataModel: LoginDataModel): Deferred<Response<LoginResponse>>
    fun userLogin(@Body loginDataModel: LoginDataModel): Call<LoginResponse>

    @POST("HCP/GetActorPersonalDetailInfo")
//    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginDataModel: LoginDataModel): Deferred<Response<LoginResponse>>
    fun getPersonalDetails(@Header("AuthGenKEY") authGenKey:String, @Body personalInfoReqModel: GenericReqModel): Call<PersonalDetailsRespModel>

    @POST("HCP/GetActorLicenseInfo")
    fun getLicenceInfo(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: GenericReqModel): Call<LicenceResp>

    @POST("HCP/GetActorEducationInfo")
    fun getEducationInfo(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: GenericReqModel): Call<EducationsResp>

    @POST("HCP/GetActorEmpHistoryInfo")
    fun getEmpHistoryInfo(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: GenericReqModel): Call<EmploymentResp>

    @POST("HCP/GetActorCertificationsInfo")
    fun getCertificationsInfo(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: GenericReqModel): Call<CertificationsResp>

    @POST("HCP/GetActorMedicalFitnessInfo")
    fun getMedicalDocsInfo(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: GenericReqModel): Call<MedicalDocsResp>

    @POST("MasterData/GetCodeValuesByType")
    fun getLicenceStates(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: MasterDataReqModel): Call<LicenceStateResp>

    @POST("Hcare/GetJobApplicationsInfoByRegistryOnDemand")
    fun getSchedules(@Header("AuthGenKEY") authGenKey:String, @Body reqModel: SchedulesReqModel): Call<SchedulesResp>
}