package com.bank.zeller.transferhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bank.zeller.R
import kotlinx.android.synthetic.main.adapter_transaction_history.view.*
import java.util.ArrayList
import javax.inject.Inject

class TransactionHistoryAdapter @Inject constructor() :
    RecyclerView.Adapter<TransactionHistoryAdapter.TransactionDetailsViewHolder>() {
    private var transactionHistory = ArrayList<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_transaction_history, parent,
            false
        )
        return TransactionDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionHistory.size
    }

    override fun onBindViewHolder(holder: TransactionDetailsViewHolder, position: Int) {
        transactionHistory.get(position).let {
            holder.bindFilter(it)
        }
    }

    inner class TransactionDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindFilter(transaction: String) {
            itemView.transaction_details.text = transaction
        }
    }

    fun setTransactionList(transactionHistory: ArrayList<String>) {
        this.transactionHistory.addAll(transactionHistory)
    }

}