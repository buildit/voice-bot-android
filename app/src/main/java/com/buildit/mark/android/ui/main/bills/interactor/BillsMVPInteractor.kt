package com.buildit.mark.android.ui.main.bills.interactor

import com.buildit.mark.android.data.network.BillResponse
import com.buildit.mark.android.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface BillsMVPInteractor : MVPInteractor {

    fun getBillList(): Observable<BillResponse>

}