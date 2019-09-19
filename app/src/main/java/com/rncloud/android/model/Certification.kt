package com.rncloud.android.model

import com.rncloud.android.common.DateTimeUtils
import java.io.Serializable

data class Certification(
    val `$id`: String,
    val ActorCode: Int,
    val ActorCredentialCode: Int,
    val ActorDocCode: Int,
    val AdditionalDetail: String,
    val CredentialNumber: String,
    val CredentialType: String,
    val CredentialTypeCode: Int,
    val CredentialTypeGroup: String,
    val ExtData: Any,
    val GroupOrFolder: String,
    val IssuingAuthority: String,
    val IssuingAuthorityLongValue: String,
    val Remarks: String,
    val ValidFrom: String,
    val ValidTo: String
): Serializable {
    val issueDateF: String
        get() = DateTimeUtils.formatToDate(ValidFrom)
    val expDateF: String
        get() = DateTimeUtils.formatToDate(ValidTo)
}