package com.example.mahsimecommerce.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class FatherProduct (
    @SerializedName("results") val results : List<ChildProduct>
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ChildProduct)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FatherProduct> {
        override fun createFromParcel(parcel: Parcel): FatherProduct {
            return FatherProduct(parcel)
        }

        override fun newArray(size: Int): Array<FatherProduct?> {
            return arrayOfNulls(size)
        }
    }
}