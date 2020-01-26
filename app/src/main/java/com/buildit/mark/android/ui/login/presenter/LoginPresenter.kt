package com.buildit.mark.android.ui.login.presenter

import com.buildit.mark.android.ui.base.presenter.BasePresenter
import com.buildit.mark.android.ui.login.interactor.LoginMVPInteractor
import com.buildit.mark.android.ui.login.view.LoginMVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class LoginPresenter<V : LoginMVPView, I : LoginMVPInteractor> @Inject internal constructor(
        interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider,
                compositeDisposable = disposable), LoginMVPPresenter<V, I> {

}