package com.bank.zeller.transfer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bank.zeller.R
import com.bank.zeller.databinding.ActivityMainBinding
import com.bank.zeller.transfer.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TransferActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var mainActivityViewModel: TransferActivityViewModel
    private var amount = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ZellerApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this, viewModelFactory)
            .get(TransferActivityViewModel::class.java)
        updateAccountBalance()

        submit.setOnClickListener {

        }

        depositButton.setOnClickListener {
            amount = enterDepositEditText.text.toString()
            mainActivityViewModel.depositMoney(amount)
        }

        withdrawButton.setOnClickListener {
            amount = enterWithdrawEditText.text.toString()
            mainActivityViewModel.withdrawMoney(amount)

        }

        mainActivityViewModel.getTransaction().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAccountBalance()
                    val status = it.data?.let { resourceId -> resources.getString(resourceId, amount) }
                    status?.let { it1 -> mainActivityViewModel.addTransaction(it1) }
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
                Status.FAILURE -> {
                    val status = it.data?.let { resourceId -> resources.getString(resourceId, amount) }
                    status?.let { it1 -> mainActivityViewModel.addTransaction(it1) }
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
        binding.availableBalance =  mainActivityViewModel.getAccountBalance()
    }
}