package com.oxad.gogym.retrofit

import com.oxad.gogym.model.UserRegisterModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// User data model


// API service interface
interface ApiService {
    @FormUrlEncoded
    @POST("registerusers.php") // Adjust this to match your backend endpoint
    fun registerUser(@Field("name") name: String,
                     @Field("email") email: String): Call<UserRegisterModel>
    @FormUrlEncoded
    @POST("updatetable.php") // Adjust this to match your backend endpoint
    fun saveprofiledata(@Field("name") name: String,
                     @Field("email") email: String,
                        @Field("age") age: Int,
                        @Field("gender") gender: String,
                        @Field("type") type: Int,
                        @Field("height") height: Int,
                        @Field("weight") weight: Int,): Call<UserRegisterModel>
}

// Response model
