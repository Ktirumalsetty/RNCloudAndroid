package com.rncloud.android.base

import com.rncloud.android.model.PersonalDetail

open class BaseResp(
    val ErrorCode: Int = 0,
    val ErrorData: String ="",
    val RedirectPage: Int = 0,
    val Remarks: String ="",
//    val ResultDescription: String,
    val hasError: Boolean =false,
    val hasRedirectPage: Boolean =false
)