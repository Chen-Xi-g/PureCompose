package com.ggf.pure.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShadowTextComponent(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.shadow()
    )
}

/**
 *  Extension function on `TextStyle` to add a shadow effect to the text.
 *
 *  This function creates a new `TextStyle` object with a shadow applied, without modifying the original `TextStyle`.
 *  It allows customizing the shadow size, color, horizontal offset, and vertical offset.
 *
 *  @param shadowSize The size of the shadow's blur radius in Dp. Defaults to 6.dp.
 *  @param shadowColor The color of the shadow. Defaults to Black.
 *  @param shadowX The horizontal offset of the shadow in Dp. Defaults to 4.dp.
 *  @param shadowY The vertical offset of the shadow in Dp. Defaults to 2.dp.
 *  @return A new `TextStyle` object with the specified shadow applied.
 */
@Composable
fun TextStyle.shadow(
    shadowSize: Dp = 6.dp,
    shadowColor: Color = Color.Black,
    shadowX: Dp = 4.dp,
    shadowY: Dp = 2.dp
): TextStyle{
    return copy(
        shadow = Shadow(
            offset = Offset(
                x = with(LocalDensity.current) { shadowX.toPx() },
                y = with(LocalDensity.current) { shadowY.toPx() }
            ),
            color = shadowColor,
            blurRadius = with(LocalDensity.current) { shadowSize.toPx() }
        )
    )
}