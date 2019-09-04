package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rncloud.android.R
import com.rncloud.android.adapter.LicencesAdapter
import com.rncloud.android.adapter.MedicalDocAdapter
import com.rncloud.android.databinding.MedicalDocsFragmentBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.model.MedicalDoc

class MedicalDocsFragment : BaseFragment<MedicalDocsFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.medical_docs_fragment

    companion object {
        fun newInstance() = MedicalDocsFragment()
    }

    private lateinit var viewModel: MedicalDocsViewModel

    private lateinit var medicalDocs: ArrayList<MedicalDoc>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MedicalDocsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getEmploymentInfo()
        viewModel.medicalDocRespLiveData.observe(this, Observer {

            if (it!=null && !it.hasError ){
                medicalDocs = it.Result as ArrayList<MedicalDoc>
                binding.rvMedicalDocs.adapter = MedicalDocAdapter(medicalDocs)
            }else{
                showErrorRespMsg()
            }

        })

    }

}
