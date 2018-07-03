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
import com.example.alexisardouin.mybeats.models.Measure
import com.example.alexisardouin.mybeats.models.Note
import com.example.alexisardouin.mybeats.views.MeasureView
import com.example.alexisardouin.mybeats.views.NoteView
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity(){


    val lineCount = 8
    val positionCount: Int = (lineCount - 1) * 2
    val set = ConstraintSet()
    var touchDelegates: TouchDelegateComposite? = null

    var measureviewsMeasures = linkedMapOf<Int, Measure>()
    var noteviewsNotes = hashMapOf<Int, Note>()

    private var selectedNoteView: NoteView? = null
        set(value) {
            field?.setBackgroundColor(Color.BLACK)
            value?.setBackgroundColor(Color.BLUE)
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        touchDelegates = TouchDelegateComposite(sheet)
        sheet.touchDelegate = touchDelegates

        addMeasure()

        add_measure.setOnClickListener { addMeasure() }
        add_note.setOnClickListener { addNote() }

        up.setOnClickListener {
            noteviewsNotes[selectedNoteView?.id]?.moveUp()
            applyNoteConstraint()
        }

        down.setOnClickListener {
            noteviewsNotes[selectedNoteView?.id]?.moveDown(positionCount.toFloat())
            applyNoteConstraint()
        }
        left.setOnClickListener {
            noteviewsNotes[selectedNoteView?.id]?.moveLeft()
            applyNoteConstraint()
        }
        right.setOnClickListener {
            noteviewsNotes[selectedNoteView?.id]?.moveRight()
            applyNoteConstraint()
        }
    }


    private fun addMeasure() {
        val leftAnchor = measureviewsMeasures.keys.lastOrNull() ?: measure_anchor.id
        val measureView = MeasureView(this)
        measureView.id = View.generateViewId()
        sheet.addView(measureView)
        measureviewsMeasures[measureView.id] = Measure(listOf())

        set.connect(measureView.id, ConstraintSet.LEFT, leftAnchor, ConstraintSet.RIGHT)
        set.connect(measureView.id, ConstraintSet.TOP, leftAnchor, ConstraintSet.TOP)
        set.connect(measureView.id, ConstraintSet.BOTTOM, leftAnchor, ConstraintSet.BOTTOM)

        set.centerVertically(measureView.id, sheet.id)
        set.constrainHeight(measureView.id, 200)
        set.constrainWidth(measureView.id, 500)
        applyConstraints()
    }

    private fun addNote() {
        val measureId = measureviewsMeasures.keys.firstOrNull()
        measureId?.let { id ->
            val noteView = NoteView(this)
            noteView.id = View.generateViewId()
            sheet.addView(noteView)
            noteviewsNotes[noteView.id] = Note(0f, 0f)

            set.connect(noteView.id, ConstraintSet.LEFT, id, ConstraintSet.LEFT)
            set.connect(noteView.id, ConstraintSet.RIGHT, id, ConstraintSet.RIGHT)
            set.connect(noteView.id, ConstraintSet.TOP, id, ConstraintSet.TOP)
            set.connect(noteView.id, ConstraintSet.BOTTOM, id, ConstraintSet.BOTTOM)
            set.constrainPercentHeight(noteView.id, 1f / lineCount)
            set.setDimensionRatio(noteView.id, "1:1")
            selectedNoteView = noteView
            noteView.setOnClickListener {
                this.selectedNoteView = it as NoteView
            }
            applyNoteConstraint()
        }

    }

    private fun applyNoteConstraint() {
        selectedNoteView?.let { noteView ->
            val note = noteviewsNotes[noteView.id]
            note?.let { note ->
                set.setVerticalBias(noteView.id, note.height / positionCount)
                set.setHorizontalBias(noteView.id, note.position * 0.9f + 0.05f)

                val extraSpace = 20

                val touchableArea = Rect()
                noteView.getHitRect(touchableArea)
                touchableArea.top -= extraSpace
                touchableArea.bottom += extraSpace
                touchableArea.left -= extraSpace
                touchableArea.right += extraSpace
                touchDelegates?.removeDelegate(noteView.parentDelegate)
                noteView.parentDelegate = TouchDelegate(touchableArea, noteView)
                touchDelegates?.addDelegate(noteView.parentDelegate)
            }
        }
        applyConstraints()
    }

    private fun applyConstraints() {
        set.applyTo(sheet)
    }

}