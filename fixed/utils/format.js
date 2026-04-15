/**
 * Format a number for the calculator display.
 * Adds commas for thousands, handles special values.
 * @param {string} value - The display string
 * @returns {string}
 */
export function formatDisplay(value) {
  if (value === '' || value === null || value === undefined) return '0'
  if (value === 'Error') return 'Error'

  const str = String(value)

  // Don't format if user is still typing a decimal
  if (str.endsWith('.')) return str

  const num = parseFloat(str)
  if (isNaN(num)) return 'Error'
  if (!isFinite(num)) return 'Error'

  // If it has a decimal part, limit to 10 decimal places
  if (str.includes('.') || (num % 1 !== 0)) {
    const formatted = parseFloat(num.toPrecision(12))
    return String(formatted)
  }

  return String(num)
}

/**
 * Format a history entry string.
 * @param {number|string} a - Left operand
 * @param {string} op - Operator symbol
 * @param {number|string} b - Right operand
 * @param {number|string} result - Calculation result
 * @returns {string}
 */
export function formatHistoryEntry(a, op, b, result) {
  const opSymbol = { '+': '+', '-': '\u2212', '*': '\u00d7', '/': '\u00f7' }[op] || op
  return `${a} ${opSymbol} ${b} = ${result}`
}
