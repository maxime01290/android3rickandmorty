package com.example.rickandmortyapplication.Main.View

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapplication.Main.View.fragment.*
import com.example.rickandmortyapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = CharactersFragment()
        supportFragmentManager.beginTransaction().add(R.id.constraintLayout_main_activity, fragment).addToBackStack(null).commit()
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
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action pour changer de page
        when (item.itemId) {
            R.id.menu_list_characters -> {
                val fragment = CharactersFragment()
                supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity,fragment).addToBackStack(null).commit()
                return true
            }
            R.id.menu_list_episodes -> {
                val fragment = EpisodesFragment()
                supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity,fragment).addToBackStack(null).commit()
                return true
            }
            R.id.menu_list_locations -> {
                val fragment = LocationsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity,fragment).addToBackStack(null).commit()
                return true
            }
            R.id.menu_favoris -> {
                val fragment = FavorisFragment()
                val bundle = Bundle()
                bundle.putSerializable(CharactersFragment.LISTFAVORIS, CharactersFragment.favorisList)
                fragment.setArguments(bundle)
                //ajouter bundle pour la liste de favoris
                supportFragmentManager.beginTransaction().replace(R.id.constraintLayout_main_activity,fragment).addToBackStack(null).commit()
                return true
            }
            else ->{
                supportFragmentManager.popBackStack()
            }
        }
        return false
    }
}