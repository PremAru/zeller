package com.bank.zeller.transfer.di

import androidx.lifecycle.ViewModel
import com.bank.zeller.transfer.TransferActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransferActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(transferActivityViewModel: TransferActivityViewModel): ViewModel

}