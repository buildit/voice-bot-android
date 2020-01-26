package com.buildit.mark.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.buildit.mark.android.data.database.repository.messages.Message

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Database(entities = [(Message::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {


}