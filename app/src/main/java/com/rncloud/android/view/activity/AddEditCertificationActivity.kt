package com.rncloud.android.view.activity

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rncloud.android.R
import com.rncloud.android.adapter.LicenceStatesSpinnerAdapter
import com.rncloud.android.databinding.ActivityAddEditCertificationBinding
import com.rncloud.android.databinding.ActivityAddEditLicenceBinding
import com.rncloud.android.model.Certification
import com.rncloud.android.model.Licence
import com.rncloud.android.model.LicenceState
import com.rncloud.android.view.activity.base.BaseActivity

import kotlinx.android.synthetic.main.activity_add_edit_certification.*

class AddEditCertificationActivity : BaseActivity<AddEditCertificationViewModel,ActivityAddEditCertificationBinding>() {
    lateinit var certification: Certification

    lateinit var states:ArrayList<LicenceState>

    override fun getLayoutRes(): Int {
        return R.layout.activity_add_edit_certification
    }

    override fun getViewModel(): Class<AddEditCertificationViewModel> {
       return AddEditCertificationViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        certification = intent.getSerializableExtra("certificationObj") as Certification
        if(certification!=null){
            supportActionBar?.title = getString(R.string.title_activity_edit_cert)
        }else{
            supportActionBar?.title = getString(R.string.title_activity_add_cert)
        }
        binding.viewModel = viewModel

        viewModel.getStates()
        viewModel.statesRespLiveData.observe(this, Observer {

            if (it!=null){
                if (!it.hasError){
                    states = it.Result as ArrayList<LicenceState>


//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.content.spinnerCertificationState.adapter = LicenceStatesSpinnerAdapter(this,states)
                    var selectedItemPos:Int = 0
                    states.forEach {
                        if(it.LongValue.equals(certification.IssuingAuthority)){
                            selectedItemPos = states.indexOf(it)
                            return@forEach
                        }
                    }

                    binding.content.spinnerCertificationState.setSelection(selectedItemPos)

                }

            }else{
                showErrorRespMsg()
            }
        })
        binding.certification = certification

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.rncloud.android.R.menu.certification_menu, menu)
        return true

    }


}
