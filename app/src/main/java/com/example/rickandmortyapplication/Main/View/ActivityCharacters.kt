package com.example.rickandmortyapplication.Main.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Adapter.AdapterCharacters
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.ViewModel.CharacterViewModel
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_list_characters.*

class ActivityCharacters : AppCompatActivity(), AdapterCharacters.onItemClickListener{

    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var characterList : ArrayList<Character>
    private lateinit var favorisList: ArrayList<Character>
    private lateinit var adapterCharacters : AdapterCharacters
    private val viewModel: CharacterViewModel by viewModels()
    private var recyclerViewIsCreate = false

    companion object  {
        val LIST = "list"
        val POSITION = "position"
        val LISTFAVORIS = "listFavoris"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_characters)

        initComposant()
        initAPI()
        MAJContenuBouton()
        manageClick()
    }

    private fun initComposant(){
        toolbar_list_characters.title = ""
        setSupportActionBar(toolbar_list_characters)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        characterList = ArrayList()
        favorisList = ArrayList()
    }

    private fun initAPI(){
        viewModel.getCharactersList()?.observe(this, changeObserver)
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
            adapterCharacters = AdapterCharacters(this@ActivityCharacters, characterList, this)
            recyclerView_characters.setAdapter(adapterCharacters)
        } else {
            adapterCharacters.submit(characterList)
        }
    }

    private fun manageClick(){
        button_previous_page_characters.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContenuBouton()
                viewModel.getUpdateCharactersList(numPage)
                    ?.observe(this, changeObserver)
            }
        }

        button_next_page_characters.setOnClickListener {
            if(numNextPage<35){
                updateNumPageSuivante()
                MAJContenuBouton()
                viewModel.getUpdateCharactersList(numPage)
                    ?.observe(this, changeObserver)
            }
        }
    }

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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        val inflater = menuInflater
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.option_characters, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        when (item.itemId) {
            R.id.menu_list_episodes -> {
                intent = Intent(this@ActivityCharacters,ActivityEpisodes::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_list_locations -> {
                intent = Intent(this@ActivityCharacters,ActivityLocations::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_favoris -> {
                intent = Intent(this@ActivityCharacters,Favoris::class.java)
                intent.putExtra(LISTFAVORIS,favorisList)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@ActivityCharacters, DetailsCharacters::class.java)
        intent.putExtra(LIST, characterList)
        intent.putExtra(POSITION, position)
        startActivity(intent)
    }

    override fun onItemLongClick(position: Int) {
        favorisList.add(characterList[position])
        Toast.makeText(this,"Ajout aux favoris",Toast.LENGTH_SHORT).show()
        Log.d("test", "taille de la liste = ${favorisList.size}")
    }

}