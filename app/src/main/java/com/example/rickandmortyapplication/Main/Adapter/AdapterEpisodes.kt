package com.example.rickandmortyapplication.Main.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.R

class AdapterEpisodes constructor(context: Context,list: ArrayList<Episodes>):RecyclerView.Adapter<AdapterEpisodes.MyVH>() {
    var list: ArrayList<Episodes> = list
    var context:Context = context

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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val context = parent.context
        val layout:View = LayoutInflater.from(context)
                .inflate(R.layout.component_list, parent, false)

        return MyVH(layout)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.tv1.setText(list[position].name)
        val episode = list[position].episode
        val airdate =  list[position].airDate
        holder.tv2.setText("$episode - $airdate")
        val created = list[position].created
        holder.tv3.setText("Created in : $created")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submit(listOfCharacter: ArrayList<Episodes>) {
        this.list.clear()
        this.list.addAll(listOfCharacter)
        this.notifyDataSetChanged()
    }
}