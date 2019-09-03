package com.rncloud.android.model

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
)