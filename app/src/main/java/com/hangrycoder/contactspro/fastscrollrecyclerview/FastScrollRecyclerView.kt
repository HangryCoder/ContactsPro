package com.hangrycoder.contactspro.fastscrollrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.hangrycoder.contactspro.utils.delayOnLifecycle
import java.util.*
import kotlin.math.floor

class FastScrollRecyclerView : RecyclerView {
    private var ctx: Context
    private var setupThings = false
    var scaledWidth = 0f
    var scaledHeight = 0f
    var sections: Array<Char?> = emptyArray()
    var sx = 0f
    var sy = 0f
    var section: Char? = null
    var showLetter = false

    constructor(context: Context) : super(context) {
        ctx = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        ctx = context
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        ctx = context
    }

    override fun onDraw(c: Canvas) {
        if (!setupThings) setupThings()
        super.onDraw(c)
    }

    private fun setupThings() {
        //create az text data
        val sectionSet: Set<Char>? =
            (adapter as FastScrollRecyclerViewInterface?)?.mapIndex?.keys
        sectionSet ?: return

        val listSection = ArrayList(sectionSet)
        listSection.sort()
        sections = arrayOfNulls(listSection.size)
        var i = 0
        for (s in listSection) {
            sections[i++] = s
        }
        scaledWidth = indWidth * ctx.resources.displayMetrics.density
        scaledHeight = indHeight * ctx.resources.displayMetrics.density
        sx = this.width - this.paddingRight - (1.2 * scaledWidth).toFloat()
        sy = ((this.height - scaledHeight * sections.size) / 2.0).toFloat()
        setupThings = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val fastScrollAdapter = adapter as FastScrollRecyclerViewInterface?
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size)
                    return super.onTouchEvent(event)
                else {
                    // We touched the index bar
                    val yy = y - this.paddingTop - paddingBottom - sy
                    var currentPosition = floor((yy / scaledHeight).toDouble()).toInt()
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition]
                    showLetter = true
                    var positionInData = 0
                    if (fastScrollAdapter?.mapIndex?.containsKey(section) == true) {
                        positionInData = fastScrollAdapter.mapIndex[section] ?: 0
                    }
                    scrollToPosition(positionInData)
                    this@FastScrollRecyclerView.invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (!showLetter && (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size))
                    return super.onTouchEvent(event)
                else {
                    val yy = y - sy
                    var currentPosition = floor((yy / scaledHeight).toDouble()).toInt()
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition]
                    showLetter = true
                    var positionInData = 0
                    if (fastScrollAdapter?.mapIndex?.containsKey(section) == true) {
                        positionInData = fastScrollAdapter.mapIndex[section] ?: 0
                    }
                    scrollToPosition(positionInData)
                    this@FastScrollRecyclerView.invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                delayOnLifecycle(SHOW_LETTER_DELAY_IN_MILLIS) {
                    showLetter = false
                    this@FastScrollRecyclerView.invalidate()
                }
                return if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size)
                    super.onTouchEvent(event) else true
            }
        }
        return true
    }

    companion object {
        var indWidth = 25
        var indHeight = 18
        var SHOW_LETTER_DELAY_IN_MILLIS = 100L
    }
}