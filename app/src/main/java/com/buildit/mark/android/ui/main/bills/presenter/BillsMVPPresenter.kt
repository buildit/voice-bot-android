package com.buildit.mark.android.ui.main.bills.presenter

import com.buildit.mark.android.ui.base.presenter.MVPPresenter
import com.buildit.mark.android.ui.main.bills.interactor.BillsMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.BillsMVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface BillsMVPPresenter<V : BillsMVPView, I : BillsMVPInteractor> :
        MVPPresenter<V, I> {

    fun onViewPrepared()
}