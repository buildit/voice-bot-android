package com.buildit.mark.android.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.buildit.mark.android.di.ApiKeyInfo
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader, internal val protectedApiHeader: ProtectedApiHeader) {

    class PublicApiHeader @Inject constructor(@ApiKeyInfo
                                              @Expose
                                              @SerializedName
                                              ("api_key") val apiKey: String)

    class ProtectedApiHeader @Inject constructor(@Expose
                                                 @SerializedName("api_key")
                                                 val apiKey: String)

}