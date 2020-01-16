package com.example.myfirstapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(val context: Context, val myDataset: List<myDataset>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    private val arrayList:List<myDataset> = null

    public class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false) {
        val imageView = itemView.imageView
        val textView = itemView.textView
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        // set the view's size, margins, paddings and layout parameters
        val holder:MyViewHolder(view)

        return holder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position].item_title
        holder.imageView.text = myDataset[position].item_image
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


}
