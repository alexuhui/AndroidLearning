package com.example.firstlineandroidcode.workmanager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlineandroidcode.R
import com.example.firstlineandroidcode.extensions.loadImageFromNet

class ThumbnailAdapter(private val fruitList: List<Thumbnail>) : RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>()  {
    private val TAG = "ThumbnailAdapter"
    private var itemLayout : Int = R.layout.thumbnail

    private lateinit var owner : Context

    fun setOwner(context: Context){
        owner = context
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.thumbnailImage)
        val fruitName: TextView = view.findViewById(R.id.imageName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener(){
            val position = viewHolder.bindingAdapterPosition
            val fruit = fruitList[position]
//            Toast.makeText(parent.context, "OnClick item : ${fruit.getName()}", Toast.LENGTH_SHORT).show()
            val name = fruit.getName()
            Log.d(TAG, "OnClick item:  $name")
            OriginalImageActivity.show(parent.context, name)
        }

        return viewHolder
    }

    override fun getItemCount() = fruitList.size

    override fun onBindViewHolder(holder: ThumbnailAdapter.ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.loadImageFromNet(owner, fruit.getImageUrl())
        holder.fruitName.text = fruit.getName()
    }
}