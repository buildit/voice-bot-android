package com.buildit.mark.android.ui.main.chat

import com.buildit.mark.android.ui.main.bills.interactor.ChatInteractor
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.ChatMVPPresenter
import com.buildit.mark.android.ui.main.bills.presenter.ChatPresenter
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
class ChatFragmentModule {

    @Provides
    internal fun provideChatInteractor(interactor: ChatInteractor): ChatMVPInteractor = interactor

    @Provides
    internal fun provideChatPresenter(presenter: ChatPresenter<ChatMVPView, ChatMVPInteractor>)
            : ChatMVPPresenter<ChatMVPView, ChatMVPInteractor> = presenter

}