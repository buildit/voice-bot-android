package com.buildit.mark.android.ui.splash.presenter

import com.buildit.mark.android.ui.base.presenter.BasePresenter
import com.buildit.mark.android.ui.splash.interactor.SplashMVPInteractor
import com.buildit.mark.android.ui.splash.view.SplashMVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class SplashPresenter<V : SplashMVPView, I : SplashMVPInteractor> @Inject internal constructor(
        interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider,
                compositeDisposable = disposable), SplashMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        decideActivityToOpen()
    }

    private fun decideActivityToOpen() = getView()?.let {
        it.openMainActivity()
    }

}