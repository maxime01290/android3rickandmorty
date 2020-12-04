package com.example.rickandmortyapplication.Main.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.rickandmortyapplication.Main.Adapter.AdapterFavorisCharacter
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_favoris.*

class Favoris : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textViewToolbar: TextView
    private lateinit var favorisList: ArrayList<Character>
    private lateinit var adapterFavoris:AdapterFavorisCharacter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoris)

        initToolbar()
        favorisList = intent.getSerializableExtra(ActivityCharacters.LISTFAVORIS) as ArrayList<Character>
    }

    private fun initToolbar(){
        toolbar = findViewById<Toolbar>(R.id.toolbar_favoris)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textViewToolbar = findViewById(R.id.textView_toolbar_favoris)
        textViewToolbar.setText("Liste des favoris")


    }

    private fun createList(){
        adapterFavoris = AdapterFavorisCharacter(this@Favoris,favorisList)
        recyclerView_favoris.setAdapter(adapterFavoris)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}