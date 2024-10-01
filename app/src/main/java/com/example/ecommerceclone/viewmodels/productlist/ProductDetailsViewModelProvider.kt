package com.example.ecommerceclone.viewmodels.productlist

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ProductDetailsViewModelProvider {

    fun productDetailsViewModelFactory():ProductDetailsViewModel.ProductDetailsViewModelFactory
}
