package com.example.alexisardouin.mybeats

import android.graphics.Rect
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View


/**
 * Created by Alexis on 30/06/2018.
 */

class TouchDelegateComposite(view: View) : TouchDelegate(emptyRect, view) {

    private val delegates = ArrayList<TouchDelegate>()

    fun addDelegate(delegate: TouchDelegate?) {
        if (delegate != null) {
            delegates.add(delegate)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var res = false
        val x = event.x
        val y = event.y
        for (delegate in delegates) {
            event.setLocation(x, y)
            res = delegate.onTouchEvent(event) || res
        }
        return res
    }

    fun removeDelegate(parentDelegate: TouchDelegate?) {
        delegates.remove(parentDelegate)
    }

    companion object {
        private val emptyRect = Rect()
    }

}