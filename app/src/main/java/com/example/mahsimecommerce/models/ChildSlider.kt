package com.example.mahsimecommerce.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ChildSlider (
    @SerializedName("objectId") val objectId : String,
    @SerializedName("title") val title : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String,
    @SerializedName("url") val url : String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(objectId)
        parcel.writeString(title)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildSlider> {
        override fun createFromParcel(parcel: Parcel): ChildSlider {
            return ChildSlider(parcel)
        }

        override fun newArray(size: Int): Array<ChildSlider?> {
            return arrayOfNulls(size)
        }
    }
}