package com.example.mahsimecommerce.models


import com.google.gson.annotations.SerializedName


data class ResultsProfileData (

    @SerializedName("objectId") val objectId : String ?= null,
    @SerializedName("userId") var userId : String?= null,
    @SerializedName("createdAt") val createdAt : String?= null,
    @SerializedName("updatedAt") val updatedAt : String?= null,
    @SerializedName("phone") var phone : String?= null,
    @SerializedName("pic") var pic : ByteArray?= null,
    @SerializedName("adress") var adress : String?= null
)