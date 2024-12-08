package com.newyorktimes.features.news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newyorktimes.core.Resource
import com.newyorktimes.features.news.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            getArticlesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _homeState.value = _homeState.value.copy(
                            isLoading = true,
                            articles = emptyList(),
                            error = null
                        )
                    }
                    is Resource.Success -> {
                        _homeState.value = _homeState.value.copy(
                            isLoading = false,
                            articles = result.data ?: emptyList(),
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        _homeState.value = _homeState.value.copy(
                            isLoading = false,
                            articles = emptyList(),
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}
