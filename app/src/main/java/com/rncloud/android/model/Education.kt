package com.rncloud.android.model

import com.rncloud.android.common.DateTimeUtils

data class Education(
    val `$id`: String,
    val ActorCode: Int,
    val ActorDocCode: Int,
    val CompletedAt: String,
    val CourseStudy: String,
    val CourseType: String,
    val EducationCode: Int,
    val InstituteName: String,
    val InstituteType: String,
    val InstituteTypeLongValue: String,
    val IsGraduate: Boolean,
    val Location: String,
    val RecordStatus: String,
    val Remarks: String,
    val StartFrom: String,
    val Subjects: String
){
    val isGraduateFormatted:String
    get() {
//        return if(IsGraduate) ? "Graduate : Yes" : "Graduate : No"
        return if (IsGraduate){
            "Graduate : Yes"
        }else{
            "Graduate : No"

        }
    }

    val completedAtFormat:String
    get() = DateTimeUtils.formatToEduCompletedAt(CompletedAt)
}