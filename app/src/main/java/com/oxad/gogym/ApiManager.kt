package com.oxad.gogym

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.oxad.gogym.Activity.Basicinformation
import com.oxad.gogym.Constants.Companion.REGISTER_USER
import com.oxad.gogym.model.UserRegisterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(context: Context,apiInterface: ApiInterface) {
private val contextA = context
    private val apiInterfaceIs = apiInterface
    private val sessionManager = SessionManager(contextA)

     fun registerUser( profileUrl:String,name: String, email: String) {
         Log.w("checkresponss","call api registeruser }")

        RetrofitClient.instance.registerUser(name,email)
            .enqueue(object : Callback<UserRegisterModel> {

                override fun onResponse(call: Call<UserRegisterModel>, response: Response<UserRegisterModel>) {
                    Log.w("checkresponss","call api response ${Gson().toJson(response.body())}")

                    if(response.body()!=null) {
                        if (response.body()!!.success) {
                            sessionManager.saveLogin(profileUrl,name,email)
                            apiInterfaceIs.onSuccess(response.body()!!, REGISTER_USER)
                        } else {
                            apiInterfaceIs.onError(response.body()!!.message)
                        }
                    }

                }

                override fun onFailure(call: Call<UserRegisterModel>, t: Throwable) {
                    Log.e("checkresponss", "Error: ${t.message}")
                    Toast.makeText(contextA, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
    fun saveUserData( profileUrl:String,name: String, email: String) {

        RetrofitClient.instance.registerUser(name,email)
            .enqueue(object : Callback<UserRegisterModel> {
                override fun onResponse(call: Call<UserRegisterModel>, response: Response<UserRegisterModel>) {
                    if(response.body()!=null) {
                        if (response.body()!!.success) {
                            sessionManager.saveLogin(profileUrl,name,email)
                            apiInterfaceIs.onSuccess(response.body()!!, REGISTER_USER)
                        } else {
                            apiInterfaceIs.onError(response.body()!!.message)
                        }
                    }

                }

                override fun onFailure(call: Call<UserRegisterModel>, t: Throwable) {
                    Log.e("checkresponss", "Error: ${t.message}")
                    Toast.makeText(contextA, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
interface ApiInterface{
    fun onSuccess(response:Any,serviceCode:Int)
    fun onError(error:String)
}