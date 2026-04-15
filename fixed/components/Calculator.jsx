import { useState } from 'react'
import Display from './Display.jsx'
import ButtonPanel from './ButtonPanel.jsx'
import HistoryPanel from './HistoryPanel.jsx'
import MemoryIndicator from './MemoryIndicator.jsx'
import { evaluate, percent } from '../utils/calculate.js'
import { formatHistoryEntry } from '../utils/format.js'

export default function Calculator() {
  const [display, setDisplay] = useState('0')
  const [previousOperand, setPreviousOperand] = useState(null)
  const [currentOperator, setCurrentOperator] = useState(null)
  const [waitingForOperand, setWaitingForOperand] = useState(false)
  const [memory, setMemory] = useState(0)
  const [history, setHistory] = useState([])
  const [expression, setExpression] = useState('')

  function handleDigit(digit) {
    if (waitingForOperand) {
      setDisplay(digit)
      setWaitingForOperand(false)
    } else {
      setDisplay(display === '0' ? digit : display + digit)
    }
  }

  function handleDecimal() {
    if (waitingForOperand) {
      setDisplay('0.')
      setWaitingForOperand(false)
      return
    }
    if (display.includes('.')) return
    setDisplay(display + '.')
  }

  function handleOperator(nextOperator) {
    const current = parseFloat(display)

    if (previousOperand !== null && currentOperator && !waitingForOperand) {
      const result = evaluate(previousOperand, currentOperator, current)
      if (result === 'Error') {
        setDisplay('Error')
        setPreviousOperand(null)
        setCurrentOperator(null)
        setWaitingForOperand(false)
        return
      }
      setDisplay(String(result))
      setPreviousOperand(result)
    } else {
      setPreviousOperand(current)
    }

    setCurrentOperator(nextOperator)
    setWaitingForOperand(true)
    setExpression(`${previousOperand !== null && currentOperator && !waitingForOperand ? evaluate(previousOperand, currentOperator, current) : current} ${nextOperator}`)
  }

  function handleEquals() {
    if (previousOperand === null || currentOperator === null) return

    const current = parseFloat(display)
    const result = evaluate(previousOperand, currentOperator, current)

    const entry = formatHistoryEntry(previousOperand, currentOperator, current, result)
    setHistory([...history, entry])

    setDisplay(String(result))
    setExpression('')
    setPreviousOperand(null)
    setCurrentOperator(null)
    setWaitingForOperand(false)
  }

  function handleClear() {
    setDisplay('0')
    setPreviousOperand(null)
    setCurrentOperator(null)
    setWaitingForOperand(false)
    setExpression('')
  }

  function handleBackspace() {
    if (waitingForOperand || display === 'Error') return
    const result = display.slice(0, -1)
    setDisplay(result === '' ? '0' : result)
  }

  function handlePercent() {
    const current = parseFloat(display)
    const result = percent(current)
    setDisplay(String(result))
  }

  function handleMemoryAdd() {
    setMemory(memory + parseFloat(display))
  }

  function handleMemorySub() {
    setMemory(memory - parseFloat(display))
  }

  function handleMemoryRecall() {
    setDisplay(String(memory))
    setWaitingForOperand(false)
  }

  function handleMemoryClear() {
    setMemory(0)
  }

  function handleButton(action) {
    if (display === 'Error' && action !== 'clear') {
      return
    }

    switch (action) {
      case '0': case '1': case '2': case '3': case '4':
      case '5': case '6': case '7': case '8': case '9':
        handleDigit(action)
        break
      case '.':
        handleDecimal()
        break
      case '+': case '-': case '*': case '/':
        handleOperator(action)
        break
      case '=':
        handleEquals()
        break
      case 'clear':
        handleClear()
        break
      case 'backspace':
        handleBackspace()
        break
      case 'percent':
        handlePercent()
        break
      case 'memory-add':
        handleMemoryAdd()
        break
      case 'memory-sub':
        handleMemorySub()
        break
      case 'memory-recall':
        handleMemoryRecall()
        break
      case 'memory-clear':
        handleMemoryClear()
        break
    }
  }

  return (
    <div className="calculator">
      <MemoryIndicator hasMemory={memory !== 0} />
      <Display expression={expression} value={display} />
      <ButtonPanel onButton={handleButton} />
      <HistoryPanel history={history} />
    </div>
  )
}
