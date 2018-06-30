package com.example.alexisardouin.mybeats.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.TouchDelegate
import android.view.View
import com.example.alexisardouin.mybeats.models.Note

/**
 * Created by Alexis on 30/06/2018.
 */
class NoteView : View {
    val note = Note(0f, 0f)
    var parentDelegate: TouchDelegate? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setBackgroundColor(Color.BLACK)
    }
}