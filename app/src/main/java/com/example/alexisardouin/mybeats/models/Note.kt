package com.example.alexisardouin.mybeats.models

class Note(val height: Float,
           val time: Float,
           val symbol: HeadShape,
           val stemMode: StemMode)

enum class HeadShape {
    DARK,
    CROSS
}

enum class StemMode {
    UPWARD,
    DOWNWARD,
    NONE
}