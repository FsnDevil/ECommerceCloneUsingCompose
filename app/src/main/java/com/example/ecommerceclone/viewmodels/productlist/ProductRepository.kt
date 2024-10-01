package com.example.ecommerceclone.viewmodels.productlist

import android.util.Log
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.ecommerceclone.ui.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "ProductRepository"

class ProductRepository @Inject constructor(private val apiService: ApiService) {

    private var _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    var productList: StateFlow<List<ProductItem>> = _productList

    private var _productItem = MutableStateFlow<ProductItem>(ProductItem())
    var productItem: StateFlow<ProductItem> = _productItem

    private var _productListError = MutableStateFlow("")
    var productListError = _productListError.asStateFlow()

    private var _isProductItemAddedToCart = MutableStateFlow(false)
    var isProductItemAddedToCart = _isProductItemAddedToCart.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()


    suspend fun getProductData() {
        try {

            _isLoading.value = true
            val response = apiService.getProductsData()
            if (response.isSuccessful && response.body() != null) {
                _productList.emit(response.body()!!)
                _isLoading.value = false
            } else {
                _productListError.emit("Something Went Wrong please try again..")
                _isLoading.value = false
            }
        } catch (e: Exception) {
            _productListError.emit(e.localizedMessage)
            _isLoading.value = false
        }
    }

    suspend fun getProductDetailsById(id: Int) {
        try {
            _isLoading.value = true
            val response = apiService.getProductDetailsById(id)
            if (response.isSuccessful && response.body() != null) {
                _productItem.emit(response.body()!!)
                _isLoading.value = false
            } else {
                _productListError.emit("Something Went Wrong please try again..")
                _isLoading.value = false
            }
        } catch (e: Exception) {
            _productListError.emit(e.localizedMessage)
            _isLoading.value = false
        }
    }

    suspend fun addProductInsideCart() {
        _isLoading.emit(true)
        delay(3000)
        _isProductItemAddedToCart.value = true
        _isLoading.emit(false)

    }
}