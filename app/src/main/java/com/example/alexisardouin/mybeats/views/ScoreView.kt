package com.example.alexisardouin.mybeats.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.alexisardouin.mybeats.R

class ScoreView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private val lineCount: Int = 5

    private var interlineHeight : Float = 0f
    private var lineColor: Int = 0

    private lateinit var paint: Paint


    private fun init() {
        interlineHeight = this.resources.getDimension(R.dimen.score_interline_height)
        lineColor = ContextCompat.getColor(context,R.color.lineColor)
        paint = Paint()
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 0..lineCount) {
            paint.color = lineColor
            canvas?.drawLine(0f, (i*interlineHeight), measuredWidth.toFloat(), (i*interlineHeight),paint)
        }
    }
}
