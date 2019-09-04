package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.MedicalDocsResp
import com.rncloud.android.model.GenericReqModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicalDocsViewModel : BaseViewModel() {

    private val _medicalDocRespLiveData : MutableLiveData<MedicalDocsResp> = MutableLiveData()
    val medicalDocRespLiveData : LiveData<MedicalDocsResp> = _medicalDocRespLiveData

    fun getEmploymentInfo(){
        _isLoading.value = true

        apiService.getMedicalDocsInfo(AppPreferences.authGenKey, GenericReqModel()).enqueue(object:
            Callback<MedicalDocsResp> {
            override fun onFailure(call: Call<MedicalDocsResp>, t: Throwable) {
                _isLoading.value =false
                _medicalDocRespLiveData.value = null

            }

            override fun onResponse(call: Call<MedicalDocsResp>, response: Response<MedicalDocsResp>) {
                _isLoading.value =false
                _medicalDocRespLiveData.value = response.body()
            }

        })
    }


}
