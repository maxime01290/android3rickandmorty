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

class ActivityCharacters : AppCompatActivity(), AdapterCharacters.onItemClickListener,AdapterCharacters.onItemLongClickListener{

    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterList : ArrayList<Character>
    private lateinit var favorisList: ArrayList<Character>
    private lateinit var adapterCharacters : AdapterCharacters
    private lateinit var buttonPreviousPage : Button
    private lateinit var buttonNextPage : Button
    private val viewModel: CharacterViewModel by viewModels()

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
        buttonPreviousPage = findViewById(R.id.button_previous_page_characters)
        buttonNextPage = findViewById(R.id.button_next_page_characters)
        characterList = ArrayList()
        favorisList = ArrayList()
        recyclerView = findViewById(R.id.recyclerView_characters)
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
        if (numPage == 1) {
            adapterCharacters = AdapterCharacters(this@ActivityCharacters, characterList, this,this)
            recyclerView.setAdapter(adapterCharacters)
        } else {
            adapterCharacters.submit(characterList)
        }
    }

    private fun manageClick(){
        buttonPreviousPage.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContenuBouton()
                viewModel.getUpdateCharactersList(numPage)
                    ?.observe(this, changeObserver)
            }
        }

        buttonNextPage.setOnClickListener {
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
            buttonPreviousPage.text = "Page $numPreviousPage"
        }
        else{
            buttonPreviousPage.text = " "
        }

        if(numNextPage<35){
            buttonNextPage.text = "Page $numNextPage"
        }
        else{
            buttonNextPage.text = " "
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
        Log.d("test", favorisList.size.toString())
        Toast.makeText(this,"Ajout à la liste des favoris",Toast.LENGTH_SHORT)
    }

}