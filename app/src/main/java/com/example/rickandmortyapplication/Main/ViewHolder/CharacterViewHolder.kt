package com.example.rickandmortyapplication.Main.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.R

//gère le bind de l'adapter pour les classes Character et les favoris
class CharacterViewHolder (view: View, onitemClickListener: ViewHolderManager.BaseInterfaceOnItemClickListener) : ViewHolderManager.BaseViewHolder<BaseClass>(view), View.OnClickListener,
        View.OnLongClickListener {
    var tv1 : TextView
    var tv2 : TextView
    var tv3 : TextView
    var iv : ImageView
    var onItemClickListener: ViewHolderManager.BaseInterfaceOnItemClickListener

    init {
        tv1 = view.findViewById(R.id.textView_name_characters)
        tv2 = view.findViewById(R.id.textView_data)
        tv3 = view.findViewById(R.id.textView_last_position)
        iv = view.findViewById(R.id.imageView_characters)
        this.onItemClickListener = onitemClickListener
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemClickListener.onItemClick(adapterPosition)
    }

    override fun onLongClick(v: View?): Boolean {
        onItemClickListener.onItemLongClick(adapterPosition)
        return true
    }

    override fun bind(item: Character) {
        iv.load(item.image)
        tv1.setText(item.nameCharacter)
        val species = item.species
        val status = item.status
        tv2.setText("$species - $status")
        val locationName = item.location?.nameLocation
        tv3.setText("Last position : $locationName")
    }

    override fun bind(item: Episodes) {}

    override fun bind(item: Locations) {}

    override fun bind(item: Favory) {
        iv.load(item.image)
        tv1.setText(item.nameCharacter)
        val species = item.species
        val status = item.status
        tv2.setText("$species - $status")
        val locationName = item.location?.nameLocation
        tv3.setText("Last position : $locationName")
    }
}