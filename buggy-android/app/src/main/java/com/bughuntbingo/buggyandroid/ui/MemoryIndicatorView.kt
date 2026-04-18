package com.bughuntbingo.buggyandroid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MemoryIndicatorView(
    hasMemory: Boolean,
    modifier: Modifier = Modifier
) {
    if (hasMemory) {
        Text(
            text = "M",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF109C57),
            modifier = modifier.padding(horizontal = 4.dp)
        )
    }
}
