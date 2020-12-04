package com.example.rickandmortyapplication.Main.View

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rickandmortyapplication.Main.Fragment.FragmentDetailsCharacter
import com.example.rickandmortyapplication.R

class DetailsCharacters : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textViewToolbar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)

        initToolbar()
        loadFragment()
    }

    private fun initToolbar(){
        toolbar = findViewById<Toolbar>(R.id.toolbar_details_characters)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textViewToolbar = findViewById(R.id.textView_toolbar_details_characters)
        textViewToolbar.setText("Détails du personnage")
    }

    private fun loadFragment(){
        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.container_details_characters, FragmentDetailsCharacter.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}