package com.ggf.pure

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AdsClick
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material.icons.rounded.TextFormat
import androidx.compose.material.icons.rounded.ToggleOff
import androidx.compose.material.icons.rounded.ToggleOn
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = MainUiState(
            drawerMenu = listOf(
                DrawerMenuItem(1,Icons.Rounded.TextFormat, "Text", true),
                DrawerMenuItem(2,Icons.Rounded.Edit, "TextField"),
                DrawerMenuItem(3,Icons.Rounded.AdsClick, "Button"),
                DrawerMenuItem(4,Icons.Rounded.ToggleOn, "Switch"),
            )
        )
    }

    fun onAction(action: MainAction) {
        when(action) {
            is MainAction.SelectMenuItem -> {
                _uiState.value = _uiState.value.copy(
                    drawerMenu = _uiState.value.drawerMenu.mapIndexed { index, item ->
                        item.copy(isSelected = index == action.index)
                    }
                )
            }
        }
    }

}

data class MainUiState(
    val drawerMenu: List<DrawerMenuItem> = emptyList()
)

sealed interface MainAction {
    data class SelectMenuItem(val index: Int): MainAction
}

data class DrawerMenuItem(
    val id: Int,
    val icon: ImageVector,
    val title: String,
    val isSelected: Boolean = false
)