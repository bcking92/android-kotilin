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

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item = myDataset[position]
        holder.apply {
            bind(item)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bind(item:myDataset) {
            view.imageView.setImageDrawable(item.item_image)
            view.textView.text = item.item_title
        }
    }


}
