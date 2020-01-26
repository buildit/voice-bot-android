package com.buildit.mark.android.ui.main.bills.presenter

import android.util.Log
import com.amazonaws.mobileconnectors.lex.interactionkit.InteractionClient
import com.amazonaws.mobileconnectors.lex.interactionkit.Response
import com.amazonaws.mobileconnectors.lex.interactionkit.continuations.LexServiceContinuation
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView
import com.buildit.mark.android.ui.base.presenter.BasePresenter
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import com.buildit.mark.android.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class ChatPresenter<V : ChatMVPView, I : ChatMVPInteractor> @Inject constructor(
        interactor: I, schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider,
                compositeDisposable = compositeDisposable), ChatMVPPresenter<V, I> {

    private val TAG = "chatPresenter"

    override fun submitTextMessage(inputTextMessage: String) {

    }

    override fun onViewPrepared(voiceBtn: InteractiveVoiceView,
                                lexInteractionClient: InteractionClient) {
        voiceBtn.setInteractiveVoiceListener(this)
        lexInteractionClient.setAudioPlaybackListener(this)
        lexInteractionClient.setInteractionListener(this)
        lexInteractionClient.setMicrophoneListener(this)
    }

    override fun dialogReadyForFulfillment(slots: MutableMap<String, String>?, intent: String?) {
        Log.d(TAG, String.format(
                Locale.US,
                "Dialog ready for fulfillment:\n\tIntent: %s\n\tSlots: %s",
                intent,
                slots.toString()))
    }

    override fun onResponse(response: Response?) {
        Log.d(TAG, "Bot response: " + response?.textResponse)
        Log.d(TAG, "Transcript: " + response?.inputTranscript)
    }

    override fun onError(responseText: String?, e: Exception?) {
        Log.e(TAG, "Error: $responseText", e)
    }

    override fun promptUserToRespond(response: Response?, continuation: LexServiceContinuation?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReadyForFulfillment(response: Response?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInteractionError(response: Response?, e: java.lang.Exception?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioPlaybackError(e: java.lang.Exception?) {
        Log.e(TAG, "Error:", e)
    }

    override fun onAudioPlayBackCompleted() {
        Log.e(TAG, "onAudioPlayBackCompleted")
    }

    override fun onAudioPlaybackStarted() {
        Log.e(TAG, "onAudioPlaybackStarted")
    }

    override fun readyForRecording() {
        Log.e(TAG, "readyForRecording")
    }

    override fun onRecordingEnd() {
        Log.e(TAG, "onRecordingEnd")
    }

    override fun startedRecording() {
        Log.e(TAG, "startedRecording")
    }

    override fun onMicrophoneError(e: java.lang.Exception?) {
        Log.e(TAG, "onMicrophoneError", e)
    }

    override fun onSoundLevelChanged(soundLevel: Double) {
        Log.e(TAG, "onSoundLevelChanged")
    }

}