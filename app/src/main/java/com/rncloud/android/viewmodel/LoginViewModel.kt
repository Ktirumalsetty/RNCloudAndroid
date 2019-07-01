package com.rncloud.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.rncloud.android.data.LoginRepository

import com.rncloud.android.R
import com.rncloud.android.api.ApiResponse
import com.rncloud.android.model.LoginDataModel
import com.rncloud.android.model.LoginResponse
import com.rncloud.android.ui.login.LoginFormState
import com.rncloud.android.ui.login.LoginResult

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    private val _loginResponseLiveData = MutableLiveData<ApiResponse<LoginResponse>>()
    private val loginResponseLiveData : LiveData<ApiResponse<LoginResponse>> = _loginResponseLiveData
    val loginResult: LiveData<LoginResult> = _loginResult
    fun login(loginDataModel: LoginDataModel) {
        // can be launched in a separate asynchronous job
        val loginRepository = LoginRepository()

        _loginResponseLiveData.value = loginRepository.login(loginDataModel).value
//        if (result is Result.Success) {
//            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

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
}
