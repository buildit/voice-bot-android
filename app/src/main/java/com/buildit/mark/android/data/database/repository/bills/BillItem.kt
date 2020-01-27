package com.buildit.mark.android.data.database.repository.bills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BillItem(
    @Expose
    @SerializedName("biller")
    var biller: String,

    @Expose
    @SerializedName("billName")
    var billName: String,

    @Expose
    @SerializedName("billImage")
    var billImage: String,

    @Expose
    @SerializedName("subtitle")
    var subtitle: String,

    @Expose
    @SerializedName("billAmount")
    var billAmount: String,

    @Expose
    @SerializedName("isSelected")
    var isSelected: Boolean = true
)