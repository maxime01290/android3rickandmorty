package com.example.rickandmortyapplication.Main.View.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.rickandmortyapplication.Main.Adapter.AdapterItem
import com.example.rickandmortyapplication.Main.Database.GlobalApplication
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.ViewHolder.ViewHolderManager
import com.example.rickandmortyapplication.Main.ViewModel.EpisodeViewModel
import com.example.rickandmortyapplication.Main.ViewModel.LocationViewModel
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryEpisodes
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryLocations
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.fragment_locations.*

class LocationsFragment : Fragment(), ViewHolderManager.BaseInterfaceOnItemClickListener{
    private var numPreviousPage:Int = 0
    private var numPage:Int = 1
    private var numNextPage:Int = 2
    private lateinit var listLocations : ArrayList<Locations>
    private lateinit var adapterLocations : AdapterItem
    val databaseViewModel : LocationViewModel by viewModels {
        RoomViewModelFactoryLocations(( requireActivity().application as GlobalApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        MAJContentButton()
        manageClick()
    }

    fun initComponent(){
        toolbar_locations.title = ""
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_locations)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        listLocations = ArrayList()
        initAPI()
    }

    fun initAPI(){
        databaseViewModel.getUpdateLocationsList(numPage).observe(viewLifecycleOwner,changeObserver)
    }

    private val changeObserver = Observer <ArrayList<Locations>?> { value ->
        value ?.let {
            listLocations = it
            createList()
        }
    }
    fun createList(){
        if (numPage == 1) {
            adapterLocations = AdapterItem(requireContext(), listLocations as ArrayList<BaseClass>,this)
            recyclerView_locations.setAdapter(adapterLocations)
        } else {
            adapterLocations.submit(listLocations as ArrayList<BaseClass>)
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
                databaseViewModel.getUpdateLocationsList(numPage).observe(viewLifecycleOwner,changeObserver)
            }
        }

        button_next_page_locations.setOnClickListener {
            if(numNextPage<7){
                updateNumPageSuivante()
                MAJContentButton()
                databaseViewModel.getUpdateLocationsList(numPage).observe(viewLifecycleOwner,changeObserver)
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

    override fun onItemClick(position: Int) {}

    override fun onItemLongClick(position: Int) {}

    override fun onClick(v: View?) {}
}