package com.bank.zeller.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bank.zeller.R
import com.bank.zeller.utils.Constants.DOLLAR_SIGN
import com.bank.zeller.utils.Resource
import dagger.Module
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@Module
class TransferViewModel @Inject constructor() : ViewModel() {
    private var accountbalance: Int = 0
    private val transactionHistory = arrayListOf<String>()
    private val transactionDetails = MutableLiveData<Resource<Int>>()

    fun depositMoney(amount: String) {
        if (!amount.isEmpty()) {
            accountbalance += amount.toInt()
            transactionDetails.postValue(Resource.success(R.string.deposit_success))
        } else {
            transactionDetails.postValue(Resource.error(R.string.enter_valid_input))
        }
    }

    fun withdrawMoney(withdrawAmount: String) {
        if (!withdrawAmount.isEmpty()) {
            if (withdrawAmount.toInt() <= accountbalance) {
                accountbalance -= withdrawAmount.toInt()
                transactionDetails.postValue(Resource.success(R.string.withdraw_success))
            } else {
                transactionDetails.postValue(Resource.failure(R.string.transaction_declined))
            }
        } else {
            transactionDetails.postValue(Resource.error(R.string.enter_valid_input))
        }
    }

    fun getTransaction(): LiveData<Resource<Int>> {
        return transactionDetails;
    }

    fun addTransaction(transaction: String) {
        transactionHistory.add(transaction)
    }

    fun getTransactionHistory(): ArrayList<String> {
         Collections.reverse(transactionHistory)
        return transactionHistory
    }

    fun getAccountBalance(): String {
        return DOLLAR_SIGN + accountbalance.toString()
    }

}

