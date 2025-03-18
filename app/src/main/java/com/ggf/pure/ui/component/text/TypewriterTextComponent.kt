package com.ggf.pure.ui.component.text

import android.icu.text.BreakIterator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ggf.pure.data.sampleTextData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.StringCharacterIterator

@Composable
fun TypewriterTextComponent() {
    var text by remember { mutableStateOf(sampleTextData.first() + "\n\n") }
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(index) {
        if (index == sampleTextData.lastIndex){
            return@LaunchedEffect
        }
        delay(1500)
        text += sampleTextData[++index % sampleTextData.size] + "\n\n"
    }
    TypewriterText(
        text = text,
        delay = 30L
    )
}

/**
 * TODO: When the component leaves the screen, it causes the animation effect to re-execute
 */
@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    delay: Long = 80L
) {
    val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }
    var substringText by remember { mutableStateOf("") }
    var job by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(text) {
        job?.cancel()

        val commonPrefixLength = substringText.commonPrefixWith(text).length
        substringText = text.substring(0, commonPrefixLength)

        job = launch {
            breakIterator.text = StringCharacterIterator(text)
            var nextIndex = breakIterator.following(commonPrefixLength)
            while (nextIndex != BreakIterator.DONE) {
                substringText = text.substring(0, nextIndex)
                nextIndex = breakIterator.next()
                delay(delay)
            }
        }
    }

    Text(
        text = substringText,
        modifier = modifier,
        style = textStyle
    )
}