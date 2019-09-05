package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.SchedulesResp
import com.rncloud.android.model.SchedulesReqModel
import com.rncloud.android.model.SendApiRequestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleViewModel : BaseViewModel() {

    private val _scheduleRespLiveData : MutableLiveData<SchedulesResp> = MutableLiveData()
    val scheduleRespLiveData : LiveData<SchedulesResp> = _scheduleRespLiveData

    fun getSchedules(){
        _isLoading.value = true
        val call = apiService.getSchedules(AppPreferences.authGenKey, SchedulesReqModel(NurseActorCode = AppPreferences.actorCode,
            EndIndex = 500,StartIndex = 0,RegistryActorCode = 0))
        call.enqueue(object:Callback<SchedulesResp>{
            override fun onFailure(call: Call<SchedulesResp>, t: Throwable) {
                _isLoading.value = false
                _scheduleRespLiveData.value = null
            }

            override fun onResponse(call: Call<SchedulesResp>, response: Response<SchedulesResp>) {
                _isLoading.value = false
                _scheduleRespLiveData.value = response.body()            }

        })
    }

}
