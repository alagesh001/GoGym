package com.oxad.gogym.RetrofitApi

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.oxad.gogym.Utils.Constants.Companion.REGISTER_USER
import com.oxad.gogym.Utils.Constants.Companion.SAVE_USER
import com.oxad.gogym.Utils.SessionManager
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
                            if(response.body()!!.isAdmin == 0) {
                                sessionManager.saveLogin(profileUrl, name, email)
                            }
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
    fun saveUserData(name: String, email: String,age: Int, gender: String,weight: Int, height: Int,type: Int) {
        Log.e("checkresponss", "saveUserData: $age $gender $weight $height $type $name $email")

        RetrofitClient.instance.saveprofiledata(name,email,age,gender,type,height, weight)
            .enqueue(object : Callback<UserRegisterModel> {
                override fun onResponse(call: Call<UserRegisterModel>, response: Response<UserRegisterModel>) {
                    Log.e("checkresponss", "saveUserData: ${response.body()}")

                    if(response.body()!=null) {
                        if (response.body()!!.success) {
                            apiInterfaceIs.onSuccess(response.body()!!, SAVE_USER)
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