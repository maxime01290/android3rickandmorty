package com.example.rickandmortyapplication.Main.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.View.ActivityCharacters
import com.example.rickandmortyapplication.R

class FragmentDetailsCharacter : Fragment() {

    private var position:Int = 0
    private lateinit var list: List<Character>
    private var imageView: ImageView? = null
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recuperationIntent(view)
        attributionValeur(view)
    }

    private fun recuperationIntent(view: View) {
        position = activity?.intent?.getIntExtra(ActivityCharacters.POSITION, 0) ?: 0
        list = activity?.intent?.getSerializableExtra(ActivityCharacters.LIST) as List<Character>
    }

    private fun attributionValeur(view:View) {
        imageView = view.findViewById<ImageView>(R.id.imageView_details_character)
        tv1 = view.findViewById<TextView>(R.id.textView_detail_name_character)
        tv2 = view.findViewById<TextView>(R.id.textView_details_Spacies_and_Status)
        tv3 = view.findViewById<TextView>(R.id.textView_details_type_and_gender)
        tv4 = view.findViewById<TextView>(R.id.textView_details_origin)
        tv5 = view.findViewById<TextView>(R.id.textView_details_location)
        tv1.setText(list[position].name)
        val species = list[position].species
        val status = list[position].status
        tv2.setText("$species - $status")
        tv3.setText(list[position].gender)
        tv4.setText("Origin : " + list[position].origin?.name)
        tv5.setText("Last position : " + list[position].location?.name)
        chargementImage()
    }

    private fun chargementImage() {
        imageView?.load(list[position].image)
    }

    companion object {
        fun newInstance() = FragmentDetailsCharacter()
    }
}