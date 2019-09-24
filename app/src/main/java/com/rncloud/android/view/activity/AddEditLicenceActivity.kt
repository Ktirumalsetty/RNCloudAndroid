package com.rncloud.android.view.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.Observer
import com.rncloud.android.adapter.LicenceStatesSpinnerAdapter
import com.rncloud.android.common.DateTimeUtils
import com.rncloud.android.databinding.ActivityAddEditLicenceBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.model.LicenceState
import com.rncloud.android.view.activity.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddEditLicenceActivity : BaseActivity<AddEditLicenceViewModel, ActivityAddEditLicenceBinding>() {

    lateinit var licence:Licence

    lateinit var states:ArrayList<LicenceState>

    var cal = Calendar.getInstance()


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

        binding.content.etLicenceValidFrom.setOnClickListener(View.OnClickListener {
            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val sdf = SimpleDateFormat(DateTimeUtils.CLIENT_DATE_TIME_FORMAAT_2, Locale.US)

                    binding.content.etLicenceValidFrom.setText(sdf.format(cal.time))

                },
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()


    })

        binding.content.etLicenceValidTo.setOnClickListener(View.OnClickListener {
            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val sdf = SimpleDateFormat(DateTimeUtils.CLIENT_DATE_TIME_FORMAAT_2, Locale.US)

                    binding.content.etLicenceValidTo.setText(sdf.format(cal.time))

                },
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

    })

    }

//    // create an OnDateSetListener
//    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
//        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
//                               dayOfMonth: Int) {
//            cal.set(Calendar.YEAR, year)
//            cal.set(Calendar.MONTH, monthOfYear)
//            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateDateInView()
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.rncloud.android.R.menu.certification_menu, menu)
        return true

    }

}
