package com.buildit.mark.android.ui.main.chat.view

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.buildit.mark.android.R
import com.buildit.mark.android.util.ScreenUtils.dpToPx
import com.mindorks.placeholderview.annotations.*

@NonReusable
@Layout(R.layout.text_message_view)
class TextMessageView(private val context: Context,
                      private val message: String,
                      private val isUserMessage: Boolean,
                      private val isAvatarVisible: Boolean) {

    @View(R.id.message_avatar)
    lateinit var messageAvatar: ImageView

    @View(R.id.message_container)
    lateinit var messageContainer: LinearLayout

    @View(R.id.message_text)
    lateinit var messageText: TextView

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
                messageText.setTypeface(messageText.typeface, Typeface.NORMAL)
            } else {
                messageText.setTypeface(messageText.typeface, Typeface.BOLD)
            }
            messageAvatar.setImageDrawable(ContextCompat.getDrawable(context, drawable))
        } else {
            if (isUserMessage) {
                messageText.setPadding(dpToPx(10F), 0, dpToPx(70F), 0)
            } else {
                messageText.setPadding(dpToPx(70F), 0,dpToPx(10F), 0)
            }
        }
        if (isUserMessage) {
            messageContainer.layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL
            messageText.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            if (isAvatarVisible) messageAvatar.scaleType = ImageView.ScaleType.FIT_END
        }
    }
}