package com.buildit.mark.android.data.database.repository.bills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BillsResponse(

    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("subtitle")
    var subtitle: String,

    @Expose
    @SerializedName("suggestion")
    var suggestion: String,

    @Expose
    @SerializedName("suggestedActions")
    var suggestedActions: List<String> = listOf(),

    @Expose
    @SerializedName("bills")
    var bills: List<BillItem> = listOf()
)