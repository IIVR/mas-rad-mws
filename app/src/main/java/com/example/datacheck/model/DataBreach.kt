package com.example.datacheck.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBreach(
    @SerializedName("Name")
    val name: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Domain")
    val domain: String,

    @SerializedName("BreachDate")
    val breachDate: String,

    @SerializedName("AddedDate")
    val addedDate: String,

    @SerializedName("ModifiedDate")
    val modifiedDate: String,

    @SerializedName("PwnCount")
    val pwnCount: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("IsVerified")
    val isVerified: String,

    @SerializedName("IsFabricated")
    val isFabricated: String,

    @SerializedName("IsSensitive")
    val isSensitive: String,

    @SerializedName("IsRetired")
    val isRetired: String,

    @SerializedName("IsSpamList")
    val isSpamList: String,

    @SerializedName("LogoPath")
    val logoPath: String,

) : Parcelable {
}