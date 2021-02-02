package com.example.rickandmortyapplication.Main.Database

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalRoomDatabase.getDatabase(this, applicationScope)
    }

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { GlobalRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { GlobalRepository(database.globalDao(),this,NetworkManager) }

    fun isConnected():Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}