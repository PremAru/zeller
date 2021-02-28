package com.bank.zeller.transfer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bank.zeller.R
import com.bank.zeller.ZellerApplication
import com.bank.zeller.databinding.ActivityMainBinding
import com.bank.zeller.transferhistory.TransferHistoryActivity
import com.bank.zeller.utils.Constants.KEY_TRANSACTION_HISTORY
import com.bank.zeller.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TransferActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var transferViewModel: TransferViewModel
    private var amount = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ZellerApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        transferViewModel = ViewModelProvider(this, viewModelFactory)
            .get(TransferViewModel::class.java)
        updateAccountBalance()

        submit.setOnClickListener {
            val transferHistoryIntent = Intent(this, TransferHistoryActivity::class.java)
            transferHistoryIntent.putExtra(KEY_TRANSACTION_HISTORY, transferViewModel.getTransactionHistory())
            startActivity(transferHistoryIntent)
        }

        depositButton.setOnClickListener {
            amount = enterDepositEditText.text.toString()
            transferViewModel.depositMoney(amount)
        }

        withdrawButton.setOnClickListener {
            amount = enterWithdrawEditText.text.toString()
            transferViewModel.withdrawMoney(amount)

        }

        transferViewModel.getTransaction().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAccountBalance()
                    val status = it.data?.let { resourceId -> resources.getString(resourceId, amount) }
                    status?.let { it1 -> transferViewModel.addTransaction(it1) }
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
                Status.FAILURE -> {
                    val status = it.data?.let { resourceId -> resources.getString(resourceId, amount) }
                    status?.let { it1 -> transferViewModel.addTransaction(it1) }
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    val status = it.data?.let { resourceId -> resources.getString(resourceId, amount) }
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun updateAccountBalance() {
        binding.availableBalance =  transferViewModel.getAccountBalance()
    }
}