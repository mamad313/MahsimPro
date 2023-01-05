package com.example.mahsimecommerce.repository

import com.example.mahsimecommerce.Api
import com.example.mahsimecommerce.models.FatherProduct

class Repository(private val api: Api) {
    suspend fun getData() = api.getApi()
    suspend fun getProductData() = api.getProductApi()
}