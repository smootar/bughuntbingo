# Buggy Android Calculator

Android/Kotlin/Compose version of the Bug Hunt Bingo calculator with 10 intentional bugs.

## The 10 Bugs

Same bugs as the iOS version:

1. **Subtraction performs addition** (Calculate.kt:19) - Easy
2. **Clear doesn't reset operator** (CalculatorViewModel.kt:104) - Easy  
3. **Display uses bodySmall font** (DisplayView.kt:42) - Easy
4. **No division-by-zero guard** (Calculate.kt:24) - Medium
5. **Percent divides by 10 instead of 100** (Calculate.kt:36) - Medium
6. **Memory Recall shows "memory" string** (CalculatorViewModel.kt:140) - Medium
7. **History shows oldest first** (HistoryPanelView.kt:47) - Medium
8. **Backspace leaves display empty** (CalculatorViewModel.kt:117) - Hard
9. **Multiple decimals allowed** (CalculatorViewModel.kt:51) - Hard
10. **Chained ops lose intermediate result** (CalculatorViewModel.kt:58) - Hard

## Build & Run

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Or open in Android Studio and run.

## Requirements

- Android SDK 36
- Gradle 8.2+
- Kotlin 1.9.20
