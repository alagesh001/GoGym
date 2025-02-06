package com.oxad.gogym.model

data class ApiResponse(val s:String)
data class UserRegisterModel(
    val success: Boolean,
    val message: String,
    val isAdmin: Int

)