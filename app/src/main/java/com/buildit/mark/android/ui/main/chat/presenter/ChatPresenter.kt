package com.buildit.mark.android.ui.main.bills.presenter

import com.buildit.mark.android.ui.base.presenter.BasePresenter
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class ChatPresenter<V : ChatMVPView, I : ChatMVPInteractor> @Inject constructor(
        interactor: I, schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider,
                compositeDisposable = compositeDisposable), ChatMVPPresenter<V, I> {

    override fun onViewPrepared() {

    }
}