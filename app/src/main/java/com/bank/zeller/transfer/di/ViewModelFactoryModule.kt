package com.bank.zeller.transfer.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds

abstract class ViewModelFactoryModule {

    @Binds
    public abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}