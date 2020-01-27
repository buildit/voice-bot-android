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
                      private val response: BillsResponse) {

    @View(R.id.bill_list_container)
    lateinit var billListContainer: LinearLayout

    @View(R.id.bill_list_count)
    lateinit var billCount: TextView

    @View(R.id.bill_list_placeholder)
    lateinit var billListPlaceholder: PlaceHolderView

    @JvmField
    @Position
    var position: Int = 0

    @SuppressLint("SetTextI18n")
    @Resolve
    public fun onResolved() {
        billCount.text = "${response.bills.count()} bills"
        billListPlaceholder.builder
                .setHasFixedSize(true)
                .setItemViewCacheSize(10)
                .setLayoutManager(LinearLayoutManager(
                        context, LinearLayoutManager.VERTICAL, false))
        billListPlaceholder.removeAllViews()
        for (bill in response.bills) {
            billListPlaceholder.addView(BillItemMessageView(context, bill))
        }
    }
}