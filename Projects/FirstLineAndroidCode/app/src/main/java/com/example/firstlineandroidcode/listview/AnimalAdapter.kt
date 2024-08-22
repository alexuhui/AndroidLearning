package com.example.firstlineandroidcode.listview

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firstlineandroidcode.R

class AnimalAdapter(activity: Activity, val resourceId: Int, data: List<AnimalItem>) :
    ArrayAdapter<AnimalItem>(activity, resourceId, data) {

        inner class ViewHolder(val animalImage:ImageView, val animalName:TextView)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view : View
            val viewHolder : ViewHolder
            if (convertView == null){
                view = LayoutInflater.from(context).inflate(resourceId, parent, false)
                val animalImage: ImageView = view.findViewById(R.id.animalImage)
                val animalName: TextView = view.findViewById(R.id.animalName)
                viewHolder = ViewHolder(animalImage, animalName)
                view.tag = viewHolder
            }else{
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
            val animal = getItem(position) // 获取当前项的Animal实例
            if (animal != null) {
                viewHolder.animalImage.setImageResource(animal.image)
                viewHolder.animalName.text = animal.name
            }
            return view
        }

}