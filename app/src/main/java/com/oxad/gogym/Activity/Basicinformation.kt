package com.oxad.gogym.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.oxad.gogym.RetrofitApi.ApiInterface
import com.oxad.gogym.RetrofitApi.ApiManager
import com.oxad.gogym.R
import com.oxad.gogym.Utils.Constants
import com.oxad.gogym.Utils.SessionManager
import com.oxad.gogym.databinding.ActivityBasicinformationBinding
import com.oxad.gogym.model.UserRegisterModel

class Basicinformation : AppCompatActivity(), View.OnClickListener, ApiInterface {
    private lateinit var binding: ActivityBasicinformationBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sessionManager: SessionManager
    private lateinit var apiManager: ApiManager
    private var name: String = ""
    private var email: String = ""
    private var age: Int = 0
    private var gender: String = "male"
    private var height: Int = 0
    private var weight: Int = 0
    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basicinformation)
        binding = ActivityBasicinformationBinding.inflate(layoutInflater)
        sessionManager = SessionManager(this)
        apiManager = ApiManager(this@Basicinformation,this)
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


       setclicklistener()

    }
    private fun setclicklistener(){
        binding.logoutTv.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


    override fun onClick(p0: View?) {
        when(p0){
            binding.logoutTv ->  logout()
            binding.btnSubmit ->{
                name =  binding.etName.text.toString()
                email = sessionManager.getUserEmail().toString()
                age =if(binding.etAge.text.toString()!=""){
                    binding.etAge.text.toString().toInt()
                }else{
                    0
                }
                height = if(binding.etHeight.text.toString()!=""){
                    binding.etHeight.text.toString().toInt()
                }else{
                    0
                }
                weight =if(binding.etWeight.text.toString()!=""){
                    binding.etWeight.text.toString().toInt()
                }else{
                    0
                }
                type = binding.spinnerType.selectedItemPosition  + 1

                val radioButton = findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId)
                gender = radioButton.text.toString()

                apiManager.saveUserData(name,email, age, gender, weight, height, type)

            }

        }
    }
    private fun logout(){
        Log.e("CheckClick","yes logout clicked")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()  // Request email if needed
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        sessionManager.logout()
        googleSignInClient.signOut()
        val intent = Intent(this@Basicinformation, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun registerSuccess(response: Any){
        val userRegisterModel =  response as UserRegisterModel


            if(userRegisterModel.success) {
                Toast.makeText(this, userRegisterModel.message, Toast.LENGTH_SHORT).show()

            }


    }
    override fun onSuccess(response: Any, serviceCode: Int) {
        when(serviceCode){
            Constants.SAVE_USER -> registerSuccess(response)
        }
    }

    override fun onError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

}