package com.example.mahsimecommerce.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ChildProduct (
    @SerializedName("objectId") val objectId : String,
    @SerializedName("title") val title : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String,
    @SerializedName("price") val price : Int,
    @SerializedName("url") val url : String,
    @SerializedName("category") val category : String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(objectId)
        parcel.writeString(title)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeInt(price)
        parcel.writeString(url)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildProduct> {
        override fun createFromParcel(parcel: Parcel): ChildProduct {
            return ChildProduct(parcel)
        }

        override fun newArray(size: Int): Array<ChildProduct?> {
            return arrayOfNulls(size)
        }
    }
}