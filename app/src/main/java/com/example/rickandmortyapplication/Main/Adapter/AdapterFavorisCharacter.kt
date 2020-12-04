package com.example.rickandmortyapplication.Main.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapplication.R

class AdapterFavorisCharacter(context: Context, list: ArrayList<com.example.rickandmortyapplication.Main.Model.Character.Character>):RecyclerView.Adapter<AdapterFavorisCharacter.MyVH>(){

    private var context: Context = context
    private var list:ArrayList<com.example.rickandmortyapplication.Main.Model.Character.Character>

    init {
        this.list = list
        this.context = context
    }
    class MyVH(view: View) : RecyclerView.ViewHolder(view) {
        var tv1 : TextView
        var tv2 : TextView
        var tv3 : TextView
        var iv : ImageView

        init {
            tv1 = view.findViewById(R.id.textView_name_characters)
            tv2 = view.findViewById(R.id.textView_data)
            tv3 = view.findViewById(R.id.textView_last_position)
            iv = view.findViewById(R.id.imageView_characters)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val context = parent.context
        val layout: View = LayoutInflater.from(context)
                .inflate(R.layout.component_list_characters, parent, false)
        return MyVH(layout)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.iv.load(list[position].image)
        holder.tv1.setText(list[position].name)
        val species = list[position].species
        val status =  list[position].status
        holder.tv2.setText("$species - $status")
        val locationName = list[position].location?.name
        holder.tv3.setText("Last position : $locationName")
    }

    override fun getItemCount(): Int {
        return list.size
    }

}