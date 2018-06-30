package com.example.alexisardouin.mybeats.controllers

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.TouchDelegate
import android.view.View
import com.example.alexisardouin.mybeats.R
import com.example.alexisardouin.mybeats.TouchDelegateComposite
import com.example.alexisardouin.mybeats.views.NoteView
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity(){

    val set = ConstraintSet()
    var touchDelegates: TouchDelegateComposite? = null

    private var selectedNoteView: NoteView? = null
        set(value) {
            field?.setBackgroundColor(Color.BLACK)
            value?.setBackgroundColor(Color.BLUE)
            field = value
        }

    private var positionCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        positionCount = (measure.lineCount - 1) * 2
        touchDelegates = TouchDelegateComposite(sheet)
        sheet.touchDelegate = touchDelegates


        add.setOnClickListener {
            val noteView = NoteView(this)
            noteView.id = View.generateViewId()
            sheet.addView(noteView)
            set.connect(noteView.id, ConstraintSet.LEFT, measure.id, ConstraintSet.LEFT)
            set.connect(noteView.id, ConstraintSet.RIGHT, measure.id, ConstraintSet.RIGHT)
            set.connect(noteView.id, ConstraintSet.TOP, measure.id, ConstraintSet.TOP)
            set.connect(noteView.id, ConstraintSet.BOTTOM, measure.id, ConstraintSet.BOTTOM)
            set.constrainPercentHeight(noteView.id, 1 / (measure.lineCount * 4f))
            set.setDimensionRatio(noteView.id, "1:1")
            selectedNoteView = noteView
            noteView.setOnClickListener {
                this.selectedNoteView = it as NoteView
            }
            applyNoteConstraint()
        }

        up.setOnClickListener {
            selectedNoteView?.note?.moveUp()
            applyNoteConstraint()
        }

        down.setOnClickListener {
            selectedNoteView?.note?.moveDown(positionCount.toFloat())
            applyNoteConstraint()
        }
        left.setOnClickListener {
            selectedNoteView?.note?.moveLeft()
            applyNoteConstraint()
        }
        right.setOnClickListener {
            selectedNoteView?.note?.moveRight()
            applyNoteConstraint()
        }
    }

    private fun applyNoteConstraint() {
        selectedNoteView?.let {
            set.setVerticalBias(it.id, it.note.height / positionCount)
            set.setHorizontalBias(it.id, it.note.position)

            val extraSpace = 150

            val touchableArea = Rect()
            it.getHitRect(touchableArea)
            touchableArea.top -= extraSpace
            touchableArea.bottom += extraSpace
            touchableArea.left -= extraSpace
            touchableArea.right += extraSpace
            touchDelegates?.removeDelegate(it.parentDelegate)
            it.parentDelegate = TouchDelegate(touchableArea, it)
            touchDelegates?.addDelegate(it.parentDelegate)
        }
        set.applyTo(sheet)
    }
}