const BUTTONS = [
  { label: 'MC', type: 'memory', action: 'memory-clear' },
  { label: 'MR', type: 'memory', action: 'memory-recall' },
  { label: 'M+', type: 'memory', action: 'memory-add' },
  { label: 'M\u2212', type: 'memory', action: 'memory-sub' },

  { label: 'C', type: 'clear', action: 'clear' },
  { label: '\u232b', type: 'clear', action: 'backspace' },
  { label: '%', type: 'operator', action: 'percent' },
  { label: '\u00f7', type: 'operator', action: '/' },

  { label: '7', type: 'digit', action: '7' },
  { label: '8', type: 'digit', action: '8' },
  { label: '9', type: 'digit', action: '9' },
  { label: '\u00d7', type: 'operator', action: '*' },

  { label: '4', type: 'digit', action: '4' },
  { label: '5', type: 'digit', action: '5' },
  { label: '6', type: 'digit', action: '6' },
  { label: '\u2212', type: 'operator', action: '-' },

  { label: '1', type: 'digit', action: '1' },
  { label: '2', type: 'digit', action: '2' },
  { label: '3', type: 'digit', action: '3' },
  { label: '+', type: 'operator', action: '+' },

  { label: '0', type: 'digit', action: '0', wide: true },
  { label: '.', type: 'digit', action: '.' },
  { label: '=', type: 'equals', action: '=' },
]

export default function ButtonPanel({ onButton }) {
  return (
    <div className="button-panel">
      {BUTTONS.map((btn) => (
        <button
          key={btn.action + btn.label}
          className={`btn btn-${btn.type}${btn.wide ? ' btn-wide' : ''}`}
          onClick={() => onButton(btn.action)}
          data-action={btn.action}
        >
          {btn.label}
        </button>
      ))}
    </div>
  )
}
