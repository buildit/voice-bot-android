package com.buildit.mark.android.ui.main.bills.presenter

import com.buildit.mark.android.ui.base.presenter.BasePresenter
import com.buildit.mark.android.ui.main.bills.interactor.BillsMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.BillsMVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class BillsPresenter<V : BillsMVPView, I : BillsMVPInteractor> @Inject constructor(
        interactor: I, schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider,
                compositeDisposable = compositeDisposable), BillsMVPPresenter<V, I> {

    override fun onViewPrepared() {
//        getView()?.showProgress()
//        interactor?.let {
//            it.getBillList()
//                .compose(schedulerProvider.ioToMainObservableScheduler())
//                .subscribe { billResponse ->
//                    getView()?.let { it ->
//                        it.hideProgress()
//                        it.displayBillList(billResponse.data)
//                    }
//                }
//        }
    }
}