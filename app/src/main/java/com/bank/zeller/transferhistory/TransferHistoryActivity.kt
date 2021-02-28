package com.bank.zeller.transferhistory

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bank.zeller.R
import com.bank.zeller.databinding.ActivityTransferHistoryBinding
import com.bank.zeller.ZellerApplication
import com.bank.zeller.utils.Constants
import kotlinx.android.synthetic.main.activity_transfer_history.*
import javax.inject.Inject

class TransferHistoryActivity : AppCompatActivity() {
    @Inject
    lateinit var transferHistoryAdapter: TransactionHistoryAdapter

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var transferHistoryViewModel: TransferHistoryViewModel

    private lateinit var binding: ActivityTransferHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ZellerApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transfer_history)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        val transactionHistory =
            intent.getSerializableExtra(Constants.KEY_TRANSACTION_HISTORY) as
                    ArrayList<String>

        transferHistoryViewModel.setTransactionHistoryList(transactionHistory)

        if (transferHistoryViewModel.isTransactionHistoryAvailable()) {
            noBalanceTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            transferHistoryAdapter.setTransactionList(
                transferHistoryViewModel.getTransactionHistoryList()
            )
        } else {
            noBalanceTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transferHistoryAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

}