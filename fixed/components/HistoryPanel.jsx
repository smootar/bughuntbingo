export default function HistoryPanel({ history }) {
  const reversed = [...history].reverse()

  return (
    <div className="history-panel">
      <div className="history-title">History</div>
      <div className="history-list">
        {reversed.length === 0 && (
          <div className="history-empty">No calculations yet</div>
        )}
        {reversed.map((entry, i) => (
          <div key={i} className="history-entry">{entry}</div>
        ))}
      </div>
    </div>
  )
}
