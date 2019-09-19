package com.rncloud.android.model

import com.rncloud.android.common.AppPreferences

data class JobsReqModel(
    val EndIndex: Int = 10,
    val NurseActorCode: Int = AppPreferences.actorCode,
    val SendApiRequestModel: SendApiRequestModel=SendApiRequestModel(
        AppPreferences.authGenKey,1,
        AppPreferences.userName),
    val StartIndex: Int = 0,
    val SearchName: String = ""
)