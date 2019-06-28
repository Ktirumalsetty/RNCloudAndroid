package com.rncloud.android

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.rncloud.android.di.component.DaggerAppComponent;

class RNCloudApplication :Application(),HasActivityInjector  {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
//        AppInjector.init(this)```
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this);
    }
    override fun activityInjector(): AndroidInjector<Activity> {
            return dispatchingAndroidInjector
    }
}