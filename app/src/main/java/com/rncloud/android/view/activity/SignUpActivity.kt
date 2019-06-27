package com.rncloud.android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.rncloud.android.R
import com.rncloud.android.databinding.ActivitySignUpBinding
import com.rncloud.android.ui.login.SignUpViewModel
import com.rncloud.android.view.activity.base.BaseActivity

class SignUpActivity : BaseActivity<SignUpViewModel,ActivitySignUpBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_up

//    override val bindingVariable: Int
//        get() = BR.viewModel

    override fun getViewModel(): Class<SignUpViewModel> {
        return SignUpViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
