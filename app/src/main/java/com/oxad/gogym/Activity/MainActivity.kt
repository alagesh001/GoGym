package com.oxad.gogym.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.oxad.gogym.Utils.Constants.Companion.REGISTER_USER
import com.oxad.gogym.R
import com.oxad.gogym.RetrofitApi.ApiInterface
import com.oxad.gogym.RetrofitApi.ApiManager
import com.oxad.gogym.Utils.SessionManager
import com.oxad.gogym.model.UserRegisterModel

class MainActivity : AppCompatActivity(), ApiInterface {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sessionManager: SessionManager
    private lateinit var apiManager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sessionManager = SessionManager(this)
        apiManager = ApiManager(this@MainActivity,this)

        // Initialize Firebase Auth
        if (sessionManager.isLoggedIn()) {
            val intent = Intent(this@MainActivity,Basicinformation::class.java)
            startActivity(intent)
            finish()
        }
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()  // Request email if needed
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set up Sign-In button
        findViewById<Button>(R.id.signInButton).setOnClickListener {
            signIn()
            //registerUser("Admin", "admin@gmail.com")

        }

    }

    val RC_SIGN_IN  = 12
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            // Signed in successfully, update UI with the signed-in user's information
            val email:String = account?.email.toString()
            val displayName:String = account?.displayName.toString()
            val profilepic:String = account?.photoUrl.toString()

            Log.w("checkresponss","Yes ${account.idToken} ${account.id} ${ account.photoUrl}}")
            apiManager.registerUser(profilepic,displayName, email)

        } catch (e: ApiException) {

        }
    }

    private fun registerSuccess(response: Any){
        val userRegisterModel =  response as UserRegisterModel

        try{
            if(userRegisterModel.isAdmin==1){
                Toast.makeText(this@MainActivity, "Admin Account", Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this, "User Account", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Basicinformation::class.java)
                startActivity(intent)
                finish()
            }

        }catch (e:Exception){
            Log.w("checkresponss","Exception ${e.message}}")

        }
    }
    override fun onSuccess(response: Any, serviceCode: Int) {
        when(serviceCode){
            REGISTER_USER -> registerSuccess(response)
        }
    }

    override fun onError(error: String) {
    }


}
