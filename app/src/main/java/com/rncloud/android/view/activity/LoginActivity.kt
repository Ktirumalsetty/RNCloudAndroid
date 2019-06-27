package com.rncloud.android.view.activity

import android.os.Bundle
import android.view.View
import com.rncloud.android.R
import com.rncloud.android.data.model.LoginDataModel
import com.rncloud.android.databinding.ActivityLoginBinding
import com.rncloud.android.ui.login.LoginViewModel
import com.rncloud.android.view.activity.base.BaseActivity

class LoginActivity: BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_login

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<LoginViewModel> {

        return LoginViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.login.setOnClickListener(View.OnClickListener {


            viewModel.login(LoginDataModel(dataBinding.email,dataBinding.password))
        })
    }
}