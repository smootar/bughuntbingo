import { describe, it, expect } from 'vitest'
import { render, screen, fireEvent } from '@testing-library/react'
import Calculator from '../components/Calculator.jsx'

function clickButton(action) {
  const btn = screen.getByRole('button', { name: getLabel(action) })
  fireEvent.click(btn)
}

function getLabel(action) {
  const map = {
    '/': '\u00f7',
    '*': '\u00d7',
    '-': '\u2212',
    'clear': 'C',
    'backspace': '\u232b',
    'percent': '%',
    '=': '=',
    'memory-clear': 'MC',
    'memory-recall': 'MR',
    'memory-add': 'M+',
    'memory-sub': 'M\u2212',
  }
  return map[action] || action
}

function getDisplayValue() {
  return document.querySelector('.display-value').textContent
}

describe('Calculator', () => {
  it('renders and shows 0 initially', () => {
    render(<Calculator />)
    expect(getDisplayValue()).toBe('0')
  })

  it('enters single digit', () => {
    render(<Calculator />)
    clickButton('5')
    expect(getDisplayValue()).toBe('5')
  })

  it('enters multi-digit number', () => {
    render(<Calculator />)
    clickButton('1')
    clickButton('2')
    clickButton('3')
    expect(getDisplayValue()).toBe('123')
  })

  it('performs addition: 2 + 3 = 5', () => {
    render(<Calculator />)
    clickButton('2')
    clickButton('+')
    clickButton('3')
    clickButton('=')
    expect(getDisplayValue()).toBe('5')
  })

  it('performs subtraction: 5 - 3 = 2', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('-')
    clickButton('3')
    clickButton('=')
    expect(getDisplayValue()).toBe('2')
  })

  it('performs multiplication: 4 * 3 = 12', () => {
    render(<Calculator />)
    clickButton('4')
    clickButton('*')
    clickButton('3')
    clickButton('=')
    expect(getDisplayValue()).toBe('12')
  })

  it('performs division: 10 / 2 = 5', () => {
    render(<Calculator />)
    clickButton('1')
    clickButton('0')
    clickButton('/')
    clickButton('2')
    clickButton('=')
    expect(getDisplayValue()).toBe('5')
  })

  it('shows Error for division by zero', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('/')
    clickButton('0')
    clickButton('=')
    expect(getDisplayValue()).toBe('Error')
  })

  it('clears everything with C', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('+')
    clickButton('3')
    clickButton('=')
    clickButton('clear')
    expect(getDisplayValue()).toBe('0')
    // Operator should be cleared too - typing a new number and pressing = should do nothing
    clickButton('4')
    clickButton('=')
    expect(getDisplayValue()).toBe('4')
  })

  it('handles backspace on multi-digit', () => {
    render(<Calculator />)
    clickButton('1')
    clickButton('2')
    clickButton('3')
    clickButton('backspace')
    expect(getDisplayValue()).toBe('12')
  })

  it('handles backspace on single digit (shows 0)', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('backspace')
    expect(getDisplayValue()).toBe('0')
  })

  it('enters decimal number', () => {
    render(<Calculator />)
    clickButton('3')
    clickButton('.')
    clickButton('1')
    clickButton('4')
    expect(getDisplayValue()).toBe('3.14')
  })

  it('prevents multiple decimal points', () => {
    render(<Calculator />)
    clickButton('3')
    clickButton('.')
    clickButton('1')
    clickButton('.')
    clickButton('4')
    expect(getDisplayValue()).toBe('3.14')
  })

  it('calculates percentage: 50% = 0.5', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('0')
    clickButton('percent')
    expect(getDisplayValue()).toBe('0.5')
  })

  it('stores and recalls memory', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('memory-add')
    clickButton('clear')
    clickButton('memory-recall')
    expect(getDisplayValue()).toBe('5')
  })

  it('clears memory', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('memory-add')
    clickButton('memory-clear')
    clickButton('memory-recall')
    expect(getDisplayValue()).toBe('0')
  })

  it('handles chained operations: 2 + 3 + 4 = 9', () => {
    render(<Calculator />)
    clickButton('2')
    clickButton('+')
    clickButton('3')
    clickButton('+')
    // After pressing second +, display should show 5 (intermediate result)
    expect(getDisplayValue()).toBe('5')
    clickButton('4')
    clickButton('=')
    expect(getDisplayValue()).toBe('9')
  })

  it('adds entry to history after calculation', () => {
    render(<Calculator />)
    clickButton('2')
    clickButton('+')
    clickButton('3')
    clickButton('=')
    expect(screen.getByText('2 + 3 = 5')).toBeInTheDocument()
  })

  it('shows most recent history entry first', () => {
    render(<Calculator />)
    // First calculation
    clickButton('1')
    clickButton('+')
    clickButton('2')
    clickButton('=')
    // Second calculation
    clickButton('clear')
    clickButton('3')
    clickButton('+')
    clickButton('4')
    clickButton('=')

    const entries = document.querySelectorAll('.history-entry')
    expect(entries[0].textContent).toBe('3 + 4 = 7')
    expect(entries[1].textContent).toBe('1 + 2 = 3')
  })

  it('blocks input after Error until clear', () => {
    render(<Calculator />)
    clickButton('5')
    clickButton('/')
    clickButton('0')
    clickButton('=')
    expect(getDisplayValue()).toBe('Error')
    clickButton('3')
    expect(getDisplayValue()).toBe('Error')
    clickButton('clear')
    expect(getDisplayValue()).toBe('0')
  })
})
