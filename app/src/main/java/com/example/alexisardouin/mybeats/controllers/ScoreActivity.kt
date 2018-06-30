package com.example.alexisardouin.mybeats.controllers

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.alexisardouin.mybeats.R
import com.example.alexisardouin.mybeats.views.NoteView
import kotlinx.android.synthetic.main.activity_score.*
import kotlin.math.max
import kotlin.math.min

class ScoreActivity : AppCompatActivity(){

    var height = 0f
    var position = 0f
    val set = ConstraintSet()

    private lateinit var note: NoteView

    private var positionCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        note = NoteView(this)
        note.id = View.generateViewId()
        sheet.addView(note)
        set.connect(note.id, ConstraintSet.LEFT, measure.id, ConstraintSet.LEFT)
        set.connect(note.id, ConstraintSet.RIGHT, measure.id, ConstraintSet.RIGHT)
        set.connect(note.id, ConstraintSet.TOP, measure.id, ConstraintSet.TOP)
        set.connect(note.id, ConstraintSet.BOTTOM, measure.id, ConstraintSet.BOTTOM)
        set.constrainPercentHeight(note.id, 1 / (measure.lineCount * 4f))
        applyNoteConstraint(note);
        positionCount = (measure.lineCount - 1) * 2

        up.setOnClickListener {
            height = max(height - 1f, 0f)
            applyNoteConstraint(note)
        }
        down.setOnClickListener {
            height = min(height + 1f, positionCount.toFloat())
            applyNoteConstraint(note)
        }
        left.setOnClickListener {
            position = max(position - 0.1f, 0f)
            applyNoteConstraint(note)
        }
        right.setOnClickListener {
            position = min(position + 0.1f, 1f)
            applyNoteConstraint(note)
        }
    }

    private fun applyNoteConstraint(note: View) {
        set.setDimensionRatio(note.id, "1:1")
        set.setVerticalBias(note.id, height / positionCount)
        set.setHorizontalBias(note.id, position)
        set.applyTo(sheet)
    }
}