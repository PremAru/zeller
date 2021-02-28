package com.bank.zeller

import android.app.Application
import com.bank.zeller.BuildConfig
import com.bank.zeller.di.AppComponent
import com.bank.zeller.di.DaggerAppComponent
import timber.log.Timber

class ZellerApplication : Application() {
    val appComponent: AppComponent by lazy { initalizeAppComponent() }

    private fun initalizeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}