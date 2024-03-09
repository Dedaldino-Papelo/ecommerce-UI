package com.example.ecommerceui.ui.screens

import androidx.lifecycle.ViewModel
import com.example.ecommerceui.data.HomeUiSTate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiSTate = MutableStateFlow(HomeUiSTate())
    val uiSTate: StateFlow<HomeUiSTate> = _uiSTate.asStateFlow()
}