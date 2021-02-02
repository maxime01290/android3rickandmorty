package com.example.rickandmortyapplication.Main.ViewHolder

import android.view.View
import android.widget.TextView
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.R

class OtherViewHolder(view: View) : ViewHolderManager.BaseViewHolder<BaseClass>(view) {
    var tv1 : TextView
    var tv2 : TextView
    var tv3 : TextView

    init {
        tv1 = view.findViewById(R.id.textView_title_component_list)
        tv2 = view.findViewById(R.id.textView_donnees_component_list)
        tv3 = view.findViewById(R.id.textView_complementary_data_component_list)
    }

    override fun bind(item: Character) {}

    override fun bind(item: Episodes) {
        tv1.setText(item.name)
        val episode = item.episode
        val airdate = item.airDate
        tv2.setText("$episode - $airdate")
        val created = item.created
        tv3.setText("Created in : $created")
    }

    override fun bind(item: Locations) {
        tv1.setText(item.name)
        val type = item.type
        val dimension = item.dimension
        tv2.setText("$type - $dimension")
        val created = item.created
        tv3.setText("Created in : $created")
    }

    override fun bind(item: Favory) {}
}