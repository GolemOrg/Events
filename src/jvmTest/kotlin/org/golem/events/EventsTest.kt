package org.golem.events

import org.junit.Test

sealed class PlayerEvent {
    object Join : PlayerEvent()
    object Leave : PlayerEvent()
    class Move(val x: Int, val y: Int) : PlayerEvent()
}

class EventsTest {

    @Test
    fun testPlayerEvent() {
        val bus = EventBus<PlayerEvent>()
        bus.listen(this) {
            when (it) {
                is PlayerEvent.Join -> println("Player joined")
                is PlayerEvent.Leave -> println("Player left")
                is PlayerEvent.Move -> println("Player moved to (${it.x}, ${it.y})")
            }
        }

        bus.dispatch(PlayerEvent.Join) // they joined
        for (i in 1..10) {
            bus.dispatch(PlayerEvent.Move(i, i)) // they moved
        }
        bus.dispatch(PlayerEvent.Leave) // they left
        bus.remove(this)

    }

    @Test
    fun testInt() {
        val bus = EventBus<Int>()
        bus.listen { number -> println("Received number: $number") }

        for(i in 1..10) {
            bus.dispatch(i)
        }
    }

}