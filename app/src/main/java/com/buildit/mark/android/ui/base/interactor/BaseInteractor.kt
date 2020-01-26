package com.buildit.mark.android.ui.base.interactor

import com.buildit.mark.android.data.network.ApiHelper
import com.buildit.mark.android.data.preferences.PreferenceHelper

/**
 * Created by harshit.laddha on 25/01/2020
 */
open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelper = apiHelper
    }

}