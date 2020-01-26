package com.buildit.mark.android.ui.main.bills.view

import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.AudioPlaybackListener
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.InteractionListener
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.MicrophoneListener
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView
import com.buildit.mark.android.ui.base.view.MVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface ChatMVPView : MVPView {
    fun toggleBtnMode(isTextEnabled: Boolean)
    fun toggleInputMode(isTextEnabled: Boolean)
}