package com.example.alexisardouin.mybeats.models

import kotlin.math.max
import kotlin.math.min

class Note(var height: Float,
           var position: Float) {
    fun moveUp() {
        height = max(height - 1f, 0f)
    }

    fun moveDown(positionCount: Float) {
        height = min(height + 1f, positionCount)
    }

    fun moveLeft() {
        position = max(position - 0.1f, 0f)
    }

    fun moveRight() {
        position = min(position + 0.1f, 1f)
    }
}

enum class HeadShape {
    DARK,
    CROSS
}

enum class StemMode {
    UPWARD,
    DOWNWARD,
    NONE
}