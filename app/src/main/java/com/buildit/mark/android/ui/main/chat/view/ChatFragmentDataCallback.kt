package com.buildit.mark.android.ui.main.chat.view

import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.lex.interactionkit.config.InteractionConfig

interface ChatFragmentDataCallback {
    fun getInteractionConfig(): InteractionConfig
    fun getAWSMobileClient(): AWSMobileClient
    fun getAWSRegion(): String
}
