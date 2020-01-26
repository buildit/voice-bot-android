package com.buildit.mark.android.ui.main.chat

import com.buildit.mark.android.ui.main.chat.view.ChatFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
internal abstract class ChatFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatFragmentModule::class])
    internal abstract fun provideChatFragmentFactory(): ChatFragment
}