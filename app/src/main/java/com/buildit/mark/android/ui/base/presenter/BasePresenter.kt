package com.buildit.mark.android.ui.base.presenter

import com.buildit.mark.android.ui.base.interactor.MVPInteractor
import com.buildit.mark.android.ui.base.view.MVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by harshit.laddha on 25/01/2020
 */
abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}