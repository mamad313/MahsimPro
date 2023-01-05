package com.example.mahsimecommerce.models

import com.google.gson.annotations.SerializedName


data class ChildOrders(

    @SerializedName("objectId") val objectId: String?=null,
    @SerializedName("userId") var userId: String?=null,
    @SerializedName("createdAt") val createdAt: String?=null,
    @SerializedName("updatedAt") val updatedAt: String?=null,
    @SerializedName("price") var price: String?=null,
    @SerializedName("name") var name: String?=null
)