package com.buildit.mark.android.ui.main.bills

import androidx.recyclerview.widget.LinearLayoutManager
import com.buildit.mark.android.ui.main.bills.interactor.BillsInteractor
import com.buildit.mark.android.ui.main.bills.interactor.BillsMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.BillsMVPPresenter
import com.buildit.mark.android.ui.main.bills.presenter.BillsPresenter
import com.buildit.mark.android.ui.main.bills.view.BillsAdapter
import com.buildit.mark.android.ui.main.bills.view.BillsFragment
import com.buildit.mark.android.ui.main.bills.view.BillsMVPView
import dagger.Module
import dagger.Provides
import java.util.*

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

    @Provides
    internal fun provideBillAdapter(): BillsAdapter = BillsAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: BillsFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}