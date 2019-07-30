package com.rncloud.android.model

data class LoginResponse(
//    val $id: String,
    val ErrorCode: Int,
    val ErrorData: String,
    val RedirectPage: Int,
    val Remarks: String,
    val Result: Result,
    val ResultDescription: String,
    val hasError: Boolean,
    val hasRedirectPage: Boolean
)

data class Result(
//    val $id: String,
    val FacilityConfigParamController: List<FacilityConfigParamController>,
    val RoleFeatureLinkInfoController: List<RoleFeatureLinkInfoController>,
    val xLoginController: XLoginController
)

data class RoleFeatureLinkInfoController(
//    val $id: String,
    val Code: Int,
    val Description: String,
    val Detail: Boolean,
    val Edit: Boolean,
    val FeatureID: String,
    val FeatureName: String,
    val FeatureType: String,
    val List: Boolean,
    val Mail: Boolean,
    val Remove: Boolean,
    val Report: Boolean,
    val RoleID: String
)

data class FacilityConfigParamController(
//    val $id: String,
    val ConfigParam: String,
    val ConfigParamType: String,
    val ConfigParamValue: String,
    val FacilityCode: Int,
    val FacilityConfigParamCode: Int
)

data class XLoginController(
//    val $id: String,
    val ActorCode: Int,
    val ActorImageFile: String,
    val ActorType: Int,
    val AuthGenKEY: String,
    val CompanyCode: Int,
    val DisplayName: String,
    val Email: String,
    val EmpFacilityCode: Int,
    val EmpFormattedCode: String,
    val EmployeeCode: Int,
    val FacilityActorCode: Int,
    val FacilityName: String,
    val FileExtension: String,
    val LangCode: Any,
    val LastLoginDate: String,
    val LocationCode: Any,
    val MobileNo: String,
    val RoleName: String,
    val TimeZoneCode: Int,
    val TimeZoneDisplayName: String,
    val TimeZoneUtcDiff: Int,
    val UserId: String,
    val UserName: String,
    val UserType: Int
)