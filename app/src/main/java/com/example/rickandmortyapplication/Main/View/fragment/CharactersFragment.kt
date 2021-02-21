package com.example.rickandmortyapplication.Main.View.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.rickandmortyapplication.Main.Adapter.AdapterItem
import com.example.rickandmortyapplication.Main.Database.GlobalApplication
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.ViewHolder.ViewHolderManager
import com.example.rickandmortyapplication.Main.ViewModel.CharacterViewModel
import com.example.rickandmortyapplication.Main.ViewModel.FavoryViewModel
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryCharacters
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryFavory
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment(),ViewHolderManager.BaseInterfaceOnItemClickListener {

    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var characterList : ArrayList<Character>
    private lateinit var adapterCharacters : AdapterItem
    private var recyclerViewIsCreate = false
    val databaseViewModel : CharacterViewModel by viewModels {
        RoomViewModelFactoryCharacters(( requireActivity().application as GlobalApplication).repository)
    }
    val databaseViewModelFavory : FavoryViewModel by viewModels {
        RoomViewModelFactoryFavory(( requireActivity().application as GlobalApplication).repository)
    }

    companion object  {
        val CHARACTER = "character"
        val LISTFAVORIS = "listFavoris"
        lateinit var favorisList: ArrayList<Character>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComposant()
        MAJContenuBouton()
        manageClick()
    }

    private fun initComposant(){
        initToolbar()
        characterList = ArrayList()
        favorisList = ArrayList()
        initAPI()
    }

    private fun initToolbar(){
        toolbar_list_characters.title = ""
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_list_characters)
        (activity as AppCompatActivity?)!!.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
    private fun initAPI(){
        databaseViewModel.getUpdateCharactersList(numPage)?.observe(viewLifecycleOwner, changeObserver)
    }

    private val changeObserver = Observer<ArrayList<Character>?> { value ->
        value ?.let {
            characterList = it
            createList()
        }
    }

    private fun createList(){
        if (!recyclerViewIsCreate) {
            recyclerViewIsCreate=true
            adapterCharacters = AdapterItem(requireContext(), characterList as ArrayList<BaseClass>, this)
            recyclerView_characters.setAdapter(adapterCharacters)
        } else {
            adapterCharacters.submit(characterList as ArrayList<BaseClass>) // met à jour la recyclerview
        }
    }

    //gère les clics pour changer de pages
    private fun manageClick(){
        button_previous_page_characters.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContenuBouton()
                databaseViewModel.getUpdateCharactersList(numPage)
                        ?.observe(viewLifecycleOwner, changeObserver)
            }
        }

        button_next_page_characters.setOnClickListener {
            if(numNextPage<35){
                updateNumPageSuivante()
                MAJContenuBouton()
                databaseViewModel.getUpdateCharactersList(numPage)
                        ?.observe(viewLifecycleOwner, changeObserver)
            }
        }
    }

    //met à jour l'UI du bouton
    private fun MAJContenuBouton(){
        if(numPage>1){
            button_previous_page_characters.text = "Page $numPreviousPage"
        }
        else{
            button_previous_page_characters.text = " "
        }

        if(numNextPage<35){
            button_next_page_characters.text = "Page $numNextPage"
        }
        else{
            button_next_page_characters.text = " "
        }
    }

    private fun updateNumPagePrecedente() {
        if (numPage > 0) {
            numPage--
            numNextPage--
        }
        if (numPreviousPage > 0) {
            numPreviousPage--
        }
    }

    private fun updateNumPageSuivante() {
        if (numPage > 0) {
            numPreviousPage++
        }
        if (numNextPage < 35) {
            numPage++
            numNextPage++
        }
    }

    //envoi sur la page de détails des personnages
    override fun onItemClick(position: Int) {
        val fragment = FragmentDetailsCharacter()
        val bundle = Bundle()
        bundle.putSerializable(CHARACTER,characterList[position])
        fragment.setArguments(bundle)
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity, fragment).addToBackStack(this.tag).commit()
    }

    //ajoute un élément à la liste des favoris
    override fun onItemLongClick(position: Int) {
        databaseViewModelFavory.insert(characterList[position])
        //favorisList.add(characterList[position])
        Toast.makeText(requireContext(),"Ajout aux favoris", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {}
}