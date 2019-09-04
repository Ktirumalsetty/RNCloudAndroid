package com.rncloud.android.model

import com.rncloud.android.common.AppPreferences

data class GenericCodeValuesByTypeReqModel(
    val CodeType: String = "CTIAT",
    val SendApiRequestModel: SendApiRequestModel = SendApiRequestModel(
        AppPreferences.authGenKey,1,
        AppPreferences.userName)
)