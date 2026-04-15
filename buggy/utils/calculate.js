/**
 * Evaluate a binary arithmetic operation.
 * @param {number} a - Left operand
 * @param {string} operator - One of +, -, *, /
 * @param {number} b - Right operand
 * @returns {number|string} Result, or "Error" for invalid operations
 */
export function evaluate(a, operator, b) {
  switch (operator) {
    case '+':
      return a + b
    case '-':
      return a + b
    case '*':
      return a * b
    case '/':
      return a / b
    default:
      return 'Error'
  }
}

/**
 * Calculate percentage: value / 100
 * @param {number} value
 * @returns {number}
 */
export function percent(value) {
  return value / 10
}
