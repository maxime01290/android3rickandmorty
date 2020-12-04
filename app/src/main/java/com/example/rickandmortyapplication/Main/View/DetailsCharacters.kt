package com.example.rickandmortyapplication.Main.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapplication.Main.Fragment.FragmentDetailsCharacter
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.activity_details_character.*

class DetailsCharacters : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)

        initToolbar()
        loadFragment()
    }

    private fun initToolbar(){
        toolbar_details_characters.title = ""
        setSupportActionBar(toolbar_details_characters)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textView_toolbar_details_characters.setText("Détails du personnage")
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