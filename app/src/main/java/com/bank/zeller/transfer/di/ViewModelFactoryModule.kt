package com.bank.zeller.transfer.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    public abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}