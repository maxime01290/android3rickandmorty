package com.example.rickandmortyapplication.Main.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.R

class ViewHolderManager {

    companion object{

        val firstItem = 100
        val secondItem = 101

        //gère le type d'affichage
        fun getItemViewTypeVH(comparable:String):Int{
            when(comparable) {
                "character" -> return firstItem
                "other" -> return secondItem
                else -> return secondItem //renvoie une valeur par défaut pour éviter un crash de l'application
            }
        }

        //appelle la fonction de création du viewHolder correspondant à l'itemViewType
        fun createVH(viewType:Int, context: Context, onItemClickListener: BaseInterfaceOnItemClickListener, parent: ViewGroup): BaseViewHolder<BaseClass> {
            when(viewType){
                firstItem -> return getCharacterVH(onItemClickListener,parent)
                secondItem -> return getOtherVH(parent)
                else -> return getOtherVH(parent)
            }
        }

        //créer le viewHolder pour la classe Character et les favoris
        fun getCharacterVH( onItemClickListener: BaseInterfaceOnItemClickListener, parent: ViewGroup):CharacterViewHolder{
            val parentContext = parent.context
            val inflater = LayoutInflater.from(parentContext)
            val layout= inflater.inflate(R.layout.component_list_characters, parent, false)
            return CharacterViewHolder(layout,onItemClickListener)
        }

        //créer le viewHolder pour la classe Episode et la classe Location
        fun getOtherVH(parent: ViewGroup):OtherViewHolder{
            val parentContext = parent.context
            val inflater = LayoutInflater.from(parentContext)
            val layout= inflater.inflate(R.layout.component_list, parent, false)
            return OtherViewHolder(layout)
        }
   }

    //interface permettant de gérer le clic et long clic sur toutes les listes
    interface BaseInterfaceOnItemClickListener: View.OnClickListener{
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    //classe permettant de gérer le bind des éléments de la liste tout en évitant le crash
    abstract class BaseViewHolder<BaseClass>(itemView: View): RecyclerView.ViewHolder(itemView) { //classe permettant de bind les éléments de la liste, peut
        abstract fun bind(item: Character)
        abstract fun bind(item:Episodes)
        abstract fun bind(item:Locations)
        abstract fun bind(item:Favory)
    }

}