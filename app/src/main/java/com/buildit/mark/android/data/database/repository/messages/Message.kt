package com.buildit.mark.android.data.database.repository.messages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Entity(tableName = "messages")
data class Message(
        @Expose
        @PrimaryKey
        var id: Long,

        @Expose
        @SerializedName("message_text")
        @ColumnInfo(name = "message_text")
        var messageText: String,

        @Expose
        @SerializedName("sender_img_url")
        @ColumnInfo(name = "sender_img_url")
        var imgUrl: String?,

        @Expose
        @SerializedName("created_at")
        @ColumnInfo(name = "created_at")
        var createdAt: String?

)