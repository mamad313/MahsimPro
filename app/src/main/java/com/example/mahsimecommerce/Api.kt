package com.example.mahsimecommerce

import com.example.mahsimecommerce.models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json"
    )
    @GET("Slider")
    suspend fun getApi(): FatherSlider

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json"
    )
    @GET("product")
    suspend fun getProductApi(): FatherProduct

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json"
    )
    @GET("product")
    fun getApiId(@Query("where") where:String ): Call<FatherProduct>

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json")
    @POST("userProfile")
    fun getProfile(@Body baseProfileData: ResultsProfileData): Call<ResponseOrders>

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json")

    @GET("userProfile")
    fun getProfileGet(@Query("where") where:String ): Call<ResultsResult>

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json")
    @POST("orders")
    fun setOrders(@Body orderData: ChildOrders): Call<ResponseOrders>

    @Headers(
        "X-Parse-REST-API-Key:jHxfJTJBzbwIYj35D9sQOh38bXVzv2poG8swwAl8",
        "X-Parse-Application-Id:RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK",
        "Content-Type:application/json")

    @GET("orders")
    fun getOrders(@Query("where") where:String ): Call<ChildOrders>



}