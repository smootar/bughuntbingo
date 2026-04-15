export default function MemoryIndicator({ hasMemory }) {
  return (
    <div className="memory-indicator">
      {hasMemory ? 'M' : '\u00a0'}
    </div>
  )
}
