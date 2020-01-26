package com.buildit.mark.android.ui.main.bills.view

import com.buildit.mark.android.data.network.Bill
import com.buildit.mark.android.ui.base.view.MVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface BillsMVPView : MVPView {

    fun displayBillList(bills: List<Bill>?) : Unit?

}