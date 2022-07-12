package com.hangrycoder.contactspro.fastscrollrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.hangrycoder.contactspro.R
import java.util.*


class FastScrollRecyclerViewItemDecoration(private val mContext: Context) :
    RecyclerView.ItemDecoration() {

   override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val scaledWidth = (parent as FastScrollRecyclerView).scaledWidth
        val sx = (parent as FastScrollRecyclerView).sx
        val scaledHeight = (parent as FastScrollRecyclerView).scaledHeight
        val sy = (parent as FastScrollRecyclerView).sy
        val sections = (parent as FastScrollRecyclerView).sections
        val section = (parent as FastScrollRecyclerView).section
        val showLetter = (parent as FastScrollRecyclerView).showLetter

        // We draw the letter in the middle
        if (showLetter && section != null && section != "") {
            //overlay everything when displaying selected index Letter in the middle
            val overlayDark = Paint()
            overlayDark.color = Color.BLACK
            overlayDark.alpha = 100
            canvas.drawRect(0f, 0f, parent.width.toFloat(),
                parent.height.toFloat(), overlayDark)
            val middleTextSize =
                mContext.resources.getDimension(R.dimen.fast_scroll_overlay_text_size)
            val middleLetter = Paint()
            middleLetter.color = Color.WHITE
            middleLetter.textSize = middleTextSize
            middleLetter.isAntiAlias = true
            middleLetter.isFakeBoldText = true
            middleLetter.style = Paint.Style.FILL
            val xPos = (canvas.width - middleTextSize.toInt()) / 2
            val yPos =
                (canvas.height / 2 - (middleLetter.descent() + middleLetter.ascent()) / 2).toInt()
            canvas.drawText(
                section.uppercase(Locale.getDefault()),
                xPos.toFloat(),
                yPos.toFloat(),
                middleLetter
            )
        }
        // draw indez A-Z
        val textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        for (i in sections.indices) {
            if (showLetter && section != null && section != "" && sections[i]?.uppercase(
                    Locale.getDefault()
                ) == section.uppercase(Locale.getDefault())
            ) {
                textPaint.color = Color.WHITE
                textPaint.alpha = 255
                textPaint.isFakeBoldText = true
                textPaint.textSize = (scaledWidth / 2)
                canvas.drawText(
                    sections[i]!!.uppercase(Locale.getDefault()),
                    sx + textPaint.textSize / 2, sy + parent.paddingTop
                            + scaledHeight * (i + 1), textPaint
                )
                textPaint.textSize = scaledWidth
                canvas.drawText(
                    "•",
                    sx - textPaint.textSize / 3, sy + parent.paddingTop
                            + scaledHeight * (i + 1) + scaledHeight / 3, textPaint
                )
            } else {
                textPaint.color = Color.LTGRAY
                textPaint.alpha = 200
                textPaint.isFakeBoldText = false
                textPaint.textSize = scaledWidth / 2
                canvas.drawText(
                    sections[i]!!.uppercase(Locale.getDefault()),
                    sx + textPaint.textSize / 2, sy + parent.paddingTop
                            + scaledHeight * (i + 1), textPaint
                )
            }
        }
    }
}