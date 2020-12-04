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
import com.example.rickandmortyapplication.Main.Adapter.AdapterLocations
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import com.example.rickandmortyapplication.Main.ViewModel.EpisodeViewModel
import com.example.rickandmortyapplication.Main.ViewModel.LocationViewModel
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_list_locations.*

class ActivityLocations : AppCompatActivity() {
    private var numPreviousPage:Int = 0
    private var numPage:Int = 1
    private var numNextPage:Int = 2
    private lateinit var listLocations : ArrayList<Locations>
    private lateinit var adapterPersonnage : AdapterLocations
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_locations)

        initComponent()
        initAPI()
        MAJContentButton()
        manageClick()
    }

    fun initComponent(){
        toolbar_locations.title = ""
        setSupportActionBar(toolbar_locations)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        listLocations = ArrayList()
    }

    fun initAPI(){
        viewModel.getLocationsList().observe(this,changeObserver)
    }

    private val changeObserver = Observer <ArrayList<Locations>?> { value ->
        value ?.let {
            listLocations = it
            createList()
        }
    }
    fun createList(){
        if (numPage == 1) {
            adapterPersonnage = AdapterLocations(this@ActivityLocations, listLocations)
            recyclerView_locations.setAdapter(adapterPersonnage)
        } else {
            adapterPersonnage.submit(listLocations)
        }
    }

    private fun MAJContentButton(){
        if(numPage>1){
            button_previous_page_locations.text = "Page $numPreviousPage"
        }
        else{
            button_previous_page_locations.text = " "
        }

        if(numNextPage<7){
            button_next_page_locations.text = "Page $numNextPage"
        }
        else{
            button_next_page_locations.text = " "
        }
    }

    private fun manageClick(){
        button_previous_page_locations.setOnClickListener {
            if(numPreviousPage>0){
                updateNumPagePrecedente()
                MAJContentButton()
                viewModel.getUpdateLocationsList(numPage).observe(this,changeObserver)
            }
        }

        button_next_page_locations.setOnClickListener {
            if(numNextPage<7){
                updateNumPageSuivante()
                MAJContentButton()
                viewModel.getUpdateLocationsList(numPage).observe(this,changeObserver)
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
        if (numNextPage < 7) {
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
        inflater.inflate(R.menu.option_locations, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        when (item.itemId) {
            R.id.menu_list_characters -> {
                intent = Intent(this@ActivityLocations,ActivityCharacters::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_list_episodes -> {
                intent = Intent(this@ActivityLocations,ActivityEpisodes::class.java)
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