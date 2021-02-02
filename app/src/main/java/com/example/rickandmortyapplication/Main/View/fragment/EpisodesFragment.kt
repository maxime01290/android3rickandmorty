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
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.ViewHolder.ViewHolderManager
import com.example.rickandmortyapplication.Main.ViewModel.CharacterViewModel
import com.example.rickandmortyapplication.Main.ViewModel.EpisodeViewModel
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryCharacters
import com.example.rickandmortyapplication.Main.ViewModel.RoomViewModelFactoryEpisodes
import com.example.rickandmortyapplication.R
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : Fragment(), ViewHolderManager.BaseInterfaceOnItemClickListener {
    private var numPreviousPage = 0
    private var numPage = 1
    private var numNextPage = 2
    private lateinit var EpisodesList : ArrayList<Episodes>
    private lateinit var adapterEpisodes : AdapterItem
    private var recyclerViewIsCreate = false
    val databaseViewModel : EpisodeViewModel by viewModels {
        RoomViewModelFactoryEpisodes(( requireActivity().application as GlobalApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
        initAPI()
        MAJContentButton()
        manageClick()
    }

    private fun initComponent(){
        toolbar_list_episodes.title = ""
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_list_episodes)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        EpisodesList = ArrayList()
    }

    private fun initAPI(){
        databaseViewModel.getUpdateEpisodesList(numPage).observe(viewLifecycleOwner,changeObserver)
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
            adapterEpisodes = AdapterItem(requireContext(), EpisodesList as ArrayList<BaseClass>,this)
            recyclerView_episodes.setAdapter(adapterEpisodes)
        } else {
            adapterEpisodes.submit(EpisodesList as ArrayList<BaseClass>)
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
                databaseViewModel.getUpdateEpisodesList(numPage).observe(viewLifecycleOwner,changeObserver)
            }
        }

        button_next_page_episodes.setOnClickListener {
            if(numNextPage<4){
                updateNumPageSuivante()
                MAJContentButton()
                databaseViewModel.getUpdateEpisodesList(numPage).observe(viewLifecycleOwner,changeObserver)
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

    override fun onItemClick(position: Int) {}

    override fun onItemLongClick(position: Int) {}

    override fun onClick(v: View?) {}

}