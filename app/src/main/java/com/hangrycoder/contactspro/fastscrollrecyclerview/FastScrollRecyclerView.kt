package com.hangrycoder.contactspro.fastscrollrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class FastScrollRecyclerView : RecyclerView {
    private var ctx: Context
    private var setupThings = false
    var scaledWidth = 0f
    var scaledHeight = 0f
    lateinit var sections: Array<String?>
    var sx = 0f
    var sy = 0f
    var section: String? = null
    var showLetter = false
    private var listHandler: Handler? = null

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
        val sectionSet: Set<String>? = //emptySet()
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
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size) return super.onTouchEvent(
                    event
                ) else {
                    // We touched the index bar
                    val yy = y - this.paddingTop - paddingBottom - sy
                    var currentPosition = Math.floor((yy / scaledHeight).toDouble()).toInt()
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition]
                    showLetter = true
                    var positionInData = 0
                    if ((adapter as FastScrollRecyclerViewInterface?)!!.mapIndex!!.containsKey(
                            section!!.uppercase(Locale.getDefault())
                        )
                    ) positionInData =
                        (adapter as FastScrollRecyclerViewInterface?)!!.mapIndex!![section!!.uppercase(
                            Locale.getDefault()
                        )]!!
                    scrollToPosition(positionInData)
                    this@FastScrollRecyclerView.invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (!showLetter && (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size)) return super.onTouchEvent(
                    event
                ) else {
                    val yy = y - sy
                    var currentPosition = Math.floor((yy / scaledHeight).toDouble()).toInt()
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition]
                    showLetter = true
                    var positionInData = 0
                    if ((adapter as FastScrollRecyclerViewInterface?)!!.mapIndex!!.containsKey(
                            section!!.uppercase(Locale.getDefault())
                        )
                    ) positionInData =
                        (adapter as FastScrollRecyclerViewInterface?)!!.mapIndex!![section!!.uppercase(
                            Locale.getDefault()
                        )]!!
                    scrollToPosition(positionInData)
                    this@FastScrollRecyclerView.invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                listHandler = ListHandler()
                listHandler!!.sendEmptyMessageDelayed(0, 100)
                return if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size) super.onTouchEvent(
                    event
                ) else true
            }
        }
        return true
    }

    private inner class ListHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            showLetter = false
            this@FastScrollRecyclerView.invalidate()
        }
    }

    companion object {
        var indWidth = 25
        var indHeight = 18
    }
}