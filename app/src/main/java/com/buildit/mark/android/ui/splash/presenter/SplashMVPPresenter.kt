package com.buildit.mark.android.ui.splash.presenter

import com.buildit.mark.android.ui.base.presenter.MVPPresenter
import com.buildit.mark.android.ui.splash.interactor.SplashMVPInteractor
import com.buildit.mark.android.ui.splash.view.SplashMVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface SplashMVPPresenter<V : SplashMVPView, I : SplashMVPInteractor> : MVPPresenter<V,I>