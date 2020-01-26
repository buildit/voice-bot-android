package com.buildit.mark.android.ui.splash.interactor

import android.content.Context
import com.buildit.mark.android.data.network.ApiHelper
import com.buildit.mark.android.data.preferences.PreferenceHelper
import com.buildit.mark.android.ui.base.interactor.BaseInteractor
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class SplashInteractor @Inject constructor(private val mContext: Context,
                                           preferenceHelper: PreferenceHelper,
                                           apiHelper: ApiHelper) :
        BaseInteractor(preferenceHelper, apiHelper), SplashMVPInteractor {

}