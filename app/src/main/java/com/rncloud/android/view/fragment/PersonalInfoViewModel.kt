package com.rncloud.android.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.LoginResponse
import com.rncloud.android.model.PersonalDetailsRespModel
import com.rncloud.android.model.PersonalInfoReqModel
import com.rncloud.android.model.SendApiRequestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalInfoViewModel : BaseViewModel() {


    private val _personalInfoRespLiveData = MutableLiveData<PersonalDetailsRespModel>()
    var personalInfoResponseLiveData: LiveData<PersonalDetailsRespModel> = _personalInfoRespLiveData


    fun getPersonalInfo (){
       val call=  apiService.getPersonalDetails(PersonalInfoReqModel(AppPreferences.actorCode,
            SendApiRequestModel(AppPreferences.authGenKey,1,AppPreferences.userName)
        ))

        call.enqueue(object :Callback<PersonalDetailsRespModel>{
            override fun onFailure(call: Call<PersonalDetailsRespModel>, t: Throwable) {
                _personalInfoRespLiveData.value= null
            }

            override fun onResponse(
                call: Call<PersonalDetailsRespModel>,
                response: Response<PersonalDetailsRespModel>
            ) {
                _personalInfoRespLiveData.value= response.body()

            }

        })

    }

}
