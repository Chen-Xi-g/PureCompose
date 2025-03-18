package com.ggf.pure.ui.component.text

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ggf.pure.data.sampleTextData
import com.ggf.pure.ui.component.CardComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextComponentList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        header(
            key = "shadow_header",
            text = "Shadow Text"
        )

        item(
            key = "shadow_item"
        ) {
            CardComponent(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ShadowTextComponent(
                    text = sampleTextData.first()
                )
            }
        }

        header(
            key = "marquee_header",
            text = "Marquee Text"
        )

        item(
            key = "marquee_item"
        ) {
            CardComponent(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MarqueeTextComponent(
                    text = sampleTextData.first()
                )
            }
        }

        header(
            key = "typewriter_header",
            text = "Typewriter Animated Text"
        )

        item(
            key = "typewriter_item"
        ) {
            CardComponent(
                modifier = Modifier.fillMaxWidth()
            ) {
                TypewriterTextComponent()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.header(
    key: String,
    text: String
) {
    stickyHeader(
        key = key
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        )
    }
}