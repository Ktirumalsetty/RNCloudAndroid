package com.rncloud.android.model

import com.rncloud.android.base.BaseResp

data class LicenceResp(val Result: List<Licence>):BaseResp()

data class EducationsResp(val Result: List<Education>):BaseResp()

data class EmploymentResp(val Result: List<Employment>):BaseResp()

data class CertificationsResp(val Result: List<Certification>):BaseResp()

data class MedicalDocsResp(val Result: List<MedicalDoc>):BaseResp()

data class LicenceStateResp(val Result: List<LicenceState>):BaseResp()

data class SchedulesResp(val Result: List<Schedule>):BaseResp()

data class JobsResp(val Result: List<Job>):BaseResp()