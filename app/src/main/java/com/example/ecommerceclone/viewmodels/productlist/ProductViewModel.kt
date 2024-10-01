package com.example.ecommerceclone.viewmodels.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    val productList: StateFlow<List<ProductItem>> = productRepository.productList

    val productListError: StateFlow<String> = productRepository.productListError

    val isLoading: StateFlow<Boolean> = productRepository.isLoading

    init {
        viewModelScope.launch {
            productRepository.getProductData()
        }
    }
}