# Bug Hunt Bingo - Answer Key (FACILITATOR ONLY)

> **Do not share this document with participants!**

There are exactly **10 bugs** in the buggy version: 3 easy, 4 medium, 3 hard.

---

## Bug 1: Subtraction performs addition

- **Difficulty:** Easy
- **File:** `buggy/utils/calculate.js`
- **Line:** 13
- **Bingo squares:** Fix a bug using Grep, Fix a bug in under 90 seconds, Fix a logic bug, Fix a bug in a utility function

### How it manifests
Type `5 - 3 =` and get `8` instead of `2`. Any subtraction gives the wrong answer.

### How to discover
- Use the calculator and try subtraction
- Grep for `case '-'` or `a + b` in calculate.js

### The fix
```diff
  case '-':
-   return a + b
+   return a - b
```

---

## Bug 2: Clear button doesn't reset the operator

- **Difficulty:** Easy
- **File:** `buggy/components/Calculator.jsx`
- **Line:** 62-67 (handleClear function)
- **Bingo squares:** Fix a logic bug, Explain a bug to another team

### How it manifests
1. Type `5 + 3 =` (shows 8)
2. Press `C` (shows 0)
3. Type `4` then `=`
4. Instead of showing `4`, it applies the old `+` operator

### How to discover
- Test the clear button followed by a new calculation
- Read the `handleClear` function and notice `setCurrentOperator(null)` is missing

### The fix
```diff
  function handleClear() {
    setDisplay('0')
    setPreviousOperand(null)
+   setCurrentOperator(null)
    setWaitingForOperand(false)
    setExpression('')
  }
```

---

## Bug 3: Typo `calssName` instead of `className`

- **Difficulty:** Easy
- **File:** `buggy/components/Display.jsx`
- **Line:** 7
- **Bingo squares:** Fix a typo or syntax bug, Fix a bug using Grep, Fix a bug in under 90 seconds

### How it manifests
The calculator display value area loses its styling — the number appears small and unstyled instead of large monospace text. The app doesn't crash; it just looks broken.

### How to discover
- Visually obvious — the display looks wrong
- Grep for `calss` to find the typo
- Browser DevTools: the `<div>` has an HTML attribute `calssname` instead of `class`

### The fix
```diff
- <div calssName="display-value">{formatDisplay(value)}</div>
+ <div className="display-value">{formatDisplay(value)}</div>
```

---

## Bug 4: No division-by-zero guard

- **Difficulty:** Medium
- **File:** `buggy/utils/calculate.js`
- **Line:** 16-17
- **Bingo squares:** Prevent a bug with input validation, Fix a logic bug, Fix a bug in a utility function

### How it manifests
- Type `5 / 0 =` — shows `Infinity` instead of `Error`
- Type `0 / 0 =` — shows `NaN`

### How to discover
- Test edge cases with division
- Read the division case in `evaluate()` and notice there's no zero check

### The fix
```diff
  case '/':
+   if (b === 0) return 'Error'
    return a / b
```

---

## Bug 5: Percentage divides by 10 instead of 100

- **Difficulty:** Medium
- **File:** `buggy/utils/calculate.js`
- **Line:** 29
- **Bingo squares:** Fix a bug using Grep, Fix a logic bug, Use Claude to suggest a fix, Fix a bug in a utility function

### How it manifests
Type `50` then press `%` — shows `5` instead of `0.5`.

### How to discover
- Test the percentage button and know the expected result
- Grep for `/ 10` to find the wrong divisor

### The fix
```diff
- return value / 10
+ return value / 100
```

---

## Bug 6: Memory Recall shows the literal string "memory"

- **Difficulty:** Medium
- **File:** `buggy/components/Calculator.jsx`
- **Line:** 90
- **Bingo squares:** Find a bug by reading a stack trace, Fix a typo or syntax bug, Find a bug that causes NaN

### How it manifests
1. Type `42` and press `M+`
2. Press `C` to clear
3. Press `MR` — display shows the word `memory` instead of `42`
4. Any subsequent calculation produces `NaN` (since `parseFloat("memory")` is `NaN`)

### How to discover
- Use the memory features and notice the word "memory" on screen
- Read `handleMemoryRecall` — it says `setDisplay("memory")` (string literal) instead of `setDisplay(String(memory))` (variable)

### The fix
```diff
- setDisplay("memory")
+ setDisplay(String(memory))
```

---

## Bug 7: History shows entries in wrong order (oldest first)

- **Difficulty:** Medium
- **File:** `buggy/components/HistoryPanel.jsx`
- **Line:** 1-16 (entire component)
- **Bingo squares:** Fix a logic bug, Explain a bug to another team

### How it manifests
Perform several calculations. The first calculation stays at the top and the most recent appears at the bottom, potentially scrolled off-screen. Expected: most recent first.

### How to discover
- Do 4-5 calculations and notice the ordering
- Read `HistoryPanel.jsx` and see it maps `history` directly without reversing

