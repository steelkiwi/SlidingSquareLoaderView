package com.steelkiwi.library.view

import android.graphics.*
import android.graphics.drawable.Drawable
import com.steelkiwi.library.util.Constant

/**
 * Created by yaroslav on 6/14/17.
 */
class GradientDrawable(val startGradientColor: Int, val endGradientColor: Int, val cornerRadius: Int) : Drawable() {

    private lateinit var paint: Paint
    private var width: Int = 0
    private var position: Int = 0

    private fun preparePaint(width: Int) {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val linerGradient = LinearGradient(0f, 0f, width.toFloat() *
                Constant.VIEW_COUNT, 0f, startGradientColor, endGradientColor, Shader.TileMode.CLAMP)
        paint.shader = linerGradient
    }

    override fun draw(canvas: Canvas?) {
        val viewBounds = bounds
        canvas?.save()
        translateCanvas(canvas, position)
        val left = viewBounds.left.toFloat() - (position * width.toFloat())
        val top = viewBounds.top.toFloat()
        val right = viewBounds.right.toFloat() - (position * width.toFloat())
        val bottom = viewBounds.bottom.toFloat()
        canvas?.drawRoundRect(left, top, right, bottom, cornerRadius.toFloat(), cornerRadius.toFloat(), paint)
        canvas?.restore()
    }

    override fun setAlpha(p0: Int) {
        // no need
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(p0: ColorFilter?) {
        // no need
    }

    fun prepareGradientSize(width: Int) {
        this.width = width
        preparePaint(width)
    }

    fun translateCanvas(canvas: Canvas?, position: Int) {
        canvas?.translate(position * width.toFloat(), 0f)
    }

    fun setPositionOffset(position: Int) {
        this.position = position
    }
}