package com.bank.zeller.transferhistory

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TransferHistoryViewModelTest {
    private lateinit var transferHistoryViewModel: TransferHistoryViewModel

    @Before
    fun setUp() {
        transferHistoryViewModel = TransferHistoryViewModel()
    }

    @Test
    fun shouldSetDataInTransactionList() {
        var transactionHistoryList = arrayListOf<String>()
        transactionHistoryList.add("Deposit $100")
        transferHistoryViewModel.setTransactionHistoryList(transactionHistoryList)
        Assert.assertEquals(
            transactionHistoryList,
            transferHistoryViewModel.getTransactionHistoryList()
        )
    }

    @Test
    fun shouldReturnTrueIfTransactionListGreaterThanZero() {
        var transactionHistoryList = arrayListOf<String>()
        transactionHistoryList.add("Deposit $100")
        transferHistoryViewModel.setTransactionHistoryList(transactionHistoryList)
        val isTransactionHistoryAvailable = transferHistoryViewModel.isTransactionHistoryAvailable()
        Assert.assertEquals(true, isTransactionHistoryAvailable)
    }

    @Test
    fun shouldReturnFalseIfTransactionListEqualsZero() {
        var transactionHistoryList = arrayListOf<String>()
        transferHistoryViewModel.setTransactionHistoryList(transactionHistoryList)
        val isTransactionHistoryAvailable = transferHistoryViewModel.isTransactionHistoryAvailable()
        Assert.assertEquals(false, isTransactionHistoryAvailable)
    }

}