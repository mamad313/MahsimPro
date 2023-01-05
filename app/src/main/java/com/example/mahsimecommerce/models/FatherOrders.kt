package com.example.mahsimecommerce.models

import com.google.gson.annotations.SerializedName

data class FatherOrders (

    @SerializedName("results") val results : List<ChildOrders>
)