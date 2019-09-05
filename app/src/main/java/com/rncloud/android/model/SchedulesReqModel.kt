package com.rncloud.android.model

import com.rncloud.android.common.AppPreferences

data class SchedulesReqModel(
    val ConditionalOperator: Int =10,
    val EndIndex: Int,
    val FromDate: String ="",
    val Location: String ="",
    val NurseActorCode: Int,
    val RegistryActorCode: Int,
    val SendApiRequestModel: SendApiRequestModel=SendApiRequestModel(
        AppPreferences.authGenKey,1,
        AppPreferences.userName),
    val StartIndex: Int,
    val ToDate: String =""
)