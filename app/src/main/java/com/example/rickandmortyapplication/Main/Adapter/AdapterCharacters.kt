package com.example.rickandmortyapplication.Main.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.R

class AdapterCharacters(context: Context, list: ArrayList<Character>,onitemClickListener:onItemClickListener) : RecyclerView.Adapter<AdapterCharacters.MyVH>() {
    var list: ArrayList<Character>
    var context: Context
    var onitemClickListener:onItemClickListener
    //var onitemLongClickListener:onItemLongClickListener

    init {
        //this.onitemLongClickListener= onItemLongClickListener
        this.list = list
        this.context = context
        this.onitemClickListener = onitemClickListener
    }

    class MyVH(view: View, onitemClickListener: onItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener,View.OnLongClickListener {
        var tv1 : TextView
        var tv2 : TextView
        var tv3 : TextView
        var iv :ImageView
        var onItemClickListener:onItemClickListener
        //var onitemLongClickListener:onItemLongClickListener
        init {
            tv1 = view.findViewById(R.id.textView_name_characters)
            tv2 = view.findViewById(R.id.textView_data)
            tv3 = view.findViewById(R.id.textView_last_position)
            iv = view.findViewById(R.id.imageView_characters)
            //this.onitemLongClickListener = onItemLongClickListener
            this.onItemClickListener = onitemClickListener
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            onItemClickListener.onItemLongClick(adapterPosition)
            return true
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val context = parent.context
        val layout:View = LayoutInflater.from(context)
                .inflate(R.layout.component_list_characters, parent, false)
        return MyVH(layout,onitemClickListener)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
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

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    fun submit(listOfCharacter: ArrayList<Character>) {
        this.list.clear()
        this.list.addAll(listOfCharacter)
        this.notifyDataSetChanged()
    }

}

