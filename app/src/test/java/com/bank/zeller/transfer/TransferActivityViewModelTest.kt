package com.bank.zeller.transfer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bank.zeller.R
import com.bank.zeller.transfer.utils.Resource
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransferActivityViewModelTest {
    private lateinit var transferActivityViewModel: TransferActivityViewModel

    @Mock
    private lateinit var transactionObserver: Observer<Resource<Int>>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        transferActivityViewModel = TransferActivityViewModel()
    }

    @Test
    fun depositMoneySuccess() {
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)

        transferActivityViewModel.depositMoney("100")
        then(verify(transactionObserver).onChanged(Resource.success(R.string.deposit_success)))
        assertEquals("$ 100", transferActivityViewModel.getAccountBalance())
        transferActivityViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun depositMoneyError_not_validInput(){
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)
        transferActivityViewModel.depositMoney("")
        then(verify(transactionObserver).onChanged(Resource.error(R.string.enter_valid_input)))
    }

    @Test
    fun withDrawMoneySuccess() {
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)

        transferActivityViewModel.depositMoney("100")
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)
        assertEquals("$ 100", transferActivityViewModel.getAccountBalance())

        transferActivityViewModel.withdrawMoney("40")
        then(verify(transactionObserver).onChanged(Resource.success(R.string.withdraw_success)))
        assertEquals("$ 60", transferActivityViewModel.getAccountBalance())
        transferActivityViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun withDrawMoneyFailure_declined_inSufficientFunds() {
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)

        transferActivityViewModel.depositMoney("100")
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)
        assertEquals("$ 100", transferActivityViewModel.getAccountBalance())

        transferActivityViewModel.withdrawMoney("150")
        then(verify(transactionObserver).onChanged(Resource.failure(R.string.transaction_declined)))
        assertEquals("$ 100", transferActivityViewModel.getAccountBalance())
        transferActivityViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun withDrawMoneyError_not_validInput(){
        transferActivityViewModel.getTransaction().observeForever(transactionObserver)
        transferActivityViewModel.withdrawMoney("")
        then(verify(transactionObserver).onChanged(Resource.error(R.string.enter_valid_input)))
    }

    @Test
    fun getAccountBalance_withDollar(){
        assertEquals("$ 0",transferActivityViewModel.getAccountBalance())
    }


}