package com.bughuntbingo.buggyandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bughuntbingo.buggyandroid.formatDisplay

@Composable
fun DisplayView(
    expression: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFF0F3461),
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = if (expression.isEmpty()) " " else expression,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF878DA9),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = formatDisplay(value),
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE5E5E5),
            textAlign = TextAlign.End,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
