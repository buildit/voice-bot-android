package com.buildit.mark.android.ui.main.chat.view

import android.app.Activity
import android.content.Context
import android.graphics.Outline
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.lex.interactionkit.InteractionClient
import com.amazonaws.mobileconnectors.lex.interactionkit.Response
import com.amazonaws.mobileconnectors.lex.interactionkit.config.InteractionConfig
import com.amazonaws.mobileconnectors.lex.interactionkit.continuations.LexServiceContinuation
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView
import com.amazonaws.regions.Regions
import com.buildit.mark.android.R
import com.buildit.mark.android.data.database.repository.bills.BillsResponse
import com.buildit.mark.android.ui.base.view.BaseDialogView
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.ChatMVPPresenter
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import com.buildit.mark.android.util.FileUtils.loadJSONFromAsset
import com.buildit.mark.android.util.ScreenUtils.dpToPx
import com.buildit.mark.android.util.ScreenUtils.getScreenHeight
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import com.mindorks.placeholderview.PlaceHolderView
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

    private var lexServiceContinuation: LexServiceContinuation? = null
    private lateinit var messagesListPlaceholder: PlaceHolderView
    private lateinit var chatScrollContainer: ScrollView
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
        setupPlaceholderView()
        setLexConfig()
        setupListeners()
        presenter.onAttach(this)
        presenter.onViewPrepared(voiceBtn, lexInteractionClient)
    }

    private fun setupPlaceholderView() {
        context?. let {

            messagesListPlaceholder.builder
                    .setHasFixedSize(false)
                    .setItemViewCacheSize(10)
                    .setLayoutManager(LinearLayoutManager(context,
                            LinearLayoutManager.VERTICAL, false))
//            messagesListPlaceholder.addView(TextMessageView(it, "Great, let's started! " +
//                    "The best way to avoid late fees is to autopay recurring bills.",
//                    isUserMessage = false, isAvatarVisible = true))
//            messagesListPlaceholder.addView(TextMessageView(it, "Based on your transaction" +
//                    " history, I found these bills that I can autopay for you.",
//                    isUserMessage = false, isAvatarVisible = false))
//            messagesListPlaceholder.addView(BillListMessageView(it, response))
//            messagesListPlaceholder.addView(SuggestionMessageView(it,
//                    "Do you want me to set up autopay for these bills?",
//                    isUserMessage = false,
//                    isAvatarVisible = false,
//                    suggestions = response.suggestedActions
//                ))
        }
    }

    private fun initView() {
        voiceBtn = btn_chat_voice as InteractiveVoiceView
        userTextInput = input_chat_message
        btnChatSubmit = btn_chat_submit
        btnChatClose = btn_chat_close
        chatContainer = chat_container
        textInputContainer = input_text_container
        voiceInputContainer = input_voice_container
        chatScrollContainer = messages_scroll_container
        messagesListPlaceholder = messages_list_view
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
                presenter.submitTextMessage(inputTextMessage, lexServiceContinuation)
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
                presenter.submitTextMessage(inputTextMessage, lexServiceContinuation)
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

    override fun setLexContinuation(continuation: LexServiceContinuation?) {
        lexServiceContinuation = continuation
    }

    override fun handleLexResponse(response: Response?) {
        response?.textResponse?. let {
            context?.let {it1 ->
                messagesListPlaceholder.addView(TextMessageView(it1, it,
                        isUserMessage = false, isAvatarVisible = true))
                if (response.intentName.equals("billpayment", true) &&
                        response.slotToIllicit.equals("billtype", true)) {
                    addBillListView(it1)
                } else {
                    scrollToBottom()
                }
            }
        }
    }

    private fun addBillListView(context: Context) {
        val bills: String = loadJSONFromAsset(context, "bills.json")
        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()
        val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                null, BillsResponse::class.java)
        val response = gson.fromJson<BillsResponse>(bills, type)
        messagesListPlaceholder.addView(BillListMessageView(context, response))
        scrollToBottom()
    }

    private fun scrollToBottom() {
        //TODO: fix scrolling chat container to bottom when new elements have been added
        chatScrollContainer.postDelayed({
            chatScrollContainer.scrollTo(0, chatScrollContainer.bottom + 250)
        }, 1000)
    }

    override fun clearTextInput() {
        userTextInput.setText("")
        context?. let {
            val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun handlerUserResponse(response: String?) {
        response?. let {
            context?. let {it1 ->
                messagesListPlaceholder.addView(TextMessageView(it1, it,
                        isUserMessage = true, isAvatarVisible = true))
                scrollToBottom()
            }
        }
    }
}