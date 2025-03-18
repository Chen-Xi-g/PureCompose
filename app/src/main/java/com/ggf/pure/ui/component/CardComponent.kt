package com.ggf.pure.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            content()
        }
    }
}