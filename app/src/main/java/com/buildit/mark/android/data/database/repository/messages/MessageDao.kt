package com.buildit.mark.android.data.database.repository.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(message: List<Message>)

    @Query("SELECT * FROM messages")
    fun loadAll(): List<Message>
}