package com.rncloud.android.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import com.rncloud.android.adapter.LicenceStatesSpinnerAdapter
import com.rncloud.android.databinding.ActivityAddEditLicenceBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.model.LicenceState
import com.rncloud.android.view.activity.base.BaseActivity


class AddEditLicenceActivity : BaseActivity<AddEditLicenceViewModel, ActivityAddEditLicenceBinding>() {

    lateinit var licence:Licence

    lateinit var states:ArrayList<LicenceState>

    override fun getLayoutRes(): Int {
        return com.rncloud.android.R.layout.activity_add_edit_licence
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
            supportActionBar?.title = getString(com.rncloud.android.R.string.title_activity_edit_licence)
        }else{
            supportActionBar?.title = getString(com.rncloud.android.R.string.title_activity_add_licence)
        }
        binding.viewModel = viewModel
        viewModel.getStates()
        viewModel.statesRespLiveData.observe(this, Observer {

            if (it!=null){
                if (!it.hasError){
                    states = it.Result as ArrayList<LicenceState>


//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.content.spinnerLicenceState.adapter = LicenceStatesSpinnerAdapter(this,states)
                    var selectedItemPos:Int = 0
                    states.forEach {
                        if(it.LongValue.equals(licence.IssuingAuthority)){
                            selectedItemPos = states.indexOf(it)
                            return@forEach
                        }
                    }

                    binding.content.spinnerLicenceState.setSelection(selectedItemPos)

                }

            }else{
                showErrorRespMsg()
            }
        })
        binding.licence = licence

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.rncloud.android.R.menu.certification_menu, menu)
        return true

    }

}
