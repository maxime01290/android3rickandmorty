package com.example.rickandmortyapplication.Main.View.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.rickandmortyapplication.Main.Adapter.AdapterItem
import com.example.rickandmortyapplication.Main.Database.GlobalApplication
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.ViewHolder.ViewHolderManager
import com.example.rickandmortyapplication.Main.ViewModel.CharacterViewModel
import com.example.rickandmortyapplication.Main.ViewModel.FavoryViewModel
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryCharacters
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryFavory
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.fragment_favoris.*

class FavorisFragment : Fragment(),ViewHolderManager.BaseInterfaceOnItemClickListener {

    private var favorisList: ArrayList<Favory>?=null
    private lateinit var adapterFavoris: AdapterItem
    val databaseViewModel : FavoryViewModel by viewModels {
        RoomViewModelFactoryFavory(( requireActivity().application as GlobalApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoris, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        databaseViewModel.getFavory()?.observe(viewLifecycleOwner,changeObserver)
    }

    private val changeObserver = Observer<ArrayList<Favory>?> { value ->
        value ?.let {
            favorisList = it
            createList()
        }
    }

    private fun initToolbar(){
        toolbar_favoris.title = ""
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_favoris)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textView_toolbar_favoris.setText("Liste des favoris")
    }

    private fun createList(){
        if(favorisList!=null){
            adapterFavoris = AdapterItem(requireContext(), favorisList!! as ArrayList<BaseClass>,this)
            recyclerView_favoris.setAdapter(adapterFavoris)
        }
    }

    private fun convertFavoryToCharacter(favory: Favory):Character{
        val character = Character(favory.id, favory.nameCharacter, favory.status, favory.species, favory.type, favory.gender, favory.origin, favory.location, favory.image,favory.episode, favory.url, favory.created)
        return character
    }

    override fun onItemClick(position: Int) {
        val fragment = FragmentDetailsCharacter()
        val bundle = Bundle()
        bundle.putSerializable(CharactersFragment.CHARACTER,convertFavoryToCharacter(favorisList?.get(position)!!))
        fragment.setArguments(bundle)
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity, fragment).addToBackStack(this.tag).commit()
    }

    override fun onItemLongClick(position: Int) {}

    override fun onClick(v: View?) {}
}