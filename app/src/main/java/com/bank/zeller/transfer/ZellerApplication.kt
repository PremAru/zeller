package com.bank.zeller.transfer

import android.app.Application
import com.bank.zeller.transfer.di.AppComponent

class ZellerApplication  : Application() {
    val appComponent: AppComponent by lazy { initalizeAppComponent() }

    private fun initalizeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}