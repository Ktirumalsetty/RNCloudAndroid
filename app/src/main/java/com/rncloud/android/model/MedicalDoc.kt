package com.rncloud.android.model

import com.rncloud.android.common.DateTimeUtils

data class MedicalDoc(
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
    val IssuingAuthorityLongValue: Any,
    val Remarks: String,
    val ValidFrom: String,
    val ValidTo: String
){
    val validFromFormatted:String
    get() = DateTimeUtils.formatToDate(ValidFrom)
}