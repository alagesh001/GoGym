package com.oxad.gogym

import com.google.gson.JsonElement
import com.oxad.gogym.model.UserRegisterModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

// User data model
data class User(
    val name: String,
    val email: String
)

// API service interface
interface ApiService {
    @FormUrlEncoded
    @POST("registerusers.php") // Adjust this to match your backend endpoint
    fun registerUser(@Field("name") name: String,
                     @Field("email") email: String): Call<UserRegisterModel>
}

// Response model
