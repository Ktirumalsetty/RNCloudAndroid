package com.rncloud.android.data

import androidx.lifecycle.LiveData
import com.rncloud.android.api.APIService
import com.rncloud.android.model.LoginDataModel
import com.rncloud.android.model.LoginResponse
import com.rncloud.android.api.ApiResponse
import javax.inject.Inject


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository () {

    @Inject
    internal lateinit var apiService:APIService
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

    fun login(loginDataModel: LoginDataModel): LiveData<ApiResponse<LoginResponse>> {
        // handle login
//        val loginResponse = MutableLiveData<LoginResponse>()
        return apiService.userLogin(loginDataModel)

    }

//    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
}
