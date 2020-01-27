package com.buildit.mark.android.ui.main.bills

import com.buildit.mark.android.ui.main.bills.interactor.BillsInteractor
import com.buildit.mark.android.ui.main.bills.interactor.BillsMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.BillsMVPPresenter
import com.buildit.mark.android.ui.main.bills.presenter.BillsPresenter
import com.buildit.mark.android.ui.main.bills.view.BillsMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
class BillsFragmentModule {

    @Provides
    internal fun provideBillInteractor(interactor: BillsInteractor): BillsMVPInteractor = interactor

    @Provides
    internal fun provideBillPresenter(presenter: BillsPresenter<BillsMVPView, BillsMVPInteractor>)
            : BillsMVPPresenter<BillsMVPView, BillsMVPInteractor> = presenter

}