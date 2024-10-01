package com.example.ecommerceclone.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.ui.components.ProductList
import com.example.ecommerceclone.ui.components.RotatingLoader
import com.example.ecommerceclone.ui.components.ShowErrorMessage
import com.example.ecommerceclone.viewmodels.productlist.ProductViewModel

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(productItemClicked: (ProductItem) -> Unit) {

    val productViewModel: ProductViewModel = hiltViewModel()
    val productList = productViewModel.productList.collectAsStateWithLifecycle()
    val productListError = productViewModel.productListError.collectAsStateWithLifecycle()
    val isLoading = productViewModel.isLoading.collectAsStateWithLifecycle()

    var productListErrorCompose = remember {
        mutableStateOf("")
    }

    var isLoadingCompose = remember {
        mutableStateOf(false)
    }

    ProductList(productList.value, onAddToCartClicked = {

    }, productItemClicked)

    LaunchedEffect(key1 = productListError.value) {
        if (productListError.value.isNotEmpty()) {
            Log.d(TAG, "HomeScreen: ${productListError.value}")
            productListErrorCompose.value = productListError.value
        }
    }

    if (productListErrorCompose.value.isNotEmpty()) {
        ShowErrorMessage(errorMessage = productListErrorCompose.value ) {
            productListErrorCompose.value=""
        }
    }

    LaunchedEffect(key1 = isLoading.value) {
        isLoadingCompose.value = isLoading.value
    }

    if (isLoadingCompose.value) {
        RotatingLoader(isLoadingCompose.value)
    }
}

