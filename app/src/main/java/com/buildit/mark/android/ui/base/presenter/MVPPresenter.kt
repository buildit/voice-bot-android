package com.buildit.mark.android.ui.base.presenter

import com.buildit.mark.android.ui.base.interactor.MVPInteractor
import com.buildit.mark.android.ui.base.view.MVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}