package com.hangrycoder.contactspro.fastscrollrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hangrycoder.contactspro.R

class FastScrollAdapter(
    private val mDataset: ArrayList<String>,
    override val mapIndex: HashMap<String, Int>
) : RecyclerView.Adapter<FastScrollAdapter.ViewHolder?>(), FastScrollRecyclerViewInterface {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(  // each data item is just a string in this case
        var mTextView: TextView
    ) : RecyclerView.ViewHolder(mTextView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // create a new view
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fast_scroll_text_view, parent, false)
        // set the view's size, margins, paddings and layout parameters
//        ...
        return ViewHolder(v as TextView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.text = mDataset[position]
    }

    override fun getItemCount(): Int = mDataset.size
}