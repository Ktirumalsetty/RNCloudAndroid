package com.rncloud.android.model

data class EmploymentHistory(
    val `$id`: String,
    val ActorCode: Int,
    val ActorDocCode: Int,
    val Address: String,
    val Duties: String,
    val EmpHistoryCode: Int,
    val EmployerName: String,
    val FromDate: String,
    val IsCurrent: Boolean,
    val MayContact: Boolean,
    val PhoneNo: String,
    val ReasonForLeaving: String,
    val RecordStatus: String,
    val Responsibilities: String,
    val SupervisorName: String,
    val SupervisorTitle: String,
    val ToDate: String
)