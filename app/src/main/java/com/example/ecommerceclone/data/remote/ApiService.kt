package com.example.ecommerceclone.data.remote

import com.example.ecommerceclone.data.models.ProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProductsData(): Response<List<ProductItem>>

    @GET("products/{productId}")
    suspend fun getProductDetailsById(@Path("productId") id :Int): Response<ProductItem>

}