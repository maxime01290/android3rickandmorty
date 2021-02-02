package com.example.rickandmortyapplication.Main.Adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.ViewHolder.ViewHolderManager

class AdapterItem(context: Context, list: ArrayList<BaseClass>,onitemClickListener:ViewHolderManager.BaseInterfaceOnItemClickListener) : RecyclerView.Adapter<ViewHolderManager.BaseViewHolder<BaseClass>>() {
    var list: ArrayList<BaseClass>
    var context: Context
    var onitemClickListener:ViewHolderManager.BaseInterfaceOnItemClickListener

    init {
        this.list = list
        this.context = context
        this.onitemClickListener = onitemClickListener
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return ViewHolderManager.getItemViewTypeVH(getClassName())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderManager.BaseViewHolder<BaseClass> {
        return ViewHolderManager.createVH(viewType,context,onitemClickListener,parent)
    }

    override fun onBindViewHolder(holder: ViewHolderManager.BaseViewHolder<BaseClass>, position: Int) {
        if(list[position] is Character){
            holder.bind(list[position] as Character)
        }else if(list[position] is Favory) {
            holder.bind(list[position]as Favory)
        }else{
            if(list[position] is Episodes){
                holder.bind(list[position] as Episodes)
            }
            else
            {
                holder.bind(list[position] as Locations)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getClassName():String{
        if(list[0] is Character|| list[0] is Favory){
            return "character"
        }else{
            return "other"
        }
    }

    fun submit(listOfItem: ArrayList<BaseClass>) {
        this.list.clear()
        this.list.addAll(listOfItem)
        this.notifyDataSetChanged()
    }
}

