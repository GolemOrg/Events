# Events

A small event bus system used as a way to push/pull data from a central location.

## Usage
Using the event system is as simple as:
```kt
val bus = EventBus<Int>()

bus.listen {
    println("Received number: $it")
}

for(i in 1..100) {
    bus.dispatch(i)
}
```
