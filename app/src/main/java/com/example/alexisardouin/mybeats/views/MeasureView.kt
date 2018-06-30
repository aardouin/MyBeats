package com.example.alexisardouin.mybeats.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.alexisardouin.mybeats.R

class MeasureView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    val lineCount: Int = 9
    private val visibleLineCount: Int = 5
    private val invisibleLineCount: Int = lineCount - visibleLineCount


    var interlineHeight: Float = 0f
    private var lineColor: Int = 0

    private lateinit var paint: Paint

    private fun init() {
        lineColor = ContextCompat.getColor(context,R.color.lineColor)
        paint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        interlineHeight = measuredHeight.toFloat() / lineCount

        for (i in (invisibleLineCount) / 2 until lineCount - invisibleLineCount / 2) {
            paint.color = lineColor
            canvas?.drawLine(0f, (i*interlineHeight), measuredWidth.toFloat(), (i*interlineHeight),paint)
        }
    }
}
