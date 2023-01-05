package com.example.mahsimecommerce.models

import com.google.gson.annotations.SerializedName

data class ResultsResult (
    @SerializedName("results") val results : List<ResultsProfileData>
    )