package com.example.rickandmortyapplication.Main.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.R

class AdapterLocations constructor(context: Context, list:ArrayList<Locations>):RecyclerView.Adapter<AdapterLocations.MyVH>() {

    private var context: Context = context
    private var list:ArrayList<Locations> = list

    init {
        this.list = list
        this.context = context
    }
    class MyVH(view:View) : RecyclerView.ViewHolder(view) {
        var tv1 : TextView
        var tv2 : TextView
        var tv3 : TextView

        init {
            tv1 = view.findViewById(R.id.textView_title_component_list)
            tv2 = view.findViewById(R.id.textView_donnees_component_list)
            tv3 = view.findViewById(R.id.textView_complementary_data_component_list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLocations.MyVH {
        val context = parent.context
        val layout:View = LayoutInflater.from(context)
            .inflate(R.layout.component_list, parent, false)

        return MyVH(layout)
    }

    override fun onBindViewHolder(holder: AdapterLocations.MyVH, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv1.setText(list[position]?.name)
        val type = list[position]?.type
        val dimension = list[position]?.dimension
        holder.tv2.setText("$type - $dimension")
        val created = list[position]?.created
        holder.tv3.setText("Created in : $created")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submit(listOfCharacter: ArrayList<Locations>) {
        this.list.clear()
        this.list.addAll(listOfCharacter)
        this.notifyDataSetChanged()
    }
}