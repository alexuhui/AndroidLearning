package com.example.firstlineandroidcode.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlineandroidcode.R

class FruitAdapterGrid(private val fruitList: List<FruitItem>) : RecyclerView.Adapter<FruitAdapterGrid.ViewHolder>() {

    private var itemLayout : Int = R.layout.fruit_grid

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage3)
        val fruitName: TextView = view.findViewById(R.id.fruitName3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener(){
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "OnClick item : ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitImage.setOnClickListener(){
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "OnClick image : ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitName.setOnClickListener(){
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "OnClick name : ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.image)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount() = fruitList.size
}