package com.buildit.mark.android.ui.main.bills.presenter

import android.content.Context
import com.amazonaws.mobileconnectors.lex.interactionkit.InteractionClient
import com.amazonaws.mobileconnectors.lex.interactionkit.continuations.LexServiceContinuation
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.AudioPlaybackListener
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.InteractionListener
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.MicrophoneListener
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView
import com.buildit.mark.android.ui.base.presenter.MVPPresenter
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface ChatMVPPresenter<V : ChatMVPView, I : ChatMVPInteractor> : MVPPresenter<V, I>,
        InteractiveVoiceView.InteractiveVoiceListener,
        AudioPlaybackListener, InteractionListener, MicrophoneListener {

    fun onViewPrepared(context: Context, voiceBtn: InteractiveVoiceView,
                       lexInteractionClient: InteractionClient)
    fun submitTextMessage(inputTextMessage: String,
                          lexServiceContinuation: LexServiceContinuation?,
                          hideMessageFromWindow: Boolean)

    fun updateInteractionClient(lexInteractionClient: InteractionClient)
}