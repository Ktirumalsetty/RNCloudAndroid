package com.rncloud.android.model

import com.rncloud.android.base.BaseResp

data class LicenceResp(val Result: List<Licence>):BaseResp()

data class EducationsResp(val Result: List<Education>):BaseResp()

data class EmploymentResp(val Result: List<Employment>):BaseResp()

data class CertificationsResp(val Result: List<Certification>):BaseResp()