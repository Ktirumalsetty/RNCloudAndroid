package com.rncloud.android.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.GenericReqModel
import com.rncloud.android.model.LicenceStateResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.rncloud.android.model.MasterDataReqModel
import com.rncloud.android.model.SendApiRequestModel


class AddEditLicenceViewModel : BaseViewModel() {

    private val _statesRespLiveData : MutableLiveData<LicenceStateResp> = MutableLiveData()
    val statesRespLiveData : LiveData<LicenceStateResp> = _statesRespLiveData

    fun getStates() {
        _isLoading.value = true
        apiService.getLicenceStates(AppPreferences.authGenKey, MasterDataReqModel("CTIAT"))
            .enqueue(object : Callback<LicenceStateResp> {
                override fun onFailure(call: Call<LicenceStateResp>, t: Throwable) {
                    _isLoading.value = false
                    _statesRespLiveData.value = null
                }

                override fun onResponse(
                    call: Call<LicenceStateResp>,
                    response: Response<LicenceStateResp>
                ) {
                    _isLoading.value = false
                    _statesRespLiveData.value = response.body()


                }

            })
    }

//    fun onValidFromClick(){
//
//        // Get Current Date
//        DatePickerDialog(this@MainActivity,
//            dateSetListener,
//            // set DatePickerDialog to point to today's date when it loads up
//            cal.get(Calendar.YEAR),
//            cal.get(Calendar.MONTH),
//            cal.get(Calendar.DAY_OF_MONTH)).show()
//
//
//    }
}