package com.rncloud.android.model

import android.graphics.drawable.Drawable
import com.rncloud.android.R
import com.rncloud.android.common.DateTimeUtils

data class Schedule(
    val `$id`: String,
    val ApplicationDate: String,
    val ApplicationRemarks: String,
    val ApplicationStatus: String,
    val CareUnitName: String,
    val ContractPeriodInDays: Int,
    val Department: String,
    val HospitalActorCode: Int,
    val HospitalAddressLine1: String,
    val HospitalAddressLine2: String,
    val HospitalCity: String,
    val HospitalCountry: String,
    val HospitalCountryLongValue: String,
    val HospitalDisplayName: String,
    val HospitalFacilityCode: Int,
    val HospitalResponse: Any,
    val HospitalState: String,
    val HospitalTelephone1: String,
    val HospitalTelephone2: String,
    val JobApplicationCode: Int,
    val JobCode: Int,
    val JobDescription: String,
    val JobFormattedCode: String,
    val JobOrderCode: Int,
    val JobPostBenefits: String,
    val JobPostCode: Int,
    val JobPostCount: Int,
    val JobPostDescription: String,
    val JobType: String,
    val JoinByDate: String,
    val NurseActorCode: Int,
    val NurseDisplayName: String,
    val NurseImageCode: Int,
    val NurseImageFile: String,
    val NurseImageFileExtension: String,
    val NurseImageFileUrl: String,
    val NurseResponse: Any,
    val OrderCount: Int,
    val OrderDate: String,
    val OrderStatus: String,
    val PostedDate: String,
    val ProposedCopmpensation: String,
    val RegistryActorCode: Any,
    val RegistryAddressLine1: String,
    val RegistryAddressLine2: String,
    val RegistryCity: String,
    val RegistryCountry: String,
    val RegistryCountryLongValue: String,
    val RegistryDisplayName: Any,
    val RegistryFacilityCode: Any,
    val RegistryState: String,
    val RegistryTelephone1: String,
    val RegistryTelephone2: String,
    val Remarks: String,
    val RowNo: Int,
    val ShiftFromTime: String,
    val ShiftToTime: String,
    val ShiftType: String,
    val Specialty: String,
    val StaffDesc: String,
    val StaffType: String
){
    val orderDay:String
    get() = DateTimeUtils.formatToDay(OrderDate)
    val orderMonthYear:String
    get() = DateTimeUtils.formatToMonthYear(OrderDate)
    val schStatusFormatted:Int
    get() = if (ApplicationStatus.equals("Assigned")){
        R.drawable.sch_booked_status
    }else if (ApplicationStatus.equals("Released Offer")){
        R.drawable.sch_pending_status
    }else if (ApplicationStatus.equals("Rejected")){
        R.drawable.sch_rejected_status
    }else{
        R.drawable.round_dot

    }
}