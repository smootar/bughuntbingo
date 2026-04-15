export default function HistoryPanel({ history }) {
  return (
    <div className="history-panel">
      <div className="history-title">History</div>
      <div className="history-list">
        {history.length === 0 && (
          <div className="history-empty">No calculations yet</div>
        )}
        {history.map((entry, i) => (
          <div key={i} className="history-entry">{entry}</div>
        ))}
      </div>
    </div>
  )
}
