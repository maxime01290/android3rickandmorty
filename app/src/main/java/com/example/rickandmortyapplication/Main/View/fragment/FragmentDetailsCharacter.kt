package com.example.rickandmortyapplication.Main.View.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.fragment_details_characters.*

class FragmentDetailsCharacter : Fragment() {
    private lateinit var item: Character

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        recuperationIntent()
        attributionValue()
    }

    private fun initToolbar() {
        toolbar_details_characters.title = ""
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_details_characters)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textView_toolbar_details_characters.setText("Détails du personnage")
    }

    private fun recuperationIntent() {
        item = this.arguments?.getSerializable(CharactersFragment.CHARACTER) as Character
    }

    private fun attributionValue() {
        textView_detail_name_character.setText(item.nameCharacter)
        val species = item.species
        val status = item.status
        textView_details_Spacies_and_Status.setText("$species - $status")
        textView_details_type_and_gender.setText(item.gender)
        textView_details_origin.setText("Origin : " + item.origin?.nameOrigin)
        textView_details_location.setText("Last position : " + item.location?.nameLocation)
        imageView_details_character?.load(item.image)
    }

}