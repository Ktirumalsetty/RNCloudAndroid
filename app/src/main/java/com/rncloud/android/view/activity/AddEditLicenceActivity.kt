package com.rncloud.android.view.activity

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.rncloud.android.R
import com.rncloud.android.databinding.ActivityAddEditLicenceBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.view.activity.base.BaseActivity

class AddEditLicenceActivity : BaseActivity<AddEditLicenceViewModel, ActivityAddEditLicenceBinding>() {

    lateinit var licence:Licence

    override fun getLayoutRes(): Int {
        return R.layout.activity_add_edit_licence
    }

    override fun getViewModel(): Class<AddEditLicenceViewModel> {
        return AddEditLicenceViewModel::class.java
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        licence = intent.getSerializableExtra("licenceObj") as Licence
        if(licence!=null){
            binding.toolbar.title = getString(R.string.title_activity_edit_licence)
        }else{
            binding.toolbar.title = getString(R.string.title_activity_add_licence)
        }
        binding.licence = licence
    }

}
