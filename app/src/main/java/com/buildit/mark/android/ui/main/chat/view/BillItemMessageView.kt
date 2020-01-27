package com.buildit.mark.android.ui.main.chat.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.buildit.mark.android.R
import com.buildit.mark.android.data.database.repository.bills.BillItem
import com.buildit.mark.android.util.extension.loadCircularImage
import com.mindorks.placeholderview.annotations.*

@NonReusable
@Layout(R.layout.bill_message_item_view)
class BillItemMessageView(private val context: Context,
                          private val bill: BillItem,
                          private val index: Int,
                          private val selectionListener: BillItemSelectionListener) {

    @View(R.id.bill_item_img)
    lateinit var billItemImg: ImageView

    @View(R.id.bill_item_title)
    lateinit var billItemTitle: TextView

    @View(R.id.bill_item_amount)
    lateinit var billItemAmount: TextView

    @View(R.id.bill_item_subtitle)
    lateinit var billItemSubtitle: TextView

    @View(R.id.bill_item_content)
    lateinit var billItemContent: RelativeLayout

    @JvmField
    @Position
    var position: Int = 0

    @SuppressLint("SetTextI18n")
    @Resolve
    public fun onResolved() {
        billItemImg.loadCircularImage(bill.billImage, 40)
//        billItemImg.loadImage(bill.billImage)
        billItemTitle.text = bill.billName
        billItemSubtitle.text = bill.subtitle
        billItemAmount.text = bill.billAmount
        toggleSelectionStyles()
    }

    @Click(R.id.bill_item_container)
    fun onClickContainer() {
        bill.isSelected = !bill.isSelected
        toggleSelectionStyles()
        selectionListener.onSelectBill(index, bill.isSelected)
    }

    private fun toggleSelectionStyles() {
        var drawable = R.drawable.input_rounded_white_bg
        if (bill.isSelected) {
            drawable = R.drawable.input_rounded_selected_bg
        }
        billItemContent.background = ContextCompat.getDrawable(context, drawable)
    }
}