package com.example.ecommerceclone.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceclone.ui.components.BottomButtons
import com.example.ecommerceclone.ui.components.ProductDetailsSection
import com.example.ecommerceclone.ui.components.ProductImageSection
import com.example.ecommerceclone.ui.components.RotatingLoader
import com.example.ecommerceclone.viewmodels.productlist.ProductDetailsViewModel
import com.example.ecommerceclone.viewmodels.productlist.ProductDetailsViewModelProvider
import dagger.hilt.android.EntryPointAccessors

@Composable
fun ProductDetailsScreen(productItemId: Int) {

    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ProductDetailsViewModelProvider::class.java
    ).productDetailsViewModelFactory()

    val productDetailsViewModel: ProductDetailsViewModel = viewModel(factory = ProductDetailsViewModel.provideProductDetailsViewModel(productItemId, factory))
    val productItem = productDetailsViewModel.productItem.collectAsStateWithLifecycle()

    val isProductItemAddedToCart = productDetailsViewModel.isProductItemAddedToCart.collectAsStateWithLifecycle()
    val isLoading = productDetailsViewModel.isLoading.collectAsStateWithLifecycle()
    
    var isLoadingCompose = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        
        
        LaunchedEffect(key1 = isLoading.value){
            isLoadingCompose.value = isLoading.value
        }
        
        if (isLoadingCompose.value){
            RotatingLoader(isVisible = isLoadingCompose.value)
        }
        
        
        Box(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)) {
                item {
                    ProductImageSection(productItem.value) // Make the entire section scrollable
                    Spacer(modifier = Modifier.height(16.dp))
                    ProductDetailsSection(productItem.value)
                }
            }

            // Bottom buttons fixed at the bottom
            BottomButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter), addToWishList = {
                    Log.d("TAG", "${productItem.value.title}")
                } , addToBag = {
                    productDetailsViewModel.addProductItemInsideCart()
                }
            )
        }
    }

    LaunchedEffect(key1 = isProductItemAddedToCart.value){
        if (isProductItemAddedToCart.value){
            Log.d("TAG", "Product Added..")
        }
    }
}