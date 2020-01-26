package com.buildit.mark.android.ui.main.bills.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buildit.mark.android.R
import com.buildit.mark.android.data.network.Bill


/**
 * Created by harshit.laddha on 25/01/2020
 */
class BillsAdapter(private val billListItems: MutableList<Bill>) : RecyclerView.Adapter<BillsAdapter.BillViewHolder>() {

    override fun getItemCount() = this.billListItems.size

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BillViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bill_list, parent, false))

    internal fun addBlogsToList(blogs: List<Bill>) {
        this.billListItems.addAll(blogs)
        notifyDataSetChanged()
    }

    inner class BillViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {

        }

        fun onBind(position: Int) {

            inflateData(billListItems[position])
        }

        private fun inflateData(bill: Bill) {

        }
    }
}
