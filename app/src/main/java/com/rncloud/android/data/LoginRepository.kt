package com.rncloud.android.data

import com.rncloud.android.api.APIService
import com.rncloud.android.data.model.LoggedInUser
import com.rncloud.android.data.model.LoginDataModel
import com.rncloud.android.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import androidx.lifecycle.MutableLiveData


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val apiService: APIService) {

//    // in-memory cache of the loggedInUser object
//    var user: LoggedInUser? = null
//        private set
//
//    val isLoggedIn: Boolean
//        get() = user != null
//
//    init {
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//        user = null
//    }
//
//    fun logout() {
//        user = null
//        dataSource.logout()
//    }

    fun login(loginDataModel: LoginDataModel): Result<LoggedInUser> {
        // handle login
        val loginResponse = MutableLiveData<LoginResponse>()
        val call = apiService.userLogin(loginDataModel)
        call.enqueue(object: retrofit2.Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResponse.value = LoginResponse(throwable = t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                loginResponse.value = LoginResponse(response.body().users)
            }

        })

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
