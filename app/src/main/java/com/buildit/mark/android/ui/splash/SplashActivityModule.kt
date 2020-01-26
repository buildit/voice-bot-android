package com.buildit.mark.android.ui.splash

import com.buildit.mark.android.ui.splash.interactor.SplashInteractor
import com.buildit.mark.android.ui.splash.interactor.SplashMVPInteractor
import com.buildit.mark.android.ui.splash.presenter.SplashMVPPresenter
import com.buildit.mark.android.ui.splash.presenter.SplashPresenter
import com.buildit.mark.android.ui.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashMVPInteractor = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashMVPView, SplashMVPInteractor>)
            : SplashMVPPresenter<SplashMVPView, SplashMVPInteractor> = splashPresenter
}