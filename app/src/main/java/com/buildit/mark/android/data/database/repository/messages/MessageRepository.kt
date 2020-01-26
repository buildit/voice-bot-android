package com.buildit.mark.android.data.database.repository.messages

import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class MessageRepository @Inject internal constructor(private val messageDao: MessageDao) :
        MessageRepo {

    override fun isMessagesRepoEmpty(): Observable<Boolean> = Observable.fromCallable {
        messageDao.loadAll().isEmpty()
    }

    override fun insertMessages(messages: List<Message>): Observable<Boolean> {
        messageDao.insertAll(messages)
        return Observable.just(true)
    }

    override fun loadMessages(): Observable<List<Message>> = Observable.fromCallable {
        messageDao.loadAll()
    }
}


