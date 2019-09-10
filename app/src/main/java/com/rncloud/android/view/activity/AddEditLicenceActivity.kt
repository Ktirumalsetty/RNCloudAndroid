package com.rncloud.android.view.activity

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.rncloud.android.R
import com.rncloud.android.databinding.ActivityAddEditLicenceBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.model.LicenceState
import com.rncloud.android.view.activity.base.BaseActivity
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import com.rncloud.android.adapter.LicenceStatesSpinnerAdapter


class AddEditLicenceActivity : BaseActivity<AddEditLicenceViewModel, ActivityAddEditLicenceBinding>() {

    lateinit var licence:Licence

    lateinit var states:ArrayList<LicenceState>

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
            supportActionBar?.title = getString(R.string.title_activity_edit_licence)
        }else{
            supportActionBar?.title = getString(R.string.title_activity_add_licence)
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

}
