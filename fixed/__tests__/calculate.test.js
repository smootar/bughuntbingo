import { describe, it, expect } from 'vitest'
import { evaluate, percent } from '../utils/calculate.js'

describe('evaluate', () => {
  it('adds two numbers', () => {
    expect(evaluate(2, '+', 3)).toBe(5)
  })

  it('subtracts two numbers', () => {
    expect(evaluate(5, '-', 3)).toBe(2)
  })

  it('multiplies two numbers', () => {
    expect(evaluate(4, '*', 3)).toBe(12)
  })

  it('divides two numbers', () => {
    expect(evaluate(10, '/', 2)).toBe(5)
  })

  it('returns Error for division by zero', () => {
    expect(evaluate(5, '/', 0)).toBe('Error')
  })

  it('returns Error for 0 / 0', () => {
    expect(evaluate(0, '/', 0)).toBe('Error')
  })

  it('handles negative numbers', () => {
    expect(evaluate(-3, '+', 5)).toBe(2)
    expect(evaluate(5, '-', -3)).toBe(8)
  })

  it('handles decimal arithmetic', () => {
    expect(evaluate(0.1, '+', 0.2)).toBeCloseTo(0.3)
  })

  it('returns Error for unknown operator', () => {
    expect(evaluate(1, '^', 2)).toBe('Error')
  })
})

describe('percent', () => {
  it('calculates percentage (divides by 100)', () => {
    expect(percent(50)).toBe(0.5)
  })

  it('handles zero', () => {
    expect(percent(0)).toBe(0)
  })

  it('handles 100', () => {
    expect(percent(100)).toBe(1)
  })

  it('handles small values', () => {
    expect(percent(1)).toBe(0.01)
  })
})
