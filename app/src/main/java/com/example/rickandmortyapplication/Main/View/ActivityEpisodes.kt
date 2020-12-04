package com.example.rickandmortyapplication.Main.View

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rickandmortyapplication.Main.Adapter.AdapterEpisodes
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.ViewModel.EpisodeViewModel
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_list_episodes.*

class ActivityEpisodes : AppCompatActivity() {
    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var EpisodesList : ArrayList<Episodes>
    private lateinit var adapterEpisodes : AdapterEpisodes
    private val viewModel: EpisodeViewModel by viewModels()
    private var recyclerViewIsCreate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_episodes)

        initComponent()
        initAPI()
        MAJContentButton()
        manageClick()
    }

    private fun initComponent(){
        toolbar_list_episodes.title = ""
        setSupportActionBar(toolbar_list_episodes)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        EpisodesList = ArrayList()
    }

    private fun initAPI(){
        viewModel.getEpisodesList().observe(this,changeObserver)
    }

    private val changeObserver = Observer <ArrayList<Episodes>?> { value ->
        value ?.let {
            EpisodesList = it
            createList()
        }
    }

    private fun createList(){
        if (!recyclerViewIsCreate) {
            recyclerViewIsCreate=true
            adapterEpisodes = AdapterEpisodes(this@ActivityEpisodes, EpisodesList)
            recyclerView_episodes.setAdapter(adapterEpisodes)
        } else {
            adapterEpisodes.submit(EpisodesList)
        }
    }

    private fun MAJContentButton(){
        if(numPage>1){
            button_previous_page_episodes.text = "Page $numPreviousPage"
        }
        else{
            button_previous_page_episodes.text = " "
        }

        if(numNextPage<4){
            button_next_page_episodes.text = "Page $numNextPage"
        }
        else{
            button_next_page_episodes.text = " "
        }
    }

    private fun manageClick(){
        button_previous_page_episodes.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContentButton()
                viewModel.getUpdateEpisodesList(numPage).observe(this,changeObserver)
            }
        }

        button_next_page_episodes.setOnClickListener {
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