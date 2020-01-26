package com.buildit.mark.android.ui.main.chat.view

import android.content.Context
import android.graphics.Outline
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.google.android.material.card.MaterialCardView
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.base.view.BaseDialogView
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.ChatMVPPresenter
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import com.buildit.mark.android.util.ScreenUtils.dpToPx
import com.buildit.mark.android.util.ScreenUtils.getScreenHeight
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

    private val TAG = "ChatFragment"

    @Inject
    internal lateinit var presenter: ChatMVPPresenter<ChatMVPView, ChatMVPInteractor>

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
        presenter.onAttach(this)
        val container = view.findViewById(R.id.chat_container) as MaterialCardView
        container.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, (view.height + 40F).toInt(), 40F)
            }
        }
        container.clipToOutline = true

//        context?.let {
//            btn_chat_voice.setBackgroundColor(ContextCompat.getColor(it, R.color.red_dark))
//        }
        btn_chat_submit.setOnClickListener {
            input_text_container.isVisible = false
            input_voice_container.isVisible = true
//            btn_chat_voice.performClick()
        }
        btn_chat_close.setOnClickListener {
            dismissDialog(TAG)
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
    }

    internal fun show(fragmentManager: FragmentManager) = super.show(fragmentManager, TAG)
}