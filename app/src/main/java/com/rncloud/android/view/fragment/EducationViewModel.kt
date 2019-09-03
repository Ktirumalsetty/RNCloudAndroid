package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.EducationsResp
import com.rncloud.android.model.GenericReqModel
import com.rncloud.android.model.LicenceResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationViewModel : BaseViewModel() {

    private val _educationRespLiveData : MutableLiveData<EducationsResp> = MutableLiveData()
    val educationRespLiveData : LiveData<EducationsResp> = _educationRespLiveData


    fun getEducationsInfo(){
        _isLoading.value = true

        apiService.getEducationInfo(AppPreferences.authGenKey, GenericReqModel()).enqueue(object:Callback<EducationsResp>{
            override fun onFailure(call: Call<EducationsResp>, t: Throwable) {
                _isLoading.value = false

            }

            override fun onResponse(
                call: Call<EducationsResp>,
                response: Response<EducationsResp>
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}
