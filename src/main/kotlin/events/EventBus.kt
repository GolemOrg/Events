package events

class EventBus<T> {
    private val listeners: MutableMap<Any, (T) -> Unit> = mutableMapOf()

    fun listen(listener: (T) -> Unit) = listeners.put(listener.hashCode(), listener)
    fun listen(owner: Any, listener: (T) -> Unit) = listeners.put(owner, listener)

    fun dispatch(event: T) = listeners.values.forEach { it(event) }

    fun remove(listener: (T) -> Unit) = listeners.remove(listener.hashCode())
    fun remove(owner: Any) = listeners.remove(owner)

    fun clear() = listeners.clear()
}