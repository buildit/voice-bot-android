package com.buildit.mark.android.ui.login.presenter

import com.buildit.mark.android.ui.base.presenter.MVPPresenter
import com.buildit.mark.android.ui.login.interactor.LoginMVPInteractor
import com.buildit.mark.android.ui.login.view.LoginMVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface LoginMVPPresenter<V : LoginMVPView, I : LoginMVPInteractor> : MVPPresenter<V, I> {

}