package com.oxad.gogym

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.oxad.gogym.Activity.Basicinformation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sessionManager = SessionManager(this)
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
            registerUser(profilepic,displayName, email)

        } catch (e: ApiException) {

        }
    }

            private fun registerUser( profileUrl:String,name: String, email: String) {
        val user = User(name, email)

        RetrofitClient.instance.registerUser(name,email)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    try{
                        if(response.body()?.isAdmin==1){
                            Toast.makeText(this@MainActivity, "Admin Account", Toast.LENGTH_SHORT).show()

                        }else{
                            Log.e("CheckClick","yes pic fetch $profileUrl")

                            Toast.makeText(this@MainActivity, "User Account", Toast.LENGTH_SHORT).show()
                            sessionManager.saveLogin(profileUrl,name,email)
                            val intent = Intent(this@MainActivity,Basicinformation::class.java)
                            startActivity(intent)
                            finish()
                        }
                        Log.w("checkresponss","Yes ${Gson().toJson(response.body())}}")

                    }catch (e:Exception){
                        Log.w("checkresponss","Exception ${e.message}}")

                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("checkresponss", "Error: ${t.message}")
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }


}
