package com.buildit.mark.android.ui.main.chat.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildit.mark.android.R
import com.buildit.mark.android.data.database.repository.bills.BillsResponse
import com.mindorks.placeholderview.PlaceHolderView
import com.mindorks.placeholderview.annotations.*

@NonReusable
@Layout(R.layout.bill_list_message_view)
class BillListMessageView(private val context: Context,
                          private val response: BillsResponse,
                          private val dataCallback: BillItemSelectionListener): BillItemSelectionListener {

    @View(R.id.bill_list_container)
    lateinit var billListContainer: LinearLayout

    @View(R.id.bill_list_count)
    lateinit var billCount: TextView

    @View(R.id.bill_list_placeholder)
    lateinit var billListPlaceholder: PlaceHolderView

    @JvmField
    @Position
    var position: Int = 0

    @Resolve
    public fun onResolved() {
        billListPlaceholder.builder
                .setHasFixedSize(true)
                .setItemViewCacheSize(10)
                .setLayoutManager(LinearLayoutManager(
                        context, LinearLayoutManager.VERTICAL, false))
        billListPlaceholder.removeAllViews()
        for ((index, value) in response.bills.withIndex()) {
            value.isSelected = true
            billListPlaceholder.addView(BillItemMessageView(context,
                    value, index, this))
        }
        updateSelectedCount()
    }

    override fun onSelectBill(position: Int, isSelected: Boolean) {
        response.bills[position].isSelected = isSelected
        dataCallback.onSelectBill(position, isSelected)
        updateSelectedCount()
    }

    @SuppressLint("SetTextI18n")
    private fun updateSelectedCount() {
        var count = 0
        for (bill in response.bills) {
            if (bill.isSelected) count += 1
        }
        billCount.text = "$count bills selected"
    }

}