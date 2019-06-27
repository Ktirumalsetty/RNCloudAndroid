package com.rncloud.android.model

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
    val status: LoginResponse?,
    val message: String?,
    val users: User?,
    val throwable: Throwable?
)