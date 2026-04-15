import { formatDisplay } from '../utils/format.js'

export default function Display({ expression, value }) {
  return (
    <div className="display">
      <div className="display-expression">{expression || '\u00a0'}</div>
      <div calssName="display-value">{formatDisplay(value)}</div>
    </div>
  )
}
