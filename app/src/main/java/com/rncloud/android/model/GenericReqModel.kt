package com.rncloud.android.model

import com.rncloud.android.common.AppPreferences

data class GenericReqModel(
    val ActorCode: Int = AppPreferences.actorCode,
    val SendApiRequestModel: SendApiRequestModel = SendApiRequestModel(AppPreferences.authGenKey,1,AppPreferences.userName)


)

data class MasterDataReqModel(
    val CodeType: String = "",
//    val ActorCode: Int = AppPreferences.actorCode,
    val SendApiRequestModel: SendApiRequestModel = SendApiRequestModel(AppPreferences.authGenKey,1,AppPreferences.userName)


)