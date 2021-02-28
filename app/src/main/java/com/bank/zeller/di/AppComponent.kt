package com.bank.zeller.di

import android.content.Context
import com.bank.zeller.transfer.TransferActivity
import com.bank.zeller.transferhistory.TransferHistoryActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(transferActivity: TransferActivity)

    fun inject(transferHistoryActivity: TransferHistoryActivity)

}