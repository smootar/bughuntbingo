package com.bughuntbingo.buggyandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bughuntbingo.buggyandroid.CalculatorViewModel

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DisplayView(
            expression = viewModel.expression,
            value = viewModel.display,
            modifier = Modifier.fillMaxWidth()
        )

        MemoryIndicatorView(
            hasMemory = viewModel.memory != 0.0,
            modifier = Modifier.fillMaxWidth()
        )

        ButtonPanelView(
            onButton = { action -> viewModel.handleButton(action) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        HistoryPanelView(
            history = viewModel.history,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}
