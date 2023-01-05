package com.example.mahsimecommerce.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FatherSlider (
    @SerializedName("results") val results : List<ChildSlider>
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ChildSlider)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FatherSlider> {
        override fun createFromParcel(parcel: Parcel): FatherSlider {
            return FatherSlider(parcel)
        }

        override fun newArray(size: Int): Array<FatherSlider?> {
            return arrayOfNulls(size)
        }
    }
}