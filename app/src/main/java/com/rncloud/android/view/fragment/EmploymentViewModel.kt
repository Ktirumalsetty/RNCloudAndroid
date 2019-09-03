package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.EducationsResp
import com.rncloud.android.model.EmploymentResp
import com.rncloud.android.model.GenericReqModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmploymentViewModel : BaseViewModel() {

    private val _employmentRespLiveData : MutableLiveData<EmploymentResp> = MutableLiveData()
    val employmentRespLiveData : LiveData<EmploymentResp> = _employmentRespLiveData

  fun getEmploymentInfo(){
      _isLoading.value = true

      apiService.getEmpHistoryInfo(AppPreferences.authGenKey, GenericReqModel()).enqueue(object:
          Callback<EmploymentResp>{
          override fun onFailure(call: Call<EmploymentResp>, t: Throwable) {
              _isLoading.value =false
              _employmentRespLiveData.value = null

          }

          override fun onResponse(call: Call<EmploymentResp>, response: Response<EmploymentResp>) {
              _employmentRespLiveData.value = response.body()
          }

      })
  }
}
