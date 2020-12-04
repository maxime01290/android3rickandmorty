package com.example.rickandmortyapplication.Main.View

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication.Main.Adapter.AdapterEpisodes
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.ViewModel.EpisodeViewModel
import com.example.rickandmortyapplication.R

class ActivityEpisodes : AppCompatActivity() {
    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var recyclerView: RecyclerView
    private lateinit var listEpisodes : ArrayList<Episodes>
    private lateinit var adapterEpisodes : AdapterEpisodes
    private lateinit var buttonPreviousPage : Button
    private lateinit var buttonNextPage : Button
    private val viewModel: EpisodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_episodes)

        initComponent()
        initAPI()
        MAJContentButton()
        manageClick()
    }

    private fun initComponent(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar_list_episodes)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recyclerView_episodes)
        buttonPreviousPage=findViewById(R.id.button_previous_page_episodes)
        buttonNextPage=findViewById(R.id.button_next_page_episodes)
        listEpisodes = ArrayList()
    }

    private fun initAPI(){
        viewModel.getEpisodesList().observe(this,changeObserver)
    }

    private val changeObserver = Observer <ArrayList<Episodes>?> { value ->
        value ?.let {
            listEpisodes = it
            createList()
        }
    }

    private fun createList(){
        if (numPage == 1) {
            adapterEpisodes = AdapterEpisodes(this@ActivityEpisodes, listEpisodes)
            recyclerView.setAdapter(adapterEpisodes)
        } else {
            adapterEpisodes.submit(listEpisodes)
        }
    }

    private fun MAJContentButton(){
        if(numPage>1){
            buttonPreviousPage.text = "Page $numPreviousPage"
        }
        else{
            buttonPreviousPage.text = " "
        }

        if(numNextPage<4){
            buttonNextPage.text = "Page $numNextPage"
        }
        else{
            buttonNextPage.text = " "
        }
    }

    private fun manageClick(){
        buttonPreviousPage.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContentButton()
                viewModel.getUpdateEpisodesList(numPage).observe(this,changeObserver)
            }
        }

        buttonNextPage.setOnClickListener {
            if(numNextPage<4){
                updateNumPageSuivante()
                MAJContentButton()
                viewModel.getUpdateEpisodesList(numPage).observe(this,changeObserver)
            }
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
        if (numNextPage < 4) {
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
        inflater.inflate(R.menu.option_episodes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        when (item.itemId) {
            R.id.menu_list_characters -> {
                intent = Intent(this@ActivityEpisodes,ActivityCharacters::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_list_locations -> {
                intent = Intent(this@ActivityEpisodes,ActivityLocations::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_favoris -> {
//                intent = Intent(this@ActivityCharacters,ActivityEpisodes::class.java)
//                startActivity(intent)
                return true
            }
        }
        return false
    }
}