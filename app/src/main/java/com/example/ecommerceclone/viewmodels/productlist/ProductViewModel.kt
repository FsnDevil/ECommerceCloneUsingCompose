package com.example.ecommerceclone.viewmodels.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val productList: StateFlow<List<ProductItem>> = productRepository.productList
    val productListError: StateFlow<String> = productRepository.productListError
    val isLoading: StateFlow<Boolean> = productRepository.isLoading

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    val filteredProductList: StateFlow<List<ProductItem>> = searchQuery
        .combine(productList) { query, productList ->
            if (query.isEmpty()) {
                productList
            } else {
                productList.filter { it.title!!.contains(query, ignoreCase = true) }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            productRepository.getProductData()
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}