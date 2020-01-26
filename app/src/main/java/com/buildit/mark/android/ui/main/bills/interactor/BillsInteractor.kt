package com.buildit.mark.android.ui.main.bills.interactor

import com.buildit.mark.android.data.network.ApiHelper
import com.buildit.mark.android.data.preferences.PreferenceHelper
import com.buildit.mark.android.ui.base.interactor.BaseInteractor
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class BillsInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper,
                                                   apiHelper: ApiHelper) :
        BaseInteractor(preferenceHelper, apiHelper), BillsMVPInteractor {

    override fun getBillList() = apiHelper.getBillApiCall()

}