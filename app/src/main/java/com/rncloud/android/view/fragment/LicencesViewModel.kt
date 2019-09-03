package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rncloud.android.RNCloudApplication
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.GenericReqModel
import com.rncloud.android.model.LicenceResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LicencesViewModel : BaseViewModel() {

    private val _licenceRespLiveData :MutableLiveData<LicenceResp> = MutableLiveData()
    val licenceRespLiveData :LiveData<LicenceResp> = _licenceRespLiveData

    fun getLicences(){
        _isLoading.value = true
        val call = apiService.getLicenceInfo(AppPreferences.authGenKey,GenericReqModel())
        call.enqueue(object:Callback<LicenceResp>{
            override fun onFailure(call: Call<LicenceResp>, t: Throwable) {
                _isLoading.value = false
                _licenceRespLiveData.value = null
            }

            override fun onResponse(call: Call<LicenceResp>, response: Response<LicenceResp>) {
                _isLoading.value = false
                _licenceRespLiveData.value = response.body()
            }

        })
    }
}
