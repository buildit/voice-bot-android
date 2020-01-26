package com.buildit.mark.android.ui.main.chat.view

import android.content.Context
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.lex.interactionkit.InteractionClient
import com.amazonaws.mobileconnectors.lex.interactionkit.config.InteractionConfig
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView
import com.amazonaws.regions.Regions
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.base.view.BaseDialogView
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.ChatMVPPresenter
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import com.buildit.mark.android.util.ScreenUtils.dpToPx
import com.buildit.mark.android.util.ScreenUtils.getScreenHeight
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject


/**
 * Created by harshit.laddha on 25/01/2020
 */
class ChatFragment : BaseDialogView(), ChatMVPView {

    companion object {

        fun newInstance(): ChatFragment {
            return ChatFragment()
        }
    }

    private lateinit var inputTextMessage: String
    private lateinit var voiceInputContainer: MaterialCardView
    private lateinit var textInputContainer: MaterialCardView
    private lateinit var chatContainer: MaterialCardView
    private lateinit var btnChatClose: ImageView
    private lateinit var btnChatSubmit: ImageView
    private lateinit var userTextInput: TextInputEditText
    private lateinit var lexInteractionClient: InteractionClient
    private lateinit var voiceBtn: InteractiveVoiceView
    private lateinit var dataCallback: ChatFragmentDataCallback
    private val TAG = "chatFragment"

    @Inject
    internal lateinit var presenter: ChatMVPPresenter<ChatMVPView, ChatMVPInteractor>
    private lateinit var interactionConfig: InteractionConfig
    private lateinit var awsMobileClient: AWSMobileClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogSlideAnim)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        clipChatContainerEdges()
        setLexConfig()
        setupListeners()
        presenter.onAttach(this)
        presenter.onViewPrepared(voiceBtn, lexInteractionClient)
    }

    private fun initView() {
        voiceBtn = btn_chat_voice as InteractiveVoiceView
        userTextInput = input_chat_message
        btnChatSubmit = btn_chat_submit
        btnChatClose = btn_chat_close
        chatContainer = chat_container
        textInputContainer = input_text_container
        voiceInputContainer = input_voice_container
    }

    private fun clipChatContainerEdges() {
        chatContainer.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, (view.height + 40F).toInt(), 40F)
            }
        }
        chatContainer.clipToOutline = true
    }

    private fun setupListeners() {
        btnChatSubmit.setOnClickListener {
            inputTextMessage = userTextInput.text.toString()
            if (inputTextMessage.isNotEmpty()) {
                presenter.submitTextMessage(inputTextMessage)
            } else {
                toggleInputMode(false)
                toggleBtnMode(false)
                voiceBtn.performClick()
            }
        }
        btnChatClose.setOnClickListener {
            dismissDialog(TAG)
        }
        userTextInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            inputTextMessage = (v as TextInputEditText).text.toString()
            updateInputState(inputTextMessage)
            if (inputTextMessage.isNotEmpty() && event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.submitTextMessage(inputTextMessage)
                return@OnKeyListener true
            }
            false
        })
        userTextInput.doOnTextChanged { text, start, count, after ->
            inputTextMessage = userTextInput.text.toString()
            updateInputState(inputTextMessage)
        }
    }

    private fun updateInputState(inputTextMessage: String) {
        var drawable = R.drawable.ic_mic_white_24dp
        if (inputTextMessage.isNotEmpty()) {
            drawable = R.drawable.ic_send_white_24dp
        }
        context?.let {
            btnChatSubmit.setImageDrawable(ContextCompat.getDrawable(it, drawable))
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()
        context?.let {
            dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    getScreenHeight(it) - dpToPx(120F))
        }
        val attrs = dialog?.window?.attributes
        attrs?.y = 120
        dialog?.window?.attributes = attrs
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataCallback = context as ChatFragmentDataCallback
    }

    internal fun show(fragmentManager: FragmentManager) = super.show(fragmentManager, TAG)

    private fun setLexConfig() {
        interactionConfig = dataCallback.getInteractionConfig()
        awsMobileClient = dataCallback.getAWSMobileClient()
        lexInteractionClient = InteractionClient(context,
                AWSMobileClient.getInstance(),
                Regions.fromName(dataCallback.getAWSRegion()),
                interactionConfig)
        voiceBtn.viewAdapter.setCredentialProvider(awsMobileClient)
        voiceBtn.viewAdapter.setInteractionConfig(interactionConfig)
        voiceBtn.viewAdapter.awsRegion = dataCallback.getAWSRegion()
        voiceBtn.isEnabled = false
        voiceBtn.invalidate()
    }

    override fun toggleBtnMode(isTextEnabled: Boolean) {
        userTextInput.isEnabled = isTextEnabled
        voiceBtn.isEnabled = !isTextEnabled
    }

    override fun toggleInputMode(isTextEnabled: Boolean) {
        textInputContainer.isVisible = isTextEnabled
        voiceInputContainer.isVisible = !isTextEnabled
    }
}