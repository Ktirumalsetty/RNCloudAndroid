package com.rncloud.android.view.fragment

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {

    protected lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    inline fun <reified T> T.TAG(): String = T::class.java.simpleName
}