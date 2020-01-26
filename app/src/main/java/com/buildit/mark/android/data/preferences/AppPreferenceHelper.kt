package com.buildit.mark.android.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.buildit.mark.android.di.PreferenceInfo
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class AppPreferenceHelper @Inject constructor(context: Context,
                                              @PreferenceInfo private val prefFileName: String) :
        PreferenceHelper {
    companion object {

    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

}