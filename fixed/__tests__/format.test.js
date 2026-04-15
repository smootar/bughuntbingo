import { describe, it, expect } from 'vitest'
import { formatDisplay, formatHistoryEntry } from '../utils/format.js'

describe('formatDisplay', () => {
  it('returns "0" for empty string', () => {
    expect(formatDisplay('')).toBe('0')
  })

  it('returns "0" for null', () => {
    expect(formatDisplay(null)).toBe('0')
  })

  it('passes through "0"', () => {
    expect(formatDisplay('0')).toBe('0')
  })

  it('formats integers', () => {
    expect(formatDisplay('12345')).toBe('12345')
  })

  it('formats decimals', () => {
    expect(formatDisplay('3.14')).toBe('3.14')
  })

  it('preserves trailing dot while typing', () => {
    expect(formatDisplay('3.')).toBe('3.')
  })

  it('passes through "Error"', () => {
    expect(formatDisplay('Error')).toBe('Error')
  })

  it('returns "Error" for NaN input', () => {
    expect(formatDisplay('abc')).toBe('Error')
  })

  it('returns "Error" for Infinity', () => {
    expect(formatDisplay('Infinity')).toBe('Error')
  })
})

describe('formatHistoryEntry', () => {
  it('formats addition', () => {
    expect(formatHistoryEntry(3, '+', 4, 7)).toBe('3 + 4 = 7')
  })

  it('formats subtraction with minus sign', () => {
    expect(formatHistoryEntry(5, '-', 3, 2)).toBe('5 \u2212 3 = 2')
  })

  it('formats multiplication with times sign', () => {
    expect(formatHistoryEntry(4, '*', 3, 12)).toBe('4 \u00d7 3 = 12')
  })

  it('formats division with division sign', () => {
    expect(formatHistoryEntry(10, '/', 2, 5)).toBe('10 \u00f7 2 = 5')
  })
})
