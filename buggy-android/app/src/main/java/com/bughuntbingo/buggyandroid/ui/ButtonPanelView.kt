package com.bughuntbingo.buggyandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CalcButton(
    val label: String,
    val type: String,
    val action: String,
    val wide: Boolean = false
)

private val buttons = listOf(
    CalcButton("MC", "memory", "memory-clear"),
    CalcButton("MR", "memory", "memory-recall"),
    CalcButton("M+", "memory", "memory-add"),
    CalcButton("M\u2212", "memory", "memory-sub"),

    CalcButton("C", "clear", "clear"),
    CalcButton("\u232b", "clear", "backspace"),
    CalcButton("%", "operator", "percent"),
    CalcButton("\u00f7", "operator", "/"),

    CalcButton("7", "digit", "7"),
    CalcButton("8", "digit", "8"),
    CalcButton("9", "digit", "9"),
    CalcButton("\u00d7", "operator", "*"),

    CalcButton("4", "digit", "4"),
    CalcButton("5", "digit", "5"),
    CalcButton("6", "digit", "6"),
    CalcButton("\u2212", "operator", "-"),

    CalcButton("1", "digit", "1"),
    CalcButton("2", "digit", "2"),
    CalcButton("3", "digit", "3"),
    CalcButton("+", "operator", "+"),

    CalcButton("0", "digit", "0", wide = true),
    CalcButton(".", "digit", "."),
    CalcButton("=", "equals", "=")
)

@Composable
fun ButtonPanelView(
    onButton: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(buttons) { btn ->
            Button(
                onClick = { onButton(btn.action) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor(btn.type)
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = if (btn.wide) {
                    Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                } else {
                    Modifier
                        .aspectRatio(1f)
                        .height(56.dp)
                }
            ) {
                Text(
                    text = btn.label,
                    fontSize = if (btn.type == "memory") 14.sp else 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = foregroundColor(btn.type)
                )
            }
        }
    }
}

private fun backgroundColor(type: String): Color {
    return when (type) {
        "digit" -> Color(0xFF1A1A3D)
        "operator" -> Color(0xFFE8455F)
        "equals" -> Color(0xFF109C57)
        "clear" -> Color(0xFF543482)
        "memory" -> Color(0xFF1A1A3D)
        else -> Color.Gray
    }
}

private fun foregroundColor(type: String): Color {
    return when (type) {
        "digit" -> Color(0xFFE5E5E5)
        "memory" -> Color(0xFF878DA9)
        else -> Color.White
    }
}
