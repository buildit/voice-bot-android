package com.buildit.mark.android.data.network

import io.reactivex.Observable

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface ApiHelper {
    fun getBillApiCall(): Observable<BillResponse>
}