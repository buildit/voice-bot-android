package com.buildit.mark.android.ui.main.bills.interactor

import android.util.Log
import com.amazonaws.mobileconnectors.lex.interactionkit.Response
import com.amazonaws.mobileconnectors.lex.interactionkit.continuations.LexServiceContinuation
import com.buildit.mark.android.data.network.ApiHelper
import com.buildit.mark.android.data.preferences.PreferenceHelper
import com.buildit.mark.android.ui.base.interactor.BaseInteractor
import java.util.*
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class ChatInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper,
                                                  apiHelper: ApiHelper) :
        BaseInteractor(preferenceHelper, apiHelper), ChatMVPInteractor {

}