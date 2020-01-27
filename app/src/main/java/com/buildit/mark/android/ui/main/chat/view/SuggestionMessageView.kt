package com.buildit.mark.android.ui.main.chat.view

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.buildit.mark.android.R
import com.buildit.mark.android.util.ScreenUtils.dpToPx
import com.mindorks.placeholderview.annotations.*

@NonReusable
@Layout(R.layout.suggestion_message_view)
class SuggestionMessageView(private val context: Context,
                            private val message: String,
                            private val isUserMessage: Boolean,
                            private val isAvatarVisible: Boolean,
                            private val suggestions: List<String>)
    : android.view.View.OnClickListener {

    override fun onClick(p0: android.view.View?) {
        if (p0 is TextView) {
            Log.d("onClick", p0.text.toString())
        }
    }

    @View(R.id.message_avatar)
    lateinit var messageAvatar: ImageView

    @View(R.id.message_container)
    lateinit var messageContainer: LinearLayout

    @View(R.id.message_text)
    lateinit var messageText: TextView

    @View(R.id.messages_suggestions_container)
    lateinit var suggestionsContainer: LinearLayout

    @JvmField
    @Position
    var position: Int = 0

    @Resolve
    public fun onResolved() {
        messageText.text = message
        messageAvatar.isVisible = isAvatarVisible
        if (isAvatarVisible) {
            var drawable = R.drawable.mark_sm
            if (isUserMessage) {
                drawable = R.drawable.ic_person_primary_24dp
            }
            messageAvatar.setImageDrawable(ContextCompat.getDrawable(context, drawable))
        } else {
            if (isUserMessage) {
                messageText.setPadding(dpToPx(10F), 0, dpToPx(70F), 0)
            } else {
                messageText.setPadding(dpToPx(70F), 0, dpToPx(10F), 0)
            }
        }
        if (isUserMessage) {
            messageContainer.layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL
            messageText.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
        }
        suggestionsContainer.removeAllViews()
        for (label in suggestions) {
            val textView = TextView(context)
            textView.text = label
            textView.setTextColor(ContextCompat.getColor(context, R.color.black_effective))
            textView.background = ContextCompat.getDrawable(context, R.drawable.chip_rounded_grey_bg)
            textView.setPadding(dpToPx(15F), dpToPx(5F), dpToPx(15F), dpToPx(5F))
            suggestionsContainer.addView(textView)
            val lp = textView.layoutParams as ViewGroup.MarginLayoutParams
            lp.setMargins(0, 0, dpToPx(10F), 0)
            textView.layoutParams = lp
            textView.setOnClickListener(this)

        }
    }
}