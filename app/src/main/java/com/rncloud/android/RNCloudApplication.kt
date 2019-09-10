package com.rncloud.android

import android.app.Activity
import android.app.Application
import android.content.Context
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.common.ResourceProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RNCloudApplication :Application()  {


    companion object {
        lateinit var context: Context
        //         lateinit var prefs: AppPrefs
        lateinit var mResourceProvider: ResourceProvider
    }

//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        context = this
        AppPreferences.init(this)
    }
//    override fun activityInjector(): AndroidInjector<Activity> {
//            return dispatchingAndroidInjector
//    }
}