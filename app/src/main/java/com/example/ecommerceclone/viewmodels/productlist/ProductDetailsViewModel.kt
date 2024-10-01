package com.example.ecommerceclone.viewmodels.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ecommerceclone.data.models.ProductItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel @AssistedInject constructor(
    private val productRepository: ProductRepository,
    @Assisted private val productId: Int
) : ViewModel() {

    val productItem: StateFlow<ProductItem> = productRepository.productItem

    val isLoading: StateFlow<Boolean> = productRepository.isLoading

    val isProductItemAddedToCart = productRepository.isProductItemAddedToCart

    init {
        viewModelScope.launch {
            productRepository.getProductDetailsById(productId)
        }
    }

    fun addProductItemInsideCart() {
        viewModelScope.launch {
            productRepository.addProductInsideCart()
        }
    }

    @AssistedFactory
    interface ProductDetailsViewModelFactory {
        fun create(productId: Int): ProductDetailsViewModel
    }

    companion object {

        fun provideProductDetailsViewModel(
            productId: Int,
            factory: ProductDetailsViewModelFactory
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(productId) as T
                }
            }
    }
}