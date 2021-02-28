package com.bank.zeller.di

import androidx.lifecycle.ViewModel
import com.bank.zeller.transfer.TransferViewModel
import com.bank.zeller.transferhistory.TransferHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransferViewModel::class)
    internal abstract fun bindTransferViewModel(transferViewModel: TransferViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransferHistoryViewModel::class)
    internal abstract fun bindTransferHistoryViewModel(
        transferHistoryViewModel: TransferHistoryViewModel
    ): ViewModel

}