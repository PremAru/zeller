package com.bank.zeller.transfer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bank.zeller.R
import com.bank.zeller.utils.Resource
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
class TransferViewModelTest {
    private lateinit var transferViewModel: TransferViewModel

    @Mock
    private lateinit var transactionObserver: Observer<Resource<Int>>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        transferViewModel = TransferViewModel()
    }

    @Test
    fun depositMoneySuccess() {
        transferViewModel.getTransaction().observeForever(transactionObserver)

        transferViewModel.depositMoney("100")
        then(verify(transactionObserver).onChanged(Resource.success(R.string.deposit_success)))
        assertEquals("$ 100", transferViewModel.getAccountBalance())
        transferViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun depositMoneyError_not_validInput(){
        transferViewModel.getTransaction().observeForever(transactionObserver)
        transferViewModel.depositMoney("")
        then(verify(transactionObserver).onChanged(Resource.error(R.string.enter_valid_input)))
    }

    @Test
    fun withDrawMoneySuccess() {
        transferViewModel.getTransaction().observeForever(transactionObserver)

        transferViewModel.depositMoney("100")
        transferViewModel.getTransaction().observeForever(transactionObserver)
        assertEquals("$ 100", transferViewModel.getAccountBalance())

        transferViewModel.withdrawMoney("40")
        then(verify(transactionObserver).onChanged(Resource.success(R.string.withdraw_success)))
        assertEquals("$ 60", transferViewModel.getAccountBalance())
        transferViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun withDrawMoneyFailure_declined_inSufficientFunds() {
        transferViewModel.getTransaction().observeForever(transactionObserver)

        transferViewModel.depositMoney("100")
        transferViewModel.getTransaction().observeForever(transactionObserver)
        assertEquals("$ 100", transferViewModel.getAccountBalance())

        transferViewModel.withdrawMoney("150")
        then(verify(transactionObserver).onChanged(Resource.failure(R.string.transaction_declined)))
        assertEquals("$ 100", transferViewModel.getAccountBalance())
        transferViewModel.getTransaction().removeObserver(transactionObserver)
    }

    @Test
    fun withDrawMoneyError_not_validInput(){
        transferViewModel.getTransaction().observeForever(transactionObserver)
        transferViewModel.withdrawMoney("")
        then(verify(transactionObserver).onChanged(Resource.error(R.string.enter_valid_input)))
    }

    @Test
    fun getAccountBalance_withDollar(){
        assertEquals("$ 0",transferViewModel.getAccountBalance())
    }


}