package com.ggf.pure.ui.component.text

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *  Displays a text component with a marquee effect implemented using `basicMarquee`.
 *
 *  The core code is the call to `basicMarquee`.
 *  This function enables a horizontal scrolling effect for the text
 *
 *  @param text The text content to display.
 */
@Composable
fun MarqueeTextComponent(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier
            .basicMarquee(Int.MAX_VALUE),
        text = text
    )
}