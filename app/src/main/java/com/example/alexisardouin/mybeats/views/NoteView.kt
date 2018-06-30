package com.example.alexisardouin.mybeats.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

/**
 * Created by Alexis on 30/06/2018.
 */
class NoteView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setBackgroundColor(Color.BLACK)
    }

    fun increaseHeight() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}