package com.master.machines.allMovies.framework.utils

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import androidx.annotation.ColorInt
import java.util.Locale

class TextDrawable(
    @ColorInt backgroundColor: Int, private val mText: String,
    @ColorInt mTextColor: Int, mTextSize: Float, typeFace: Typeface?
) : Drawable() {

    private val mBadgePaint = Paint()
    private val mTextPaint: TextPaint = TextPaint()
    private val mTxtRect: Rect = Rect()

    init {
        mBadgePaint.apply {
            color = backgroundColor
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        mTextPaint.apply {
            color = mTextColor
            typeface = typeFace
            textSize = mTextSize
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }

    override fun draw(canvas: Canvas) {
        val bounds: Rect = bounds
        val width: Float = (bounds.right - bounds.left).toFloat()
        val height: Float = (bounds.bottom - bounds.top).toFloat()
        val radius = width.coerceAtMost(height) / 2 - 1
        val centerX = bounds.centerX().toFloat()
        val centerY = bounds.centerY().toFloat()
        canvas.drawCircle(centerX, centerY, radius, mBadgePaint)
        mTextPaint.getTextBounds(
            mText.uppercase(Locale.getDefault()), 0, mText.length, mTxtRect
        )
        val textHeight: Int = mTxtRect.bottom - mTxtRect.top
        val textY = centerY + textHeight / 2f
        canvas.drawText(mText.uppercase(Locale.getDefault()), centerX, textY, mTextPaint)
    }

    override fun setAlpha(alpha: Int) {
        mBadgePaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mBadgePaint.colorFilter = cf
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
}