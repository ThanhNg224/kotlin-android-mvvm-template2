package com.thanhng224.app.feature.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.core.util.Result
import com.thanhng224.app.core.util.UiState
import com.thanhng224.app.feature.product.domain.usecases.GetProductDetailsUseCase
import com.thanhng224.app.feature.product.presentation.model.ProductDetailsUiModel
import com.thanhng224.app.feature.product.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _productDetailsState =
        MutableStateFlow<UiState<ProductDetailsUiModel>>(UiState.loading())
    val productDetailsState = _productDetailsState.asStateFlow()

    init {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch(ioDispatcher) {
            _productDetailsState.value = UiState.loading()

            when (val result = getProductDetailsUseCase()) {
                is Result.Success -> {
                    _productDetailsState.value = UiState.success(result.data.toUiModel())
                }
                is Result.Error -> {
                    _productDetailsState.value = UiState.error(result.exception)
                }
            }
        }
    }
}

