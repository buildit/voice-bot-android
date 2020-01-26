package com.buildit.mark.android.ui.main.bills.presenter

import com.buildit.mark.android.ui.base.presenter.MVPPresenter
import com.buildit.mark.android.ui.main.bills.interactor.ChatMVPInteractor
import com.buildit.mark.android.ui.main.bills.view.ChatMVPView

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface ChatMVPPresenter<V : ChatMVPView, I : ChatMVPInteractor> : MVPPresenter<V, I> {

    fun onViewPrepared()
}