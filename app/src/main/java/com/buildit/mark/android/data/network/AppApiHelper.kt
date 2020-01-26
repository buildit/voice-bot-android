package com.buildit.mark.android.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    override fun getBillApiCall(): Observable<BillResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BILL_LIST)
                    .build()
                    .getObjectObservable(BillResponse::class.java)

}