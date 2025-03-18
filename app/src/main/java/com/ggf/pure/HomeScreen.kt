package com.ggf.pure

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ggf.pure.ui.DevicePreviews
import com.ggf.pure.ui.component.text.TextComponentList
import com.ggf.pure.ui.theme.PureComposeTheme

/**
 * Home screen of the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    menuId: Int = 0,
    modifier: Modifier = Modifier
) {
    when(menuId){
        1 -> {
            TextComponentList(modifier)
        }
    }
}

@DevicePreviews
@Composable
fun PreviewHomeScreen() {
    PureComposeTheme {
        Surface {
            HomeScreen()
        }
    }
}