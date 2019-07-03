package com.rncloud.android.view.activity.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes

abstract class BaseAppCompatActivity<DB:ViewDataBinding>:AppCompatActivity() {

     protected var TAG: String? = null

//    protected abstract val layoutRes: Int

    @LayoutRes
    abstract fun getLayoutRes(): Int

    protected lateinit var binding:DB


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        TAG = BaseAppCompatActivity::class.qualifiedName

//        binding = DataBindingUtil.setContentView(this, getLayoutRes())
//        binding.setLifecycleOwner(this)
    }

    inline fun <reified T> T.TAG(): String = T::class.java.simpleName

    protected fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }


}