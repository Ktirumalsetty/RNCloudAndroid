package com.rncloud.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Patterns

import com.rncloud.android.base.BaseViewModel
import com.rncloud.android.model.LoginDataModel
import com.rncloud.android.model.LoginResponse
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

//    private val _isLoading:MutableLiveData<Boolean> = MutableLiveData()
//    val isLoading:LiveData<Boolean> = _isLoading

    private val _loginResponseLiveData = MutableLiveData<LoginResponse>()
    var loginResponseLiveData:LiveData<LoginResponse> = _loginResponseLiveData

    /**
     * login API to authenticate
     */
    fun login(loginDataModel: LoginDataModel){
        _isLoading.value = true
        val call = apiService.userLogin(loginDataModel)
        call.enqueue(object :retrofit2.Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _loginResponseLiveData.value = null
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                    _loginResponseLiveData.value = response.body()

            }

        })
    }

//    fun loginDataChanged(username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
//        }
//    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5;
    }

    fun getLoginRespLiveData() = loginResponseLiveData
}
