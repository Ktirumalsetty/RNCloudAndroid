package com.rncloud.android.data.model

data class LoginDataModel(
    val EmailId:String,
    val Password:String,
    val Platform:String="a"

)

data class User(
    val name: String,
    val profileUrl: String

)

data class LoginResponse(
    val status: Int,
    val message: String?,
    val users: User?,
    val throwable: Throwable?
)