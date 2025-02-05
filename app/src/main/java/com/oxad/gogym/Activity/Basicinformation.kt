package com.oxad.gogym.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.oxad.gogym.MainActivity
import com.oxad.gogym.R
import com.oxad.gogym.SessionManager
import com.oxad.gogym.databinding.ActivityBasicinformationBinding

class Basicinformation : AppCompatActivity() {
    private lateinit var binding: ActivityBasicinformationBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basicinformation)
        binding = ActivityBasicinformationBinding.inflate(layoutInflater)
        sessionManager = SessionManager(this)

        setContentView(binding.root)



        initUI()


    }
   private fun initUI() {
        binding.etName.text = sessionManager.getUserName().toString().toEditable()
        binding.etEmail.text = sessionManager.getUserEmail().toString().toEditable()
       Log.e("CheckClick","yes pic fetch ${sessionManager.getUserPic()}")

       Glide.with(this)
            .load(sessionManager.getUserPic())
            .placeholder(R.drawable.ic_launcher_foreground) // Default image
            .circleCrop() // Make image round
            .into(binding.imgProfile)


       val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
           .requestEmail()  // Request email if needed
           .build()
       googleSignInClient = GoogleSignIn.getClient(this, gso)
       binding.logoutTv.setOnClickListener {
           Log.e("CheckClick","yes logout clicked")
           sessionManager.logout()
           googleSignInClient.signOut()
           val intent = Intent(this@Basicinformation,MainActivity::class.java)
           startActivity(intent)
           finish()
       }
    }
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}