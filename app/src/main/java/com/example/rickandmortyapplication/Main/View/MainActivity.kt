package com.example.rickandmortyapplication.Main.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapplication.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient:GoogleSignInClient
    private var isLoggedInGoogle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComposant()
        connexionGoogle()
        gestionClick()
    }

    private fun initComposant(){
        button_google_connexion.setSize(SignInButton.SIZE_STANDARD)
        textView_title_connexion_page.setText("Bienvenue sur l'application Rick et Morty!")
        textView_connexion.setText("Connexion")
    }

    private fun connexionGoogle(){
        val gso:GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    private fun signIn(){
        val intent:Intent = mGoogleSignInClient.signInIntent
        startActivity(intent)
    }

    private fun signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this, OnCompleteListener {  })
    }

    private fun gestionClick(){
        button_next_page_home.setOnClickListener {
            if(isLoggedInGoogle){
                val intent = Intent(MainActivity@this, ActivityCharacters::class.java)
                startActivity(intent)
            }
            else{ Toast.makeText(MainActivity@this,"Connectez vous pour pouvoir continuer",Toast.LENGTH_SHORT).show() }
        }

        button_google_connexion.setOnClickListener {
            signIn()
            isLoggedInGoogle=true
        }

    }

    override fun onStop() {
        super.onStop()
        if (isLoggedInGoogle) {
            signOut()
        }
    }
}