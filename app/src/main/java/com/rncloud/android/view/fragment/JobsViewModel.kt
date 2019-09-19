package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.JobsReqModel
import com.rncloud.android.model.JobsResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel : BaseViewModel() {

    private val _jobRespLiveData : MutableLiveData<JobsResp> = MutableLiveData()
    val jobRespLiveData : LiveData<JobsResp> = _jobRespLiveData

    fun getJobs(){
        _isLoading.value = true
        val call = apiService.getJobs(
            AppPreferences.authGenKey, JobsReqModel()
        )
        call.enqueue(object: Callback<JobsResp> {
            override fun onFailure(call: Call<JobsResp>, t: Throwable) {
                _isLoading.value = false
                _jobRespLiveData.value = null
            }

            override fun onResponse(call: Call<JobsResp>, response: Response<JobsResp>) {
                _isLoading.value = false
                _jobRespLiveData.value = response.body()            }

        })
    }

}
