package com.bank.zeller.transferhistory

import androidx.lifecycle.ViewModel
import javax.inject.Inject


class TransferHistoryViewModel @Inject constructor(): ViewModel() {
    private var transactionHistoryList = arrayListOf<String>()
    fun setTransactionHistoryList(transactionHistoryList: ArrayList<String>) {
        this.transactionHistoryList = transactionHistoryList
    }

    fun getTransactionHistoryList(): ArrayList<String> {
        return transactionHistoryList
    }

    fun isTransactionHistoryAvailable(): Boolean {
        when {
            transactionHistoryList.size == 0 -> {
                return false
            }
            else -> {
                return true
            }
        }
    }
}
