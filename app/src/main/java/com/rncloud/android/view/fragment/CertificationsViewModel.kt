package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.databinding.CertificationsFragmentBinding
import com.rncloud.android.model.CertificationsResp
import com.rncloud.android.model.EducationsResp
import com.rncloud.android.model.GenericReqModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertificationsViewModel : BaseViewModel() {


    private val _certificationsRespLiveData : MutableLiveData<CertificationsResp> = MutableLiveData()
    val certificationsRespLiveData : LiveData<CertificationsResp> = _certificationsRespLiveData


    fun getCertificationsInfo(){
        _isLoading.value = true

        apiService.getCertificationsInfo(AppPreferences.authGenKey, GenericReqModel()).enqueue(object:
            Callback<CertificationsResp> {
            override fun onFailure(call: Call<CertificationsResp>, t: Throwable) {
                _isLoading.value = false
                _certificationsRespLiveData.value=null

            }

            override fun onResponse(
                call: Call<CertificationsResp>,
                response: Response<CertificationsResp>
            ) {
                _isLoading.value = false
                _certificationsRespLiveData.value=response.body()


            }

        })

    }


}
