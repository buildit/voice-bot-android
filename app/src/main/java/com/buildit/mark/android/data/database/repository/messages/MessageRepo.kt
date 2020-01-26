package com.buildit.mark.android.data.database.repository.messages

import io.reactivex.Observable

/**
 * Created by harshit.laddha on 25/01/2020
 */
interface MessageRepo {

    fun isMessagesRepoEmpty(): Observable<Boolean>

    fun insertMessages(messages: List<Message>): Observable<Boolean>

    fun loadMessages(): Observable<List<Message>>
}