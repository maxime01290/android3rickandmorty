package com.example.rickandmortyapplication.Main.View


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapplication.Main.Adapter.AdapterFavorisCharacter
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_favoris.*

class Favoris : AppCompatActivity() {

    private lateinit var favorisList: ArrayList<Character>
    private lateinit var adapterFavoris:AdapterFavorisCharacter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoris)

        initToolbar()
        favorisList = intent.getSerializableExtra(ActivityCharacters.LISTFAVORIS) as ArrayList<Character>
        Log.d("test","favoris list = ${favorisList.size}")
        createList()
    }

    private fun initToolbar(){
        toolbar_favoris.title = ""
        setSupportActionBar(toolbar_favoris)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textView_toolbar_favoris.setText("Liste des favoris")
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