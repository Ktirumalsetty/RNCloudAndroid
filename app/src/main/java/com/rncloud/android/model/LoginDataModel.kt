package com.rncloud.android.model

data class LoginDataModel(
    val UserName:String,
    val Password:String = "gdyb21LQTcIANtvYMT7QVQ==",
    val RequestMode:String ="IOS",
    val RequestType:Int = 1,
    val IsEncriptFromClient:Boolean = true

)

data class User(
    val name: String,
    val profileUrl: String

)
//
//data class LoginResponse(
//    val status: LoginResponse?,
//    val message: String?,
//    val users: User?,
//    val throwable: Throwable?
//)