### The fix
```diff
  export default function HistoryPanel({ history }) {
+   const reversed = [...history].reverse()
+
    return (
      <div className="history-panel">
        <div className="history-title">History</div>
        <div className="history-list">
-         {history.length === 0 && (
+         {reversed.length === 0 && (
            <div className="history-empty">No calculations yet</div>
          )}
-         {history.map((entry, i) => (
+         {reversed.map((entry, i) => (
```

---

## Bug 8: Backspace on single digit leaves display empty

- **Difficulty:** Hard
- **File:** `buggy/components/Calculator.jsx`
- **Line:** 71-72 (handleBackspace function)
- **Bingo squares:** Prevent a bug with input validation, Find a bug that causes NaN, Find a bug by reading a stack trace

### How it manifests
1. Type `5`
2. Press backspace — display goes completely blank (empty string)
3. Any subsequent operation produces `NaN` because `parseFloat("")` is `NaN`

### How to discover
- Specifically test backspace on a single digit
- The NaN propagation in later calculations is a clue
- Read `handleBackspace` and notice the missing empty-string guard

### The fix
```diff
  const result = display.slice(0, -1)
- setDisplay(result)
+ setDisplay(result === '' ? '0' : result)
```

---

## Bug 9: Multiple decimal points allowed

- **Difficulty:** Hard
- **File:** `buggy/components/Calculator.jsx`
- **Line:** 33 (handleDecimal function)
- **Bingo squares:** Prevent a bug with input validation, Fix a bug + add a test, Find a bug that causes NaN

### How it manifests
1. Type `3.1`
2. Press `.` again
3. Type `4` — display shows `3.1.4`
4. Press any operator or `=` — produces `NaN` because `parseFloat("3.1.4")` is `NaN`

### How to discover
- Deliberately press the decimal button twice
- Read `handleDecimal` and notice the missing `includes('.')` guard

### The fix
```diff
  function handleDecimal() {
    if (waitingForOperand) {
      setDisplay('0.')
      setWaitingForOperand(false)
      return
    }
+   if (display.includes('.')) return
    setDisplay(display + '.')
  }
```

---

## Bug 10: Chained operations lose the intermediate result

- **Difficulty:** Hard
- **File:** `buggy/components/Calculator.jsx`
- **Line:** 36-44 (handleOperator function)
- **Bingo squares:** Fix a logic bug, Use Claude to suggest a fix, Explain a bug to another team

### How it manifests
1. Type `2 + 3` then press `+` again
2. Expected: display shows `5` (intermediate result of 2+3)
3. Actual: display shows `3` — the `2 +` is discarded, `previousOperand` is overwritten with `3`
4. Type `4 =` — shows `7` (3+4) instead of `9` (5+4)

### How to discover
- Test chained operations like `2 + 3 + 4 =`
- This is the hardest bug — requires understanding how `handleOperator` should evaluate the pending operation before storing the new one
- Reading the fixed version or using Claude to reason about the state machine helps

### The fix
```diff
  function handleOperator(nextOperator) {
    const current = parseFloat(display)

-   setPreviousOperand(current)
+   if (previousOperand !== null && currentOperator && !waitingForOperand) {
+     const result = evaluate(previousOperand, currentOperator, current)
+     if (result === 'Error') {
+       setDisplay('Error')
+       setPreviousOperand(null)
+       setCurrentOperator(null)
+       setWaitingForOperand(false)
+       return
+     }
+     setDisplay(String(result))
+     setPreviousOperand(result)
+   } else {
+     setPreviousOperand(current)
+   }
```

---

## Bug Summary Table

| # | Description | Difficulty | File | Line(s) |
|---|-------------|-----------|------|---------|
| 1 | Subtraction does addition | Easy | `calculate.js` | 13 |
| 2 | Clear doesn't reset operator | Easy | `Calculator.jsx` | 62-67 |
| 3 | `calssName` typo | Easy | `Display.jsx` | 7 |
| 4 | No division-by-zero guard | Medium | `calculate.js` | 16-17 |
| 5 | Percent divides by 10 | Medium | `calculate.js` | 29 |
| 6 | Memory recall shows "memory" | Medium | `Calculator.jsx` | 90 |
| 7 | History in wrong order | Medium | `HistoryPanel.jsx` | all |
| 8 | Backspace leaves display empty | Hard | `Calculator.jsx` | 71-72 |
| 9 | Multiple decimals allowed | Hard | `Calculator.jsx` | 33 |
| 10 | Chained ops lose intermediate | Hard | `Calculator.jsx` | 36-44 |

## Tips for Facilitating

- If teams are stuck after 5 minutes, hint: "Have you tried all the basic operations?"
- If teams find easy bugs fast, hint: "What happens with edge cases? Zero? Multiple decimals?"
- Bug 10 is the hardest — most teams won't find it without chaining operations
- Bug 3 is the most visually obvious — teams will likely find it first
- Bugs 8 and 9 both cause NaN propagation — teams may find the NaN but struggle to trace it back to the root cause
