package com.steelkiwi.library.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Created by yaroslav on 6/8/17.
 */
class SquareView : View {

    private lateinit var drawable: GradientDrawable
    private var viewSize: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        drawable.setBounds(0, 0, right - left, bottom - top)
    }

    fun prepareGradientDrawable(startGradientColor : Int, endGradientColor : Int, cornerRadius: Int, viewSize: Int) {
        this.viewSize = viewSize
        drawable = GradientDrawable(startGradientColor, endGradientColor, cornerRadius)
        drawable.prepareGradientSize(viewSize)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewSize, viewSize)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    fun updateGradientPosition(position: Int) {
        drawable.setPositionOffset(position)
        invalidate()
    }
}