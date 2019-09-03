package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rncloud.android.R
import com.rncloud.android.adapter.CertificationsAdapter
import com.rncloud.android.adapter.EmploymentAdapter
import com.rncloud.android.databinding.CertificationsFragmentBinding
import com.rncloud.android.model.Certification
import com.rncloud.android.model.Employment

class CertificationsFragment : BaseFragment<CertificationsFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.certifications_fragment

    companion object {
        fun newInstance() = CertificationsFragment()
    }

    private lateinit var viewModel: CertificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CertificationsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getCertificationsInfo()

        viewModel.certificationsRespLiveData.observe(this, Observer {
            if (it!=null && !it.hasError ){
                var certification = it.Result as ArrayList<Certification>
                binding.rvCertifications.adapter = CertificationsAdapter(certification)
            }else{
                showErrorRespMsg()
            }
        })
    }

}
