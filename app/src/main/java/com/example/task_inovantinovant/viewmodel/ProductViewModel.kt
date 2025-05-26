package com.example.task_inovantinovant.viewmodel

import androidx.lifecycle.*
import com.example.task_inovantinovant.models.ProductDetailResponse
import com.example.task_inovantinovant.repository.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repo: ProductRepo) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _productsDetails = MutableLiveData<ProductDetailResponse>()
    val productsDetails: LiveData<ProductDetailResponse> = _productsDetails

    init {
        fetchProductDetails()
    }

    private fun fetchProductDetails() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.getProductDetails()
                _productsDetails.postValue(result)
            } catch (e: Exception) {

            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
