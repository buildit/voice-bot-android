package com.buildit.mark.android.ui.login

import com.buildit.mark.android.ui.login.interactor.LoginInteractor
import com.buildit.mark.android.ui.login.interactor.LoginMVPInteractor
import com.buildit.mark.android.ui.login.presenter.LoginMVPPresenter
import com.buildit.mark.android.ui.login.presenter.LoginPresenter
import com.buildit.mark.android.ui.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
class LoginActivityModule {

    @Provides
    internal fun provideLoginInteractor(interactor: LoginInteractor): LoginMVPInteractor = interactor

    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenter<LoginMVPView, LoginMVPInteractor>)
            : LoginMVPPresenter<LoginMVPView, LoginMVPInteractor> = presenter

}