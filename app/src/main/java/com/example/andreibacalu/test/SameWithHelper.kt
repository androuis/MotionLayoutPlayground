package com.example.andreibacalu.test

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

class SameWithHelper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun updatePostMeasure(container: ConstraintLayout) {
        var maxWidth = 0

        // Find the greatest width of the referenced widgets.
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val child = container.getViewById(id)
            val widget = container.getViewWidget(child)
            if (widget.width > maxWidth) {
                maxWidth = widget.width
            }

            val x = IntArray(5)
            x[0] = 1
        }

        // Set the width of all referenced view to the width of the view with the greatest width.
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val child = container.getViewById(id)
            if (child.width != width) {
                val layoutParams = child.layoutParams
                layoutParams.width = width
                child.layoutParams = layoutParams
            }
        }
    }
}