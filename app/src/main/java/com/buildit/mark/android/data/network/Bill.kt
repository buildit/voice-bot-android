package com.buildit.mark.android.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by harshit.laddha on 25/01/2020
 */
data class Bill(@Expose
                @SerializedName("bill_url")
                var billUrl: String? = null,

                @Expose
                @SerializedName("img_url")
                var imgUrl: String? = null,

                @Expose
                @SerializedName("title")
                var title: String? = null,

                @Expose
                @SerializedName("status")
                var status: String? = null,

                @Expose
                @SerializedName("amount")
                var amount: String? = null)