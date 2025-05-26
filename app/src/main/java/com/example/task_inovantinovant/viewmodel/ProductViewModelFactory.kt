package com.example.task_inovantinovant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task_inovantinovant.repository.ProductRepo

class ProductViewModelFactory(private val productRepo: ProductRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepo) as T
    }
